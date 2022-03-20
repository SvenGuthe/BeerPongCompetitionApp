import { Table } from "react-bootstrap";
import { tCompetitionAdmin } from "../../../../types/competition";

const CompetitionAdminDetailTable: React.FC<{
    competitionAdminDetail: tCompetitionAdmin;
}> = (props) => {

    const competitionAdminDetail = props.competitionAdminDetail;

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Competition Admin ID</th>
                    <td>{competitionAdminDetail.id}</td>
                </tr>
                <tr>
                    <th>User Name</th>
                    <td>{competitionAdminDetail.user.gamerTag}</td>
                </tr>
                <tr>
                    <th>Hinzugefügt am</th>
                    <td>{competitionAdminDetail.creationTime}</td>
                </tr>
            </tbody>
        </Table>
    </>;

};

export default CompetitionAdminDetailTable;