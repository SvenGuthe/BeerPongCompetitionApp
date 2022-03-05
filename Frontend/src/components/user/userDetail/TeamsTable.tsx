import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tUserDetail } from "../../../types/user";

const TeamsTable: React.FC<{ user: tUserDetail }> = (props) => {

    const user = props.user;

    const rows = user.teams.map(team => {

        const linkToDetails = `/team/${team.id}`

        return <tr key={team.id}>
            <td style={{ textAlign: 'center' }}>
                <Link to={linkToDetails}>
                    <Button variant="secondary" size="sm">Details</Button>
                </Link>
            </td>
            <td>{team.id}</td>
            <td>{team.teamName}</td>
            <td>{team.admin ? "TRUE" : "FALSE"}</td>
            <td>{team.creationTime}</td>
            <td>{team.playerTeam ? "TRUE" : "FALSE"}</td>
        </tr>
    })

    return <>
        <h4>Teams</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Team Name</th>
                    <th>Admin</th>
                    <th>Angelegt am</th>
                    <th>Spielerteam</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </Table>
    </>;

}

export default TeamsTable;