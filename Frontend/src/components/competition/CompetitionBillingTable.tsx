import { Table } from "react-bootstrap";
import { tBillingStatusHistory } from "../../types/competition";

const CompetitionBillingTable: React.FC<{ competitionBillingHistories: tBillingStatusHistory[] }> = (props) => {

    const competitionBillingHistories = props.competitionBillingHistories;

    const rows = competitionBillingHistories.map(competitionBillingHistory => {
        return <tr key={competitionBillingHistory.id}>
            <td>{competitionBillingHistory.id}</td>
            <td>{competitionBillingHistory.validFrom}</td>
            <td>{competitionBillingHistory.validTo}</td>
            <td>{competitionBillingHistory.billingStatusType}</td>
        </tr>
    })

    return <>
        <h6 style={{marginTop: "1rem"}}>Bezahlhistorie</h6>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Valide von</th>
                    <th>Valide bis</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </Table>
    </>;

};

export default CompetitionBillingTable;