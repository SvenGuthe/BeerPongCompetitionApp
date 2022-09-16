import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetition } from "../../../types/competition";
import { competitionHierarchy } from "../../../utility/hierarchy";

/**
 * Component which includes a single row of the competition list
 * @param props the competition which should be displayed in the row
 * @returns JSX of the row with the important information about the competition
 */
const CompetitionRow: React.FC<{
  competition: tCompetition;
}> = (props) => {
  // get the competition from the props
  const competition = props.competition;

  // if additional attributes are set, add them here to the table
  // mostly elements to edit values
  const additionalAttributes = competition.additionalAttributes
    ? competition.additionalAttributes
    : [];

  // create the link to the detail page of the competition
  const linkToDetails = `${competitionHierarchy.url}/${competition.id}`;

  // select the current competition status to display it
  const currentCompetitionStatus = competition.competitionStatus.filter(
    (singleCompetitionStatus) => {
      return singleCompetitionStatus.validTo === null;
    }
  )[0];

  // get the amount of competition teams to display it
  const countCompetitionTeams = competition.competitionTeams.length;

  return (
    <tr>
      <td style={{ textAlign: "center" }}>
        <Link to={linkToDetails}>
          <Button variant="secondary" size="sm">
            Details
          </Button>
        </Link>
      </td>
      <td>{competition.id}</td>
      <td>{competition.competitionName}</td>
      <td>{competition.competitionStartTimestamp}</td>
      <td>{competition.minTeams}</td>
      <td>{competition.maxTeams}</td>
      <td>{currentCompetitionStatus.competitionStatusType}</td>
      <td>{countCompetitionTeams}</td>
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

export default CompetitionRow;
