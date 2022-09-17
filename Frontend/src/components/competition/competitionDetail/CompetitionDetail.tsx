import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import {
  addCompetition,
  removeCompetitionDetail,
  storeCompetitionDetail,
} from "../../../store/competition/competition-store";
import { tEnum } from "../../../types/defaults/generics";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import CompetitionAdminAdd from "./competitionAdmin/CompetitionAdminAdd";
import CompetitionAdminDetail from "./competitionAdmin/CompetitionAdminDetail";
import CompetitionDetailTable from "./CompetitionDetailTable";
import CompetitionStatusAddRow from "./competitionStatus/CompetitionStatusAddRow";
import CompetitionTeamAdd from "./competitionTeam/CompetitionTeamAdd";
import CompetitionTeamDetail from "./competitionTeam/CompetitionTeamDetail";

/**
 * Component to show the competition details and the competition- + competitionadminstatus + all competition teams
 * @returns JSX with the competition details, all competition teams, competition admins and the status
 */
const CompetitionDetail: React.FC = () => {
  const dispatch = useDispatch();

  // get the competition id from the url params
  const competitionId = useParams().id;

  // get the competition details from redux
  const { competitionDetail } = useSelector((state: RootState) => {
    return {
      competitionDetail: state.competition.competitionDetail,
    };
  });

  // just if the competition detail changes, the competition status should be reloaded
  const competitionStatus = useMemo(() => {
    return competitionDetail?.competition.competitionStatus;
  }, [competitionDetail]);

  // just if the competition detail changes, the competition admins should be reloaded
  const competitionAdminStatus = useMemo(() => {
    return competitionDetail?.competition.competitionAdmins;
  }, [competitionDetail]);

  // just if the competition detail changes, the competition teams should be reloaded
  const competitionTeamDetails = useMemo(() => {
    return competitionDetail?.competition.competitionTeams;
  }, [competitionDetail]);

  // if there is an id in the url, load the competition details from the api
  useEffect(() => {
    if (competitionId) {
      // load the details for the given id, add the competition to the local fetched competition (if not already stored) and also add the competition details
      dispatch(
        getRequestWithID(+competitionId, "/competition/competition", [
          addCompetition,
          storeCompetitionDetail,
        ])
      );
    }

    // if the page is unmount, remove the competition detail
    return () => {
      dispatch(removeCompetitionDetail());
    };

    // Reload if the id was changed
  }, [competitionId, dispatch]);

  return (
    <>
      {competitionDetail && (
        <>
          <CompetitionDetailTable competition={competitionDetail.competition} />
          {competitionStatus && (
            <TableSection>
              <h3>Turnier Status</h3>
              <EnumTable
                enumData={[
                  ...competitionStatus.map((singleCompetitionStatus) => {
                    // Add additional attributes (valid from and valid to) to the default enum-table
                    const additionalAttributes = [
                      {
                        id: singleCompetitionStatus.id + "_validFrom",
                        value: singleCompetitionStatus.validFrom,
                      },
                      {
                        id: singleCompetitionStatus.id + "_validTo",
                        value: singleCompetitionStatus.validTo,
                      },
                    ];

                    const newCompetitionStatus = {
                      ...singleCompetitionStatus,
                      additionalAttributes: additionalAttributes,
                    };

                    return newCompetitionStatus;
                  }),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={
                  <CompetitionStatusAddRow
                    competitionId={competitionDetail.competition.id}
                  />
                }
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
          {competitionAdminStatus && (
            <TableSection>
              <h3>Turnier Admins</h3>
              {competitionAdminStatus.map((singleCompetitionAdminStatus) => {
                return (
                  <TableSection key={singleCompetitionAdminStatus.id}>
                    <CompetitionAdminDetail
                      competitionAdminDetail={singleCompetitionAdminStatus}
                    />
                  </TableSection>
                );
              })}
              <p>
                <b>User als Administrator hinzufügen:</b>
              </p>
              <CompetitionAdminAdd
                users={competitionDetail.possibleAdminUsers}
                competitionId={competitionDetail.competition.id}
              />
            </TableSection>
          )}
          {competitionTeamDetails && (
            <TableSection>
              <h3>Turnier Teams</h3>
              {competitionTeamDetails.map((competitionTeamDetail) => {
                return (
                  <TableSection key={competitionTeamDetail.id}>
                    <CompetitionTeamDetail
                      competitionTeamDetail={competitionTeamDetail}
                      teams={competitionDetail.teams}
                      users={competitionDetail.possiblePlayers}
                    />
                  </TableSection>
                );
              })}
              <p>
                <b>Turnier Team Hinzufügen:</b>
              </p>
              <CompetitionTeamAdd
                teams={competitionDetail.teams}
                users={competitionDetail.possiblePlayers}
                competitionId={competitionDetail.competition.id}
              />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default CompetitionDetail;
