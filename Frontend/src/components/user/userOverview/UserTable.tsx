import { Table } from "react-bootstrap";
import { tUser } from "../../../types/user";
import UserRow from "./UserRow";

/**
 * Component which displays the table of all users
 * @param props all users
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 * @returns JSX with the table of users
 */
const UserTable: React.FC<{
  users: tUser[];
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
}> = (props) => {
  const users = props.users;
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
          <th>Spielername</th>
          <th>Freigeschalten</th>
          <th>User Status</th>
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
        {users.map((user) => (
          <UserRow key={user.id} user={user} />
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

export default UserTable;
