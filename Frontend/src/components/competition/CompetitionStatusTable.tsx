import { Table } from "react-bootstrap";
import { tCompetitionStatusHistory } from "../../types/competition";

const CompetitionStatusTable: React.FC<{ competitionStatusHistories: tCompetitionStatusHistory[] }> = (props) => {

    const competitionStatusHistories = props.competitionStatusHistories;

    const status = competitionStatusHistories.map(competitionStatusHistory => {
        return <tr key={competitionStatusHistory.id}>
            <td>{competitionStatusHistory.validFrom}</td>
            <td>{competitionStatusHistory.validTo}</td>
            <td>{competitionStatusHistory.competitionStatusType}</td>
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