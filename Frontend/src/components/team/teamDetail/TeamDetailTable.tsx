import { Table } from "react-bootstrap";
import { tTeam } from "../../../types/team";
import FormItem from "../../ui/form/FormItem";

const TeamDetailTable: React.FC<{
    team: tTeam
}> = (props) => {

    const team = props.team;

    return <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <th>ID</th>
                <td>{team.id}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td><FormItem defaultValue={team.teamName} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Player Team</th>
                <td>{String(team.playerTeam)}</td>
            </tr>
            <tr>
                <th>Erzeugt am</th>
                <td>{team.creationTime}</td>
            </tr>
        </tbody>
    </Table>;

}

export default TeamDetailTable;