import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tUser } from "../../../types/user";

/**
 * Component which includes a single row of the user list
 * @param props the user which should be displayed in the row
 * @returns JSX of the row with the important information about the team
 */
const UserRow: React.FC<{ user: tUser }> = (props) => {
  // get the user from the props
  const user = props.user;

  // if additional attributes are set, add them here to the table
  // mostly elements to edit values
  const additionalAttributes = user.additionalAttributes
    ? user.additionalAttributes
    : [];

  // create the link to the detail page of the user
  const linkToDetails = `/user/${user.id}`;

  return (
    <tr>
      <td style={{ textAlign: "center" }}>
        <Link to={linkToDetails}>
          <Button variant="secondary" size="sm">
            Details
          </Button>
        </Link>
      </td>
      <td>{user.id}</td>
      <td>{user.gamerTag}</td>
      <td>{user.enabled ? "true" : "false"}</td>
      <td>
        {
          user.userStatus.filter((status) => status.validTo === null)[0]
            .userStatus
        }
      </td>
      {additionalAttributes.map((additionalAttribute) => {
        return additionalAttribute.reactElement ? (
          <td key={additionalAttribute.id}>
            {additionalAttribute.reactElement}
          </td>
        ) : (
          <td key={additionalAttribute.id}>{additionalAttribute.value}</td>
        );
      })}
    </tr>
  );
};

export default UserRow;
