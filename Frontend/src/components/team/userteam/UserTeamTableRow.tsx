import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tTeamDetail } from "../../../types/team";
import TeamStateButton from "../TeamStateButton";

const UserTeamTableRow: React.FC<{ team: tTeamDetail }> = (props) => {

    const team = props.team;

    const buttons = TeamStateButton({team});

    const status = team.teamStatusHistories.filter(status => status.validTo === null)[0].teamStatusDescription;
    const linkToDetails = `${team.id}`

    return <tr key={team.id}>
        <td style={{textAlign: 'center'}}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{team.id}</td>
        <td>{team.teamName}</td>
        <td>{status}</td>
        <td style={{textAlign: 'right'}}>{buttons}</td>
    </tr>

};

export default UserTeamTableRow;