import { useRef } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateTeamComposition } from "../../../store/team/team-store-actions";
import { tTeamCompositionUpdate } from "../../../types/team";
import { tUser } from "../../../types/user";
import CheckboxInput from "../../ui/form/CheckboxInput";

/**
 * Component to show the Teams of a single user
 * @param props the current user
 *              a flag if the user is an admin of the team
 *              the id of the team composition
 * @returns the table with the information of the user in the team
 */
const UserDetailsTableTeam: React.FC<{
  user: tUser;
  admin: boolean;
  teamCompositionId: number;
}> = (props) => {
  const inputRef = useRef<HTMLInputElement>(null);

  const user = props.user;
  const admin = props.admin;
  const teamCompositionId = props.teamCompositionId;

  const dispatch = useDispatch();

  // Handler to change the meta data of the team composition
  const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();
    const newIsAdmin = inputRef.current!.checked;

    // create the DTO to change the team composition
    const teamCompositionUpdate: tTeamCompositionUpdate = {
      id: teamCompositionId,
      isAdmin: newIsAdmin,
    };

    // send a PUT request to the team composition route to update the metadata of the team composition
    dispatch(updateTeamComposition(teamCompositionUpdate));
  };

  return (
    <>
      <Table striped bordered hover size="sm">
        <tbody>
          <tr>
            <th>User ID</th>
            <td>{user.id}</td>
          </tr>
          <tr>
            <th>Vorname</th>
            <td>{user.firstName}</td>
          </tr>
          <tr>
            <th>Nachname</th>
            <td>{user.lastName}</td>
          </tr>
          <tr>
            <th>Spielername</th>
            <td>{user.gamerTag}</td>
          </tr>
          <tr>
            <th>Admin im Team</th>
            <td>
              <CheckboxInput disabled={false} value={admin} ref={inputRef} />
            </td>
          </tr>
        </tbody>
      </Table>
      <div style={{ textAlign: "right" }}>
        <Button
          variant="secondary"
          size="sm"
          style={{ width: "200px" }}
          onClick={onSaveHandler}
        >
          Save Data
        </Button>
      </div>
    </>
  );
};

export default UserDetailsTableTeam;
