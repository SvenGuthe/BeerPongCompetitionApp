import { tCompetitionAdmin } from "../../../../types/competition";
import CompetitionAdminDetailTable from "./CompetitionAdminDetailTable";
import TableSection from "../../../layout/TableSection";
import EnumTable from "../../../enums/EnumTable";
import AdminStatusAddRow from "./adminStatus/AdminStatusAddRow";
import { useMemo } from "react";
import { tEnum } from "../../../../types/defaults/generics";

const CompetitionAdminDetail: React.FC<{
    competitionAdminDetail: tCompetitionAdmin;
}> = (props) => {

    const competitionAdminDetail = props.competitionAdminDetail;

    const competitionAdminStatus = useMemo(() => {
        return competitionAdminDetail.competitionAdminStatus;
    }, [competitionAdminDetail]);

    return <>
        {competitionAdminDetail && <>
            <h4>{competitionAdminDetail.user.gamerTag}</h4>
            <CompetitionAdminDetailTable competitionAdminDetail={competitionAdminDetail} />
            {competitionAdminStatus && <TableSection>
                <h5>Turnier Admin Status</h5>
                <EnumTable enumData={[...competitionAdminStatus.map(singleCompetitionAdminStatus => {

                    const additionalAttributes = [
                        {
                            id: singleCompetitionAdminStatus.id + "_validFrom",
                            value: singleCompetitionAdminStatus.validFrom
                        },
                        {
                            id: singleCompetitionAdminStatus.id + "_validTo",
                            value: singleCompetitionAdminStatus.validTo
                        }
                    ]

                    const newCompetitionAdminStatus = {
                        ...singleCompetitionAdminStatus,
                        additionalAttributes: additionalAttributes
                    }

                    return newCompetitionAdminStatus;

                })].sort((a: tEnum, b: tEnum) => a.id - b.id)} wrapped addRow={<AdminStatusAddRow id={competitionAdminDetail.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
        </>}
    </>;

};

export default CompetitionAdminDetail;