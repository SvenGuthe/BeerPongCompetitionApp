import { useMemo } from "react";
import { tUserIDAndGamerTag } from "../../../../types/user";
import { tCompetitionTeam } from "../../../../types/competition";
import { tEnum } from "../../../../types/defaults/generics";
import { tTeamAndUser } from "../../../../types/team";
import EnumTable from "../../../enums/EnumTable";
import TableSection from "../../../layout/TableSection";
import BillingStatusAddRow from "./billingStatus/BillingStatusAddRow";
import CompetitionPlayerAdd from "./competitionPlayer/CompetitionPlayerAdd";
import CompetitionPlayerDetail from "./competitionPlayer/CompetitionPlayerDetail";
import CompetitionTeamDetailTable from "./CompetitionTeamDetailTable";
import RegistrationStatusAddRow from "./registrationStatus/RegistrationStatusAddRow";

/**
 * Component to show the competition team details and the registration- + billingstatus + competition players
 * @param props competition details with the registration- and billing status and competition players
 *              teams to know, who could be possible add to the competition team
 *              users to know, who could be possible add to the competition team
 * @returns JSX with the competition team details and the registration- + billinstatus and all competition players
 */
const CompetitionTeamDetail: React.FC<{
  competitionTeamDetail: tCompetitionTeam;
  teams: tTeamAndUser[];
  users: tUserIDAndGamerTag[];
}> = (props) => {
  // get the competition team detail from the props
  const competitionTeamDetail = props.competitionTeamDetail;

  // just if the competition team detail changes, the registration status should be reloaded
  const competitionTeamRegistrationStatus = useMemo(() => {
    return competitionTeamDetail.registrationStatus;
  }, [competitionTeamDetail]);

  // just if the competition team detail changes, the billing status should be reloaded
  const competitionTeamBillingStatus = useMemo(() => {
    return competitionTeamDetail.billingStatus;
  }, [competitionTeamDetail]);

  // just if the competition team detail changes, the competition players should be reloaded
  const competitionPlayer = useMemo(() => {
    return competitionTeamDetail.competitionPlayer;
  }, [competitionTeamDetail]);

  // If the competition team has a "real" team than choose the right team
  let team;
  if (competitionTeamDetail.team) {
    team = props.teams.filter(
      (team) => team.team.id === competitionTeamDetail.team!.id
    )[0];
  }

  return (
    <>
      {competitionTeamDetail && (
        <>
          <h4>{competitionTeamDetail.competitionTeamName}</h4>
          <CompetitionTeamDetailTable
            teams={props.teams}
            competitionTeamDetail={competitionTeamDetail}
          />
          {competitionTeamRegistrationStatus && (
            <TableSection>
              <h5>Turnier Team Registrations Status</h5>
              <EnumTable
                enumData={[
                  ...competitionTeamRegistrationStatus.map(
                    (singleCompetitionTeamRegistrationStatus) => {
                      // Add additional attributes (valid from and valid to) to the default enum-table
                      const additionalAttributes = [
                        {
                          id:
                            singleCompetitionTeamRegistrationStatus.id +
                            "_validFrom",
                          value:
                            singleCompetitionTeamRegistrationStatus.validFrom,
                        },
                        {
                          id:
                            singleCompetitionTeamRegistrationStatus.id +
                            "_validTo",
                          value:
                            singleCompetitionTeamRegistrationStatus.validTo,
                        },
                      ];

                      const newCompetitionTeamRegistrationStatus = {
                        ...singleCompetitionTeamRegistrationStatus,
                        additionalAttributes: additionalAttributes,
                      };

                      return newCompetitionTeamRegistrationStatus;
                    }
                  ),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={
                  <RegistrationStatusAddRow id={competitionTeamDetail.id} />
                }
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
          {competitionTeamBillingStatus && (
            <TableSection>
              <h5>Turnier Team Zahlungs Status</h5>
              <EnumTable
                enumData={[
                  ...competitionTeamBillingStatus.map(
                    (singleCompetitionTeamBillingStatus) => {
                      // // Add additional attributes (valid from and valid to) to the default enum-table
                      const additionalAttributes = [
                        {
                          id:
                            singleCompetitionTeamBillingStatus.id +
                            "_validFrom",
                          value: singleCompetitionTeamBillingStatus.validFrom,
                        },
                        {
                          id:
                            singleCompetitionTeamBillingStatus.id + "_validTo",
                          value: singleCompetitionTeamBillingStatus.validTo,
                        },
                      ];

                      const newCompetitionTeamBillingStatus = {
                        ...singleCompetitionTeamBillingStatus,
                        additionalAttributes: additionalAttributes,
                      };

                      return newCompetitionTeamBillingStatus;
                    }
                  ),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={<BillingStatusAddRow id={competitionTeamDetail.id} />}
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
          {competitionPlayer && (
            <TableSection>
              <h5>Turnier Spieler</h5>
              {competitionPlayer.map((singleCompetitionPlayer) => {
                return (
                  <TableSection key={singleCompetitionPlayer.id}>
                    <CompetitionPlayerDetail
                      competitionPlayerDetail={singleCompetitionPlayer}
                    />
                  </TableSection>
                );
              })}
              <CompetitionPlayerAdd
                team={team}
                user={props.users}
                id={competitionTeamDetail.id}
              />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default CompetitionTeamDetail;
