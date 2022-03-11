import { Table } from "react-bootstrap";
import { tTeam } from "../../../types/team";
import RealTeamTableRow from "./RealTeamTableRow";

const RealTeamTable: React.FC<{ 
    teams: tTeam[]
}> = (props) => {

    const teams = props.teams;


    return <Table striped bordered hover size="sm" style={{
        marginBottom: '3rem'
    }}>
        <thead>
            <tr>
                <th style={{width: "15%"}}></th>
                <th style={{width: "5%"}}>ID</th>
                <th style={{width: "40%"}}>Team Name</th>
                <th style={{width: "15%"}}>Status</th>
                <th style={{width: "25%"}}></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(team =><RealTeamTableRow key={team.id} team={team} />)}
        </tbody>
    </Table>

}

export default RealTeamTable;