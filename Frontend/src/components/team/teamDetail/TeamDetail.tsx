import React, { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import {
  addTeam,
  removeTeamDetail,
  storeTeamDetail,
} from "../../../store/team/team-store";
import { tEnum } from "../../../types/defaults/generics";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import UserDetailsTableTeam from "../../user/userDetail/UserDetailTableTeam";
import TeamCompositionAdd from "./teamComposition/TeamCompositionAdd";
import TeamCompositionStatusAddRow from "./teamComposition/TeamCompositionStatusAddRow";
import TeamDetailTable from "./TeamDetailTable";
import TeamInvitationLinkTable from "./teamInvitationLink/TeamInvitationLinkTable";
import TeamStatusAddRow from "./teamStatus/TeamStatusAddRow";

/**
 * Component to show the team details and the teamstatus + teamlinks + teamusers + all competitions of the teams
 * @returns JSX with all the information of a team
 */
const TeamDetail: React.FC = () => {
  const dispatch = useDispatch();

  // get the team id from the url params
  const teamId = useParams().id;

  // get the team details from redux
  const { teamDetail } = useSelector((state: RootState) => {
    return {
      teamDetail: state.team.teamDetail,
    };
  });

  // just if the team detail changes, the team status should be reloaded
  const teamStatus = useMemo(() => {
    return teamDetail?.team.teamStatus;
  }, [teamDetail]);

  // just if the team detail changes, the team invitation links should be reloaded
  const teamInvitationLinks = useMemo(() => {
    return teamDetail?.team.teamInvitationLinks;
  }, [teamDetail]);

  // just if the team detail changes, the team users should be reloaded
  const users = useMemo(() => {
    return teamDetail?.users;
  }, [teamDetail]);

  // just if the team detail changes, the team competitions should be reloaded
  const competitions = useMemo(() => {
    return removeDuplicates(teamDetail?.competitions);
  }, [teamDetail]);

  // if there is an id in the url, load the team details from the api
  useEffect(() => {
    if (teamId) {
      // load the details for the given id, add the team to the local fetched teams (if not already stored) and also add the team details
      dispatch(
        getRequestWithID(+teamId, "/team/team", [addTeam, storeTeamDetail])
      );
    }

    // if the page is unmount, remove the team detail
    return () => {
      dispatch(removeTeamDetail());
    };

    // Reload if the id was changed
  }, [teamId, dispatch]);

  return (
    <>
      {teamDetail && (
        <>
          <TeamDetailTable team={teamDetail.team} />
          {teamStatus && (
            <TableSection>
              <h3>Team Status</h3>
              <EnumTable
                enumData={[
                  ...teamStatus.map((singleTeamStatus) => {
                    const additionalAttributes = [
                      {
                        id: singleTeamStatus.id + "_validFrom",
                        value: singleTeamStatus.validFrom,
                      },
                      {
                        id: singleTeamStatus.id + "_validTo",
                        value: singleTeamStatus.validTo,
                      },
                    ];

                    const newTeamStatus = {
                      ...singleTeamStatus,
                      additionalAttributes,
                    };

                    return newTeamStatus;
                  }),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={<TeamStatusAddRow teamId={teamDetail.team.id} />}
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
          {teamInvitationLinks && (
            <TableSection>
              <h3>Team Einladungslinks</h3>
              <TeamInvitationLinkTable
                teamId={teamDetail.team.id}
                teamInvitationLinks={teamInvitationLinks}
                wrapped
              />
            </TableSection>
          )}
          {users && (
            <TableSection>
              <h3>Team Mitglieder</h3>
              {users.map((user) => {
                return (
                  <TableSection key={user.id}>
                    <h4>{user.user.gamerTag}</h4>
                    <UserDetailsTableTeam
                      user={user.user}
                      admin={user.isAdmin}
                      teamCompositionId={user.id}
                    ></UserDetailsTableTeam>

                    <EnumTable
                      enumData={[
                        ...user.teamCompositionStatus.map(
                          (singleTeamCompositionStatus) => {
                            const additionalAttributes = [
                              {
                                id:
                                  singleTeamCompositionStatus.id + "_validFrom",
                                value: singleTeamCompositionStatus.validFrom,
                              },
                              {
                                id: singleTeamCompositionStatus.id + "_validTo",
                                value: singleTeamCompositionStatus.validTo,
                              },
                            ];

                            const newTeamCompositionStatus = {
                              ...singleTeamCompositionStatus,
                              additionalAttributes,
                            };

                            return newTeamCompositionStatus;
                          }
                        ),
                      ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                      wrapped
                      addRow={
                        <TeamCompositionStatusAddRow
                          teamCompositionId={user.id}
                        />
                      }
                      additionalAttributesHeader={["Valide von", "Valide bis"]}
                    />
                  </TableSection>
                );
              })}
              <TeamCompositionAdd
                teamId={teamDetail.team.id}
                users={teamDetail.possibleUsers}
              />
            </TableSection>
          )}
          {competitions && (
            <TableSection>
              <h3>Turniere</h3>
              <CompetitionTable competitions={competitions} wrapped />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default TeamDetail;
