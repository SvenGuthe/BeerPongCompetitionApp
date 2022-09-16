import { Table } from "react-bootstrap";
import { tCompetition } from "../../../types/competition";
import CompetitionRow from "./CompetitionRow";

/**
 * Component which displays the table of all competitions
 * @param props all competitions
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 * @returns JSX with the table of competitions
 */
const CompetitionTable: React.FC<{
  competitions: tCompetition[];
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
}> = (props) => {
  const competitions = props.competitions;
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
          <th>Start</th>
          <th>Min. Teams</th>
          <th>Max. Teams</th>
          <th>Status</th>
          <th>#Teams aktuell</th>
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
        {competitions.map((competition) => (
          <CompetitionRow key={competition.id} competition={competition} />
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

export default CompetitionTable;
