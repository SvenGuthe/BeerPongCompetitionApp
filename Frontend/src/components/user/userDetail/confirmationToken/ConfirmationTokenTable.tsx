import { Table } from "react-bootstrap";
import tConfirmationToken from "../../../../types/user/confirmationtoken/confirmationToken";
import ConfirmationTokenAddRow from "./ConfirmationTokenAddRow";
import ConfirmationTokenRow from "./ConfirmationTokenRow";

/**
 * Component to show all confirmation token and add new ones for the user
 * @param props the id of the user
 *              current available confirmation token from the database
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 * @returns JSX to display all available confirmation token and add new ones
 */
const ConfirmationTokenTable: React.FC<{
  id: number;
  confirmationToken: tConfirmationToken[];
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
}> = (props) => {
  const confirmationToken = props.confirmationToken;
  const wrapped = props.wrapped ? props.wrapped : false;
  const additionalAttributesHeader = props.additionalAttributesHeader
    ? props.additionalAttributesHeader
    : [];

  const input = (
    <>
      <thead>
        <tr>
          <th>ID</th>
          <th>Erstellt am</th>
          <th>Valide bis</th>
          <th>Token</th>
          <th></th>
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
        {confirmationToken.map((confirmationToken) => (
          <ConfirmationTokenRow
            key={confirmationToken.id}
            confirmationToken={confirmationToken}
          />
        ))}
        <ConfirmationTokenAddRow id={props.id} />
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

export default ConfirmationTokenTable;
