import { Table } from "react-bootstrap";
import { tRegistrationStatusHistory } from "../../types/competition";
const CompetitionRegistrationTable: React.FC<{ competitionRegistrationHistories: tRegistrationStatusHistory[] }> = (props) => {

    const competitionRegistrationHistories = props.competitionRegistrationHistories;

    const rows = competitionRegistrationHistories.map(competitionRegistrationHistory => {
        return <tr key={competitionRegistrationHistory.id}>
            <td>{competitionRegistrationHistory.id}</td>
            <td>{competitionRegistrationHistory.validFrom}</td>
            <td>{competitionRegistrationHistory.validTo}</td>
            <td>{competitionRegistrationHistory.registrationStatusType}</td>
        </tr>
    })

    return <>
        <h6 style={{marginTop: "1rem"}}>Registrationshistorie</h6>
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

export default CompetitionRegistrationTable;