import { Table } from "react-bootstrap";
import { tTeamDetail } from "../../../types/team";
import UserTeamTableRow from "./UserTeamTableRow";

const UserTeamTable: React.FC<{
    teams: tTeamDetail[]
}> = (props) => {

    const teams = props.teams;

    return <Table striped bordered hover size="sm" style={{
        marginBottom: '3rem'
    }}>
        <thead>
            <tr>
                <th style={{width: "10%"}}></th>
                <th style={{width: "5%"}}>ID</th>
                <th style={{width: "55%"}}>User Name</th>
                <th style={{width: "10%"}}>Status</th>
                <th style={{width: "20%"}}></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(player => <UserTeamTableRow key={player.id} team={player} />)}
        </tbody>
    </Table>

}

export default UserTeamTable;