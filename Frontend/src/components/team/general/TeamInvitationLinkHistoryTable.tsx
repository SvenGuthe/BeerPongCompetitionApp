import { Table } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../types/team";
const TeamInvitationLinkHistoryTable: React.FC<{ teamInvitationLinks: tTeamInvitationLink[] }> = (props) => {
    const teamInvitationLinks = props.teamInvitationLinks;

    const tableRows = teamInvitationLinks.map(teamInvitationLink => {
        return <tr key={teamInvitationLink.id}>
            <td>{teamInvitationLink.id}</td>
            <td>{teamInvitationLink.teamInvitationLink}</td>
            <td>{teamInvitationLink.validFrom}</td>
            <td>{teamInvitationLink.validTo}</td>
        </tr>
    })

    return <>
        <h4 style={{ marginTop: "1rem" }}>Einladungslinks Historie</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Einladungslink</th>
                    <th>Valid Von</th>
                    <th>Valid Bis</th>
                </tr>
            </thead>
            <tbody>
                {tableRows}
            </tbody>
        </Table>
    </>;
};

export default TeamInvitationLinkHistoryTable;