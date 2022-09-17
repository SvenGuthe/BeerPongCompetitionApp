import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import tTeam from "../../../types/team/team";
import { teamHierarchy } from "../../../utility/hierarchy";

/**
 * Component which includes a single row of the team list
 * @param props the team which should be displayed in the row
 * @returns JSX of the row with the important information about the team
 */
const TeamRow: React.FC<{
  team: tTeam;
}> = (props) => {
  // get the team from the props
  const team = props.team;

  // if additional attributes are set, add them here to the table
  // mostly elements to edit values
  const additionalAttributes = team.additionalAttributes
    ? team.additionalAttributes
    : [];

  // create the link to the detail page of the team
  const linkToDetails = `${teamHierarchy.url}/${team.id}`;

  // select the current team status to display it
  const currentTeamStatus = team.teamStatus.filter((singleTeamStatus) => {
    return singleTeamStatus.validTo === null;
  })[0];

  // get the amount of valid team invitation links to display it
  const countValidInvitationLinks = team.teamInvitationLinks.filter(
    (teamInvitationLink) => {
      return teamInvitationLink.validTo === null;
    }
  ).length;

  return (
    <tr>
      <td style={{ textAlign: "center" }}>
        <Link to={linkToDetails}>
          <Button variant="secondary" size="sm">
            Details
          </Button>
        </Link>
      </td>
      <td>{team.id}</td>
      <td>{team.teamName}</td>
      <td>{team.creationTime}</td>
      <td>{currentTeamStatus.teamStatusDescription}</td>
      <td>{String(team.isPlayerTeam)}</td>
      <td>{countValidInvitationLinks}</td>
      {additionalAttributes.map((additionalAttribute) => {
        return additionalAttribute.reactElement ? (
          <td key={additionalAttribute.id}>
            {additionalAttribute.reactElement}
          </td>
        ) : (
          <td key={additionalAttribute.id}>{additionalAttribute.value}</td>
        );
      })}
    </tr>
  );
};

export default TeamRow;
