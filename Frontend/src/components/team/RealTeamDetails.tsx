import { Table } from "react-bootstrap";
import { useTeamStateButton } from "../../hooks/use-teamStateButton";
import { tTeamWithUsers } from "../../types/team";

const RealTeamDetails: React.FC<{ team: tTeamWithUsers }> = (props) => {
    const team = props.team;

    const buttons = useTeamStateButton(team);
    const status = team.teamStatusHistories.filter(teamStatusHistory => teamStatusHistory.validTo === null).map(teamStatusHistory => teamStatusHistory.teamStatusDescription)[0]

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

    const teamStatusHistory = <Table striped bordered hover size="sm">
        <thead>
            <tr>
                <th>ID</th>
                <th>Status</th>
                <th>Valid Von</th>
                <th>Valid Bis</th>
            </tr>
        </thead>
        <tbody>
            {
                props.team.teamStatusHistories.map(teamStatusHistory => {
                    return <tr key={teamStatusHistory.id}>
                        <td>{teamStatusHistory.id}</td>
                        <td>{teamStatusHistory.teamStatusDescription}</td>
                        <td>{teamStatusHistory.validFrom}</td>
                        <td>{teamStatusHistory.validTo}</td>
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
        <h4 style={{marginTop: "1rem"}}>Teammitglieder</h4>
        {members}
        <h4>Teamstatus Historie</h4>
        {teamStatusHistory}
    </>;
};

export default RealTeamDetails;