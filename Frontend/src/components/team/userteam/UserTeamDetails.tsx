import { Table } from "react-bootstrap";
import { tTeamDetail } from "../../../types/team";
import TeamStatusHistoryTable from "../general/TeamStatusHistoryTable";
import TeamStateButton from "../TeamStateButton";

const UserTeamDetails: React.FC<{ team: tTeamDetail }> = (props) => {

    const team = props.team;

    const buttons = TeamStateButton({team});
    const status = team.teamStatusHistories.filter(teamStatusHistory => teamStatusHistory.validTo === null).map(teamStatusHistory => teamStatusHistory.teamStatusDescription)[0]

    const members = <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <th>ID</th>
                <td>{team.members[0].userId}</td>
            </tr>
            <tr>
                <th>Vorname</th>
                <td>{team.members[0].firstName}</td>
            </tr>
            <tr>
                <th>Nachname</th>
                <td>{team.members[0].lastName}</td>
            </tr>
            <tr>
                <th>Spielername</th>
                <td>{team.members[0].gamerTag}</td>
            </tr>
            <tr>
                <th>Aktiviert</th>
                <td>{team.members[0].enabled ? "true" : "false"}</td>
            </tr>
            <tr>
                <th>Status</th>
                <td>{team.members[0].userStatus}</td>
            </tr>
        </tbody>
    </Table>

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Team ID</th>
                    <td>{team.id}</td>
                </tr>
                <tr>
                    <th>Team Name</th>
                    <td>{team.teamName}</td>
                </tr>
                <tr>
                    <th>Player Team</th>
                    <td>{team.playerTeam ? "true" : "false"}</td>
                </tr>
                <tr>
                    <th>Erstellt am</th>
                    <td>{team.creationTime}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>{status}</td>
                </tr>
            </tbody>
        </Table>
        {buttons}
        <h4 style={{marginTop: "1rem"}}>Userdaten</h4>
        {members}
        <TeamStatusHistoryTable teamStatusHistories={props.team.teamStatusHistories} />
    </>;

};

export default UserTeamDetails;