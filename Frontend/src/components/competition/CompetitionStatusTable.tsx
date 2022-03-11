import { Table } from "react-bootstrap";
import { tCompetitionStatus } from "../../types/competition";

const CompetitionStatusTable: React.FC<{ competitionStatus: tCompetitionStatus[] }> = (props) => {

    const competitionStatus = props.competitionStatus;

    const status = competitionStatus.map(singleCompetitionStatus => {
        return <tr key={singleCompetitionStatus.id}>
            <td>{singleCompetitionStatus.validFrom}</td>
            <td>{singleCompetitionStatus.validTo}</td>
            <td>{singleCompetitionStatus.competitionStatusType}</td>
        </tr>
    })

    return <>
        <h4>Turnierstatushistorie</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>Valide von</th>
                    <th>Valide bis</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                { status }
            </tbody>
        </Table>
    </>;

}

export default CompetitionStatusTable;