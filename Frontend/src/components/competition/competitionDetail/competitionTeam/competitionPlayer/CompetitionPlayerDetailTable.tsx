import { Table } from "react-bootstrap";
import { tCompetitionPlayer } from "../../../../../types/competition";

/**
 * Component to show the meta data of a single competition player
 * @param props the competition player info (important user and creation time)
 * @returns JSX with a table filled with meta data of a single competition player
 */
const CompetitionPlayerDetailTable: React.FC<{
  competitionPlayerDetail: tCompetitionPlayer;
}> = (props) => {
  // get the information of the competition player from the props
  const competitionPlayerDetail = props.competitionPlayerDetail;

  return (
    <>
      <Table striped bordered hover size="sm">
        <tbody>
          <tr>
            <th>Competition Player ID</th>
            <td>{competitionPlayerDetail.id}</td>
          </tr>
          <tr>
            <th>User Name</th>
            <td>{competitionPlayerDetail.user.gamerTag}</td>
          </tr>
          <tr>
            <th>Hinzugefügt am</th>
            <td>{competitionPlayerDetail.creationTime}</td>
          </tr>
        </tbody>
      </Table>
    </>
  );
};

export default CompetitionPlayerDetailTable;
