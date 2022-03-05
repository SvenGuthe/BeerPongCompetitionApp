import { Table } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../types/team";

const TeamInvitationLinkHistoryTable: React.FC<{ teamInvitationLinkHistories: tTeamInvitationLink[] }> = (props) => {
    const teamInvitationLinkHistories = props.teamInvitationLinkHistories;

    const tableRows = teamInvitationLinkHistories.map(teamInvitationLinkHistory => {
        return <tr key={teamInvitationLinkHistory.id}>
            <td>{teamInvitationLinkHistory.id}</td>
            <td>{teamInvitationLinkHistory.teamInvitationLink}</td>
            <td>{teamInvitationLinkHistory.validFrom}</td>
            <td>{teamInvitationLinkHistory.validTo}</td>
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