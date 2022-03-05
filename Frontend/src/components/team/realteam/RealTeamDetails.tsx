import { Table } from "react-bootstrap";
import { tTeamDetail } from "../../../types/team";
import TeamInvitationLinkHistoryTable from "../general/TeamInvitationLinkHistoryTable";
import TeamStatusHistoryTable from "../general/TeamStatusHistoryTable";
import TeamStateButton from "../TeamStateButton";

const RealTeamDetails: React.FC<{ team: tTeamDetail }> = (props) => {
    const team = props.team;
    

    const buttons = TeamStateButton({team});

    const status = team.teamStatusHistories.filter(teamStatusHistory =>
        teamStatusHistory.validTo === null).map(teamStatusHistory =>
            teamStatusHistory.teamStatusDescription
        )[0]

    const members = <Table striped bordered hover size="sm">
        <thead>
            <tr>
                <th>ID</th>
                <th>Vorname</th>
                <th>Nachname</th>
                <th>Spielername</th>
                <th>Teamadmin</th>
                <th>Aktiviert</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            {
                props.team.members.map(member => {
                    return <tr key={member.userId}>
                        <td>{member.userId}</td>
                        <td>{member.firstName}</td>
                        <td>{member.lastName}</td>
                        <td>{member.gamerTag}</td>
                        <td>{member.admin ? "true" : "false"}</td>
                        <td>{member.enabled ? "true" : "false"}</td>
                        <td>{member.userStatus}</td>
                    </tr>
                })
            }
        </tbody>
    </Table>

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <td>Team ID</td>
                    <td>{team.id}</td>
                </tr>
                <tr>
                    <td>Team Name</td>
                    <td>{team.teamName}</td>
                </tr>
                <tr>
                    <td>Player Team</td>
                    <td>{team.playerTeam ? "true" : "false"}</td>
                </tr>
                <tr>
                    <td>Erstellt am</td>
                    <td>{team.creationTime}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>{status}</td>
                </tr>
            </tbody>
        </Table>
        {buttons}
        <h4 style={{ marginTop: "1rem" }}>Teammitglieder</h4>
        {members}
        <TeamStatusHistoryTable teamStatusHistories={team.teamStatusHistories} />
        <TeamInvitationLinkHistoryTable teamInvitationLinkHistories={team.teamInvitationLinkHistories} />
    </>;
};

export default RealTeamDetails;