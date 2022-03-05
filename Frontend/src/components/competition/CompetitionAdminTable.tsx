import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetitionAdmin } from "../../types/competition";

const CompetitionAdminTable: React.FC<{ competitionAdmins: tCompetitionAdmin[] }> = (props) => {

    const competitionAdmins = props.competitionAdmins;

    const currentAdministrators = competitionAdmins.flatMap(competitionAdmin => {
        const linkToDetails = `/user/${competitionAdmin.userId}`
        return competitionAdmin.competitionAdminStatusHistories.map(competitionStatusHistory => {
            return <tr key={competitionStatusHistory.id}>
                <td style={{ textAlign: 'center', width: '100px' }}>
                    <Link to={linkToDetails}>
                        <Button variant="secondary" size="sm">Details</Button>
                    </Link>
                </td>
                <td>{competitionAdmin.userId}</td>
                <td>{competitionAdmin.gamerTag}</td>
                <td>{competitionStatusHistory.validFrom}</td>
                <td>{competitionStatusHistory.validTo}</td>
                <td>{competitionStatusHistory.competitionAdminStatusType}</td>
            </tr>;
        })
    });

    return <>
        <h4>Administratorenhistorie</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th></th>
                    <th>User ID</th>
                    <th>Spielername</th>
                    <th>Valide von</th>
                    <th>Valide bis</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {currentAdministrators}
            </tbody>
        </Table>
    </>

};

export default CompetitionAdminTable;