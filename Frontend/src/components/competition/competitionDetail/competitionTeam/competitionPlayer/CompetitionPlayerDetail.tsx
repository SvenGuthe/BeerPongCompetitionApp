import { useMemo } from "react";
import { tCompetitionPlayer } from "../../../../../types/competition";
import { tEnum } from "../../../../../types/defaults/generics";
import EnumTable from "../../../../enums/EnumTable";
import TableSection from "../../../../layout/TableSection";
import CompetitionPlayerDetailTable from "./CompetitionPlayerDetailTable";
import CompetitionPlayerStatusAddRow from "./competitionPlayerStatus/CompetitionPlayerStatusAddRow";

const CompetitionPlayerDetail: React.FC<{
    competitionPlayerDetail: tCompetitionPlayer
}> = (props) => {

    const competitionPlayerDetail = props.competitionPlayerDetail;

    const competitionPlayerStatus = useMemo(() => {
        return competitionPlayerDetail.competitionPlayerStatus
    }, [competitionPlayerDetail])

    return <>
        {competitionPlayerDetail && <>
            <h6>{competitionPlayerDetail.user.gamerTag}</h6>
            <CompetitionPlayerDetailTable competitionPlayerDetail={competitionPlayerDetail} />
            {competitionPlayerStatus && <TableSection>
                <h5>Turnier Spieler Status</h5>
                <EnumTable enumData={[...competitionPlayerStatus
                    .map(singleCompetitionPlayerStatus => {
                        const additionalAttributes = [
                            {
                                id: singleCompetitionPlayerStatus.id + "_validFrom",
                                value: singleCompetitionPlayerStatus.validFrom
                            },
                            {
                                id: singleCompetitionPlayerStatus.id + "_validTo",
                                value: singleCompetitionPlayerStatus.validTo
                            }
                        ]

                        const newCompetitionPlayerStatus = {
                            ...singleCompetitionPlayerStatus,
                            additionalAttributes: additionalAttributes
                        }

                        return newCompetitionPlayerStatus;
                    })].sort((a: tEnum, b: tEnum) => a.id - b.id)} wrapped addRow={<CompetitionPlayerStatusAddRow id={competitionPlayerDetail.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
        </>}
    </>;

};

export default CompetitionPlayerDetail;