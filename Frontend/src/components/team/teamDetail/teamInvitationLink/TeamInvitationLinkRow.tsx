import { Button } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../../types/team";
import FormItem from "../../../ui/form/FormItem";

const TeamInvitationLinkRow: React.FC<{
    teamInvitationLink: tTeamInvitationLink
}> = (props) => {

    const teamInvitationLink = props.teamInvitationLink;

    return <tr>
        <td>{teamInvitationLink.id}</td>
        <td>{teamInvitationLink.validFrom}</td>
        <td>{teamInvitationLink.validTo}</td>
        <td><FormItem defaultValue={teamInvitationLink.teamInvitationLink} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
        <td style={{ textAlign: "right" }}><Button variant="secondary" size="sm" style={{ width: '100px' }}>{teamInvitationLink.validTo ? "Revalidate" : "Invalidate"}</Button></td>
    </tr>;

}

export default TeamInvitationLinkRow;