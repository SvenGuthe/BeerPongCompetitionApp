import { Table } from "react-bootstrap";
import { useTeamStateButton } from "../../hooks/use-teamStateButton";
import { tTeamWithUsers } from "../../types/team";

const UserTeamDetails: React.FC<{ team: tTeamWithUsers }> = (props) => {

    const team = props.team;

    const buttons = useTeamStateButton(team);
    const status = team.teamStatusHistories.filter(teamStatusHistory => teamStatusHistory.validTo === null).map(teamStatusHistory => teamStatusHistory.teamStatusDescription)[0]

    const members = <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <td>ID</td>
                <td>{team.members[0].userId}</td>
            </tr>
            <tr>
                <td>Vorname</td>
                <td>{team.members[0].firstName}</td>
            </tr>
            <tr>
                <td>Nachname</td>
                <td>{team.members[0].lastName}</td>
            </tr>
            <tr>
                <td>Spielername</td>
                <td>{team.members[0].gamerTag}</td>
            </tr>
            <tr>
                <td>Aktiviert</td>
                <td>{team.members[0].enabled ? "true" : "false"}</td>
            </tr>
            <tr>
                <td>Status</td>
                <td>{team.members[0].userStatus}</td>
            </tr>
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
        <h4 style={{marginTop: "1rem"}}>Userdaten</h4>
        {members}
        <h4>Teamstatus Historie</h4>
        {teamStatusHistory}
    </>;

};

export default UserTeamDetails;