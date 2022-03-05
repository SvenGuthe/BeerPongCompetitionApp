import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetitionDetail } from "../../types/competition";

const CompetitionRow: React.FC<{ competition: tCompetitionDetail }> = (props) => {

    const competition = props.competition;

    const linkToDetails = `/competition/${competition.id}`

    return <tr>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{competition.id}</td>
        <td>{competition.competitionName}</td>
        <td>{competition.competitionStartTimestamp}</td>
        <td>{competition.fee.toFixed(2)} €</td>
        <td>{competition.competitionTeams.length}</td>
        <td>{competition.minTeams}</td>
        <td>{competition.maxTeams}</td>
        <td>{competition.competitionStatusHistories.find(
            competitionStatusHistory => competitionStatusHistory.validTo === null
        )?.competitionStatusType}</td>
    </tr>

};

export default CompetitionRow;