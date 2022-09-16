import { useMemo } from "react";
import { tCompetitionPlayer } from "../../../../../types/competition";
import { tEnum } from "../../../../../types/defaults/generics";
import EnumTable from "../../../../enums/EnumTable";
import TableSection from "../../../../layout/TableSection";
import CompetitionPlayerDetailTable from "./CompetitionPlayerDetailTable";
import CompetitionPlayerStatusAddRow from "./competitionPlayerStatus/CompetitionPlayerStatusAddRow";

/**
 * Component to show the competition player details and the status of every competition player
 * @param props a single user (competition player) with the history of the status (id = competition player id)
 * @returns JSX with all players and the history of the competition players
 */
const CompetitionPlayerDetail: React.FC<{
  competitionPlayerDetail: tCompetitionPlayer;
}> = (props) => {
  // get the competition player detail for the given competition
  const competitionPlayerDetail = props.competitionPlayerDetail;

  // just if competiton player detail changes, the status should be reloaded
  const competitionPlayerStatus = useMemo(() => {
    return competitionPlayerDetail.competitionPlayerStatus;
  }, [competitionPlayerDetail]);

  return (
    <>
      {competitionPlayerDetail && (
        <>
          <h6>{competitionPlayerDetail.user.gamerTag}</h6>
          <CompetitionPlayerDetailTable
            competitionPlayerDetail={competitionPlayerDetail}
          />
          {competitionPlayerStatus && (
            <TableSection>
              <h5>Turnier Spieler Status</h5>
              <EnumTable
                enumData={[
                  ...competitionPlayerStatus.map(
                    (singleCompetitionPlayerStatus) => {
                      // Add additional attributes (valid from and valid to) to the default enum-table
                      const additionalAttributes = [
                        {
                          id: singleCompetitionPlayerStatus.id + "_validFrom",
                          value: singleCompetitionPlayerStatus.validFrom,
                        },
                        {
                          id: singleCompetitionPlayerStatus.id + "_validTo",
                          value: singleCompetitionPlayerStatus.validTo,
                        },
                      ];

                      const newCompetitionPlayerStatus = {
                        ...singleCompetitionPlayerStatus,
                        additionalAttributes: additionalAttributes,
                      };

                      // Return the enum data (player status) with additional data (valid from and valid to)
                      return newCompetitionPlayerStatus;
                    }
                  ),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={
                  <CompetitionPlayerStatusAddRow
                    id={competitionPlayerDetail.id}
                  />
                }
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default CompetitionPlayerDetail;
