import { tCompetitionAdmin } from "../../../../types/competition";
import CompetitionAdminDetailTable from "./CompetitionAdminDetailTable";
import TableSection from "../../../layout/TableSection";
import EnumTable from "../../../enums/EnumTable";
import AdminStatusAddRow from "./adminStatus/AdminStatusAddRow";
import { useMemo } from "react";
import { tEnum } from "../../../../types/defaults/generics";

/**
 * Component to show the competition admin details and the status of every competition admin
 * @param props a single user (competition admin) with the history of the status (id = competition admin id)
 * @returns JSX with all admins and the history of the admin status
 */
const CompetitionAdminDetail: React.FC<{
  competitionAdminDetail: tCompetitionAdmin;
}> = (props) => {
  // get the competition admin detail for the given competition
  const competitionAdminDetail = props.competitionAdminDetail;

  // just if competition admin detail changes, the status should be changed
  const competitionAdminStatus = useMemo(() => {
    return competitionAdminDetail.competitionAdminStatus;
  }, [competitionAdminDetail]);

  return (
    <>
      {competitionAdminDetail && (
        <>
          <h4>{competitionAdminDetail.user.gamerTag}</h4>
          <CompetitionAdminDetailTable
            competitionAdminDetail={competitionAdminDetail}
          />
          {competitionAdminStatus && (
            <TableSection>
              <h5>Turnier Admin Status</h5>
              <EnumTable
                enumData={[
                  ...competitionAdminStatus.map(
                    (singleCompetitionAdminStatus) => {
                      // Add additional attributes (valid from and valid to) to the default enum-table
                      const additionalAttributes = [
                        {
                          id: singleCompetitionAdminStatus.id + "_validFrom",
                          value: singleCompetitionAdminStatus.validFrom,
                        },
                        {
                          id: singleCompetitionAdminStatus.id + "_validTo",
                          value: singleCompetitionAdminStatus.validTo,
                        },
                      ];

                      const newCompetitionAdminStatus = {
                        ...singleCompetitionAdminStatus,
                        additionalAttributes: additionalAttributes,
                      };

                      // Return the enum data (admin status) with additional data (valid from and valid to)
                      return newCompetitionAdminStatus;
                    }
                  ),
                ].sort((a: tEnum, b: tEnum) => a.id - b.id)}
                wrapped
                addRow={<AdminStatusAddRow id={competitionAdminDetail.id} />}
                additionalAttributesHeader={["Valide von", "Valide bis"]}
              />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default CompetitionAdminDetail;
