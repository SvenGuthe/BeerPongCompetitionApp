import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useTeamStateButton } from "../../hooks/use-teamStateButton";
import { tTeamWithUsers } from "../../types/team";

const RealTeamTableRow: React.FC<{ team: tTeamWithUsers }> = (props) => {

    const team = props.team;
    
    const buttons = useTeamStateButton(team);

    const status = team.teamStatusHistories.filter(status => status.validTo === null)[0].teamStatusDescription;
    const linkToDetails = `${team.id}`

    return <tr>
        <td>{team.id}</td>
        <td>{team.teamName}</td>
        <td>{team.members.length}</td>
        <td>{team.members.filter(member => member.admin).length}</td>
        <td>{status}</td>
        <td>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{buttons}</td>
    </tr>

};

export default RealTeamTableRow;