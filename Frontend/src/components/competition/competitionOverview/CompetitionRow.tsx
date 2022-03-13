import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetition } from "../../../types/competition";
import { competitionHierarchy } from "../../../utility/hierarchy";

const CompetitionRow: React.FC<{
    competition: tCompetition
}> = (props) => {

    const competition = props.competition;
    const additionalAttributes = competition.additionalAttributes ? competition.additionalAttributes : []

    const linkToDetails = `${competitionHierarchy.url}/${competition.id}`

    const currentCompetitionStatus = competition.competitionStatus.filter(singleCompetitionStatus => {
        return singleCompetitionStatus.validTo === null;
    })[0];

    const countCompetitionTeams = competition.competitionTeams.length;

    return <tr>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{competition.id}</td>
        <td>{competition.competitionName}</td>
        <td>{competition.competitionStartTimestamp}</td>
        <td>{competition.minTeams}</td>
        <td>{competition.maxTeams}</td>
        <td>{currentCompetitionStatus.competitionStatusType}</td>
        <td>{countCompetitionTeams}</td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default CompetitionRow;