import { Table } from "react-bootstrap";
import { tCompetitionAdmin } from "../../../../types/competition";

/**
 * Component to show the meta data of a single competition admin
 * @param props the competition admin info (important user and creation time)
 * @returns JSX with a table filled with meta data of a single competition admin
 */
const CompetitionAdminDetailTable: React.FC<{
  competitionAdminDetail: tCompetitionAdmin;
}> = (props) => {
  // get the information of the competition admin from the props
  const competitionAdminDetail = props.competitionAdminDetail;

  return (
    <>
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
    </>
  );
};

export default CompetitionAdminDetailTable;
