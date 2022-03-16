import { useDispatch } from "react-redux";
import { changeCompetitionStatus } from "../../../../store/competition/competition-store-actions";
import { tCompetitionStatusType } from "../../../../types/enums/competitionStatusType";
import { CompetitionStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

const CompetitionStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewCompetitionStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tCompetitionStatusType;
        dispatch(changeCompetitionStatus(id, newStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Team Status setzen:</td>
        <td colSpan={2}><CompetitionStatusTypeInput saveValue={(newValue, changed) => {
            onSaveNewCompetitionStatusType(newValue as string[]);
        }} add /></td>
    </tr>;

};

export default CompetitionStatusAddRow;