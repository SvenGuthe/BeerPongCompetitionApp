import { Table } from "react-bootstrap";
import { tTeamDetail } from "../../../types/team";
import RealTeamTableRow from "./RealTeamTableRow";

const RealTeamTable: React.FC<{ 
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
                <th style={{width: "35%"}}>Team Name</th>
                <th style={{width: "10%"}}>Mitglieder</th>
                <th style={{width: "10%"}}>Admins</th>
                <th style={{width: "10%"}}>Status</th>
                <th style={{width: "20%"}}></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(team =><RealTeamTableRow key={team.id} team={team} />)}
        </tbody>
    </Table>

}

export default RealTeamTable;