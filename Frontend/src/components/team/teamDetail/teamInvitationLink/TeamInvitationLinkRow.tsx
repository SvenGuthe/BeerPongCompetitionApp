import { useRef } from "react";
import { Button } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../../types/team";
import FormItem from "../../../ui/form/FormItem";

const TeamInvitationLinkRow: React.FC<{
    teamInvitationLink: tTeamInvitationLink
}> = (props) => {

    const teamInvitationLink = props.teamInvitationLink;
    const additionalAttributes = teamInvitationLink.additionalAttributes ? teamInvitationLink.additionalAttributes : []

    const teamInvitationLinkRef = useRef<HTMLInputElement>(null);

    return <tr>
        <td>{teamInvitationLink.id}</td>
        <td>{teamInvitationLink.validFrom}</td>
        <td>{teamInvitationLink.validTo}</td>
        <td><FormItem ref={teamInvitationLinkRef} defaultValue={teamInvitationLink.teamInvitationLink} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
        <td style={{ textAlign: "right" }}><Button variant="secondary" size="sm" style={{ width: '100px' }}>{teamInvitationLink.validTo ? "Revalidate" : "Invalidate"}</Button></td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

}

export default TeamInvitationLinkRow;