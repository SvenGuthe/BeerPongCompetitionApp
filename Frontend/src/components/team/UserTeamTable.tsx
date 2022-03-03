import { Table } from "react-bootstrap";
import { tTeamWithUsers } from "../../types/team";
import UserTeamTableRow from "./UserTeamTableRow";

const UserTeamTable: React.FC<{
    teams: tTeamWithUsers[]
}> = (props) => {

    const teams = props.teams;

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
            {teams.map(player => <UserTeamTableRow key={player.id} team={player} />)}
        </tbody>
    </Table>

}

export default UserTeamTable;