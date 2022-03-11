import { tTeam } from "../../../types/team";
import RealTeamTableRow from "./RealTeamTableRow";

const RealTeamTable: React.FC<{ 
    teams: tTeam[]
}> = (props) => {

    const teams = props.teams;

    return <>
        <thead>
            <tr>
                <th style={{width: "10%"}}></th>
                <th style={{width: "5%"}}>ID</th>
                <th style={{width: "70%"}}>Team Name</th>
                <th style={{width: "15%"}}>Status</th>
            </tr>
        </thead>
        <tbody>
            {teams.map(team =><RealTeamTableRow key={team.id} team={team} />)}
        </tbody>
    </>

}

export default RealTeamTable;