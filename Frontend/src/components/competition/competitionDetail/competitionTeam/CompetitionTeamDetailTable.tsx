import { Table } from "react-bootstrap";
import { tCompetitionTeam } from "../../../../types/competition";

const CompetitionTeamDetailTable: React.FC<{
    competitionTeamDetail: tCompetitionTeam
}> = (props) => {

    const competitionTeamDetail = props.competitionTeamDetail;

    return <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <th>Competition Team ID</th>
                <td>{competitionTeamDetail.id}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td>{competitionTeamDetail.competitionTeamName}</td>
            </tr>
            <tr>
                <th>Hauptteam</th>
                <td>{competitionTeamDetail.team && competitionTeamDetail.team.teamName}</td>
            </tr>
            <tr>
                <th>Hinzugefügt am</th>
                <td>{competitionTeamDetail.creationTime}</td>
            </tr>
        </tbody>
    </Table>;

};

export default CompetitionTeamDetailTable;