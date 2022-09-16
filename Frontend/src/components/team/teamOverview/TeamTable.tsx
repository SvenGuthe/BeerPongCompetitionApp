import { Table } from "react-bootstrap";
import { tTeam } from "../../../types/team";
import TeamRow from "./TeamRow";

/**
 * Component which displays the table of all teams
 * @param props all teams
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 * @returns JSX with the table of teams
 */
const TeamTable: React.FC<{
  teams: tTeam[];
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
}> = (props) => {
  const teams = props.teams;
  const wrapped = props.wrapped ? props.wrapped : false;
  const additionalAttributesHeader = props.additionalAttributesHeader
    ? props.additionalAttributesHeader
    : [];

  const input = (
    <>
      <thead>
        <tr>
          <th></th>
          <th>ID</th>
          <th>Name</th>
          <th>Erstellt am</th>
          <th>Status</th>
          <th>Player</th>
          <th>Valide Einladungslinks</th>
          {additionalAttributesHeader.map(
            (singleAdditionalAttributesHeader) => {
              return (
                <th key={singleAdditionalAttributesHeader}>
                  {singleAdditionalAttributesHeader}
                </th>
              );
            }
          )}
        </tr>
      </thead>
      <tbody>
        {teams.map((team) => (
          <TeamRow key={team.id} team={team} />
        ))}
      </tbody>
    </>
  );

  const wrappedInput = wrapped ? (
    <Table striped bordered hover size="sm">
      {input}
    </Table>
  ) : (
    input
  );

  return wrappedInput;
};

export default TeamTable;
