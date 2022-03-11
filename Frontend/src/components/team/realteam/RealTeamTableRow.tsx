import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tTeam } from "../../../types/team";

const RealTeamTableRow: React.FC<{ team: tTeam }> = (props) => {

    const team = props.team;
    
    const status = team.teamStatus.filter(status => status.validTo === null)[0].teamStatusDescription;
    const linkToDetails = `${team.id}`

    return <tr>
        <td style={{textAlign: 'center'}}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{team.id}</td>
        <td>{team.teamName}</td>
        <td>{status}</td>
    </tr>

};

export default RealTeamTableRow;