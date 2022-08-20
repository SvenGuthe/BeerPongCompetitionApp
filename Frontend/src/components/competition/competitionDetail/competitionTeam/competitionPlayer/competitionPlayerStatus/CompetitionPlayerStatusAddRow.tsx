import { useDispatch } from "react-redux";
import { updateCompetitionPlayerStatus } from "../../../../../../store/competition/competition-store-actions";
import { tCompetitionPlayerStatusUpdate } from "../../../../../../types/competition";
import { tCompetitionPlayerStatusType } from "../../../../../../types/enums/competitionPlayerStatusType";
import { CompetitionPlayerStatusTypeInput } from "../../../../../ui/form/PredefinedSelectInputs";

const CompetitionPlayerStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();


    const onSaveNewCompetitionPlayerStatusType = (newValue: string[]) => {
        const competitionPlayerStatus: tCompetitionPlayerStatusUpdate = {
            id: id,
            competitionPlayerStatusType: newValue[0] as tCompetitionPlayerStatusType
        }
        dispatch(updateCompetitionPlayerStatus(competitionPlayerStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Turnier Spieler Status setzen:</td>
        <td colSpan={2}><CompetitionPlayerStatusTypeInput saveValue={(newValue, changed) => {
            onSaveNewCompetitionPlayerStatusType(newValue as string[]);
        }} add /></td>
    </tr>;

};

export default CompetitionPlayerStatusAddRow;