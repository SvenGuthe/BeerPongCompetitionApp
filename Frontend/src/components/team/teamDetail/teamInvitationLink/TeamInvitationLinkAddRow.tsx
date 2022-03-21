import { useRef } from "react";
import { useDispatch } from "react-redux";
import { addTeamInvitationLink } from "../../../../store/team/team-store-actions";
import FormItem from "../../../ui/form/FormItem";

const TeamInvitationLinkAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const teamInvitationLinkRef = useRef<HTMLInputElement>(null);

    const onSaveNewInvitationLink = (newValue: string) => {
        dispatch(addTeamInvitationLink(id, newValue));
        teamInvitationLinkRef.current!.value = "";
    }


    return <tr style={{borderTop: "2px dashed black"}}>
        <td colSpan={3} style={{ textAlign: "right" }}>Neuen Einladungslink Erstellen:</td>
        <td colSpan={2}><FormItem ref={teamInvitationLinkRef} defaultValue="" saveValue={(newValue, changed) => onSaveNewInvitationLink(newValue as string)} /></td>
    </tr>;

}

export default TeamInvitationLinkAddRow;