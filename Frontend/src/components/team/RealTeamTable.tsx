import { Table } from "react-bootstrap";
import { tTeamWithUsers } from "../../types/team";
import RealTeamTableRow from "./RealTeamTableRow";

const RealTeamTable: React.FC<{ 
    teams: tTeamWithUsers[]
}> = (props) => {

    const teams = props.teams;


    return <Table striped bordered hover size="sm" style={{
        marginBottom: '3rem'
    }}>
        <thead>
            <tr>
                <th>ID</th>
                <th>Team Name</th>
                <th>Mitglieder</th>
                <th>Admins</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(team =><RealTeamTableRow key={team.id} team={team} />)}
        </tbody>
    </Table>

}

export default RealTeamTable;