import { useDispatch } from "react-redux";
import { updateTeamStatus } from "../../../../store/team/team-store-actions";
import { tTeamStatusType } from "../../../../types/enums/teamStatusType";
import { TeamStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

const TeamStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewTeamStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tTeamStatusType;
        dispatch(updateTeamStatus(id, newStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Team Status setzen:</td>
        <td colSpan={2}><TeamStatusTypeInput saveValue={(newValue, changed) =>
            onSaveNewTeamStatusType(newValue as string[])
        } add /></td>
    </tr>;


}

export default TeamStatusAddRow;