import { Table } from "react-bootstrap";
import { useTeamStateButton } from "../../../hooks/use-teamStateButton";
import { tTeamDetail } from "../../../types/team";
import TeamStatusHistoryTable from "../general/TeamStatusHistoryTable";

const UserTeamDetails: React.FC<{ team: tTeamDetail }> = (props) => {

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
        <TeamStatusHistoryTable teamStatusHistories={props.team.teamStatusHistories} />
    </>;

};

export default UserTeamDetails;