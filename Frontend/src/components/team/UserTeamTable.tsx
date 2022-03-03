import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { TeamStatus } from "../../types/enums/teamStatus";
import { tTeamWithUsers } from "../../types/team";

const UserTeamTable: React.FC<{
    teams: tTeamWithUsers[],
    createButtons: (team: tTeamWithUsers, status: TeamStatus) => JSX.Element | undefined
}> = (props) => {

    const teams = props.teams;
    const createButtons = props.createButtons;

    return <Table striped bordered hover size="sm" style={{
        marginBottom: '3rem'
    }}>
        <thead>
            <tr>
                <th>ID</th>
                <th>User Name</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(player => {

                const status = player.teamStatusHistories.filter(status => status.validTo === null)[0].teamStatusDescription;
                let button = createButtons(player, status);
                const linkToDetails = `${player.id}`

                return <tr key={player.id}>
                    <td>{player.id}</td>
                    <td>{player.teamName}</td>
                    <td>{status}</td>
                    <td>
                        <Link to={linkToDetails}>
                            <Button variant="secondary" size="sm">Details</Button>
                        </Link>
                    </td>
                    <td>{button}</td>
                </tr>
            })}
        </tbody>
    </Table>

}

export default UserTeamTable;