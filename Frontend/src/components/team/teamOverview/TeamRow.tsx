import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tTeam } from "../../../types/team";
import { teamHierarchy } from "../../../utility/hierarchy";

const TeamRow: React.FC<{
    team: tTeam
}> = (props) => {

    const team = props.team;
    const additionalAttributes = team.additionalAttributes ? team.additionalAttributes : []

    const linkToDetails = `${teamHierarchy.url}/${team.id}`

    const currentTeamStatus = team.teamStatus.filter(singleTeamStatus => {
        return singleTeamStatus.validTo === null;
    })[0];

    const countValidInvitationLinks = team.teamInvitationLinks.filter(teamInvitationLink => {
        return teamInvitationLink.validTo === null;
    }).length;

    return <tr>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{team.id}</td>
        <td>{team.teamName}</td>
        <td>{team.creationTime}</td>
        <td>{currentTeamStatus.teamStatusDescription}</td>
        <td>{String(team.playerTeam)}</td>
        <td>{countValidInvitationLinks}</td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default TeamRow;