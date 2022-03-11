import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetitionAdmin } from "../../types/competition";

const CompetitionAdminTable: React.FC<{ competitionAdmins: tCompetitionAdmin[] }> = (props) => {

    const competitionAdmins = props.competitionAdmins;

    const currentAdministrators = competitionAdmins.flatMap(competitionAdmin => {
        const linkToDetails = `/user/${competitionAdmin.user.id}`
        return competitionAdmin.competitionAdminStatus.map(singleCompetitionAdminStatus => {
            return <tr key={singleCompetitionAdminStatus.id}>
                <td style={{ textAlign: 'center', width: '100px' }}>
                    <Link to={linkToDetails}>
                        <Button variant="secondary" size="sm">Details</Button>
                    </Link>
                </td>
                <td>{competitionAdmin.user.id}</td>
                <td>{competitionAdmin.user.gamerTag}</td>
                <td>{singleCompetitionAdminStatus.validFrom}</td>
                <td>{singleCompetitionAdminStatus.validTo}</td>
                <td>{singleCompetitionAdminStatus.competitionAdminStatusDescription}</td>
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