import { useDispatch } from "react-redux";
import { updateTeamCompositionStatus } from "../../../../store/team/team-store-actions";
import { tTeamCompositionStatusType } from "../../../../types/enums/teamCompositionStatusType";
import { tTeamCompositionStatusUpdate } from "../../../../types/team";
import { TeamCompositionStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

const TeamCompositionStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewTeamCompositionStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tTeamCompositionStatusType;
        const teamCompositionStatus: tTeamCompositionStatusUpdate = {
            id: id,
            teamCompositionStatusType: newStatus
        }
        dispatch(updateTeamCompositionStatus(teamCompositionStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Team Composition Status setzen:</td>
        <td colSpan={2}><TeamCompositionStatusTypeInput saveValue={(newValue, changed) =>
            onSaveNewTeamCompositionStatusType(newValue as string[])
        } add /></td>
    </tr>;


}

export default TeamCompositionStatusAddRow;