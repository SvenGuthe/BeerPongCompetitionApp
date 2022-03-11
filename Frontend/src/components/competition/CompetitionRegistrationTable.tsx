import { Table } from "react-bootstrap";
import { tRegistrationStatus } from "../../types/competition";

const CompetitionRegistrationTable: React.FC<{ competitionRegistrationStatus: tRegistrationStatus[] }> = (props) => {

    const competitionRegistrationStatus = props.competitionRegistrationStatus;

    const rows = competitionRegistrationStatus.map(singleCompetitionRegistrationStatus => {
        return <tr key={singleCompetitionRegistrationStatus.id}>
            <td>{singleCompetitionRegistrationStatus.id}</td>
            <td>{singleCompetitionRegistrationStatus.validFrom}</td>
            <td>{singleCompetitionRegistrationStatus.validTo}</td>
            <td>{singleCompetitionRegistrationStatus.registrationStatusDescription}</td>
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