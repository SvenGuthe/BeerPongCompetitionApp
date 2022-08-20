import { Table } from "react-bootstrap";
import { tCompetitionPlayer } from "../../../../../types/competition";

const CompetitionPlayerDetailTable: React.FC<{
    competitionPlayerDetail: tCompetitionPlayer
}> = (props) => {

    const competitionPlayerDetail = props.competitionPlayerDetail;

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Competition Player ID</th>
                    <td>{competitionPlayerDetail.id}</td>
                </tr>
                <tr>
                    <th>User Name</th>
                    <td>{competitionPlayerDetail.user.gamerTag}</td>
                </tr>
                <tr>
                    <th>Hinzugefügt am</th>
                    <td>{competitionPlayerDetail.creationTime}</td>
                </tr>
            </tbody>
        </Table>
    </>;

};

export default CompetitionPlayerDetailTable;