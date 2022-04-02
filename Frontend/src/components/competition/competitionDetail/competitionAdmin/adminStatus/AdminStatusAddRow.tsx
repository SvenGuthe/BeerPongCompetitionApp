import { useDispatch } from "react-redux";
import { changeCompetitionAdminStatus } from "../../../../../store/competition/competition-store-actions";
import { tCompetitionAdminStatusType } from "../../../../../types/enums/competitionAdminStatusType";
import { CompetitionAdminStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

const AdminStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewCompetitionAdminStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tCompetitionAdminStatusType;
        dispatch(changeCompetitionAdminStatus(id, newStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Turnier Admin Status setzen:</td>
        <td colSpan={2}><CompetitionAdminStatusTypeInput saveValue={(newValue, changed) => {
            onSaveNewCompetitionAdminStatusType(newValue as string[]);
        }} add /></td>
    </tr>;

};

export default AdminStatusAddRow;