import { Table } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../../types/team";
import TeamInvitationLinkAddRow from "./TeamInvitationLinkAddRow";
import TeamInvitationLinkRow from "./TeamInvitationLinkRow";

const TeamInvitationLinkTable: React.FC<{
    teamInvitationLinks: tTeamInvitationLink[],
    id: number,
    wrapped?: boolean,
    additionalAttributesHeader?: string[]
}> = (props) => {

    const teamInvitationLinks = props.teamInvitationLinks;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th>ID</th>
                <th>Valide von</th>
                <th>Valide bis</th>
                <th>Link</th>
                <th></th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {teamInvitationLinks.map(teamInvitationLink => <TeamInvitationLinkRow key={teamInvitationLink.id} teamInvitationLink={teamInvitationLink} />)}
            <TeamInvitationLinkAddRow id={props.id} />
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

}

export default TeamInvitationLinkTable;