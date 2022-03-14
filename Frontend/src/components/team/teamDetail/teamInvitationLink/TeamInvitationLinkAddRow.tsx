import FormItem from "../../../ui/form/FormItem";

const TeamInvitationLinkAddRow: React.FC = (props) => {

    return <tr style={{borderTop: "2px dashed black"}}>
        <td colSpan={3} style={{ textAlign: "right" }}>Neuen Einladungslink Erstellen:</td>
        <td colSpan={2}><FormItem defaultValue="" saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;

}

export default TeamInvitationLinkAddRow;