import { Table } from "react-bootstrap";
import { tBillingStatus } from "../../types/competition";

const CompetitionBillingTable: React.FC<{ competitionBillingStatus: tBillingStatus[] }> = (props) => {

    const competitionBillingStatus = props.competitionBillingStatus;

    const rows = competitionBillingStatus.map(singleCompetitionBillingStatus => {
        return <tr key={singleCompetitionBillingStatus.id}>
            <td>{singleCompetitionBillingStatus.id}</td>
            <td>{singleCompetitionBillingStatus.validFrom}</td>
            <td>{singleCompetitionBillingStatus.validTo}</td>
            <td>{singleCompetitionBillingStatus.billingStatusDescription}</td>
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