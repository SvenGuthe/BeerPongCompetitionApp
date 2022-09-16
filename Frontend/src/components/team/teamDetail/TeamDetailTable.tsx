import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateTeam } from "../../../store/team/team-store-actions";
import { tTeam } from "../../../types/team";
import FormItem from "../../ui/form/FormItem";

/**
 * Component to show meta data of a single team
 * @param props team which should be displayed
 * @returns JSX with a table filled with meta data of a single team
 */
const TeamDetailTable: React.FC<{
  team: tTeam;
}> = (props) => {
  // get the team from the props
  const team = props.team;

  const dispatch = useDispatch();

  const [isChanged, setIsChanged] = useState<boolean>(false);

  const teamNameRef = useRef<HTMLInputElement>(null);

  // Handler to change the meta data of the competition
  const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    // just do something, if there was a change
    if (isChanged) {
      // create the DTO to change the team info
      const newMetaData = {
        id: team.id,
        teamName: teamNameRef.current!.value,
      };

      // send a PUT request to the team route to update the metadata of the team
      dispatch(updateTeam(newMetaData));

      // set the flag if something was changed back to false
      setIsChanged(false);
    }
  };

  return (
    <>
      <Table striped bordered hover size="sm">
        <tbody>
          <tr>
            <th>ID</th>
            <td>{team.id}</td>
          </tr>
          <tr>
            <th>Name</th>
            <td>
              <FormItem
                ref={teamNameRef}
                defaultValue={team.teamName}
                saveValue={(newValue, changed) =>
                  changed && setIsChanged(changed)
                }
              />
            </td>
          </tr>
          <tr>
            <th>Player Team</th>
            <td>{String(team.playerTeam)}</td>
          </tr>
          <tr>
            <th>Erzeugt am</th>
            <td>{team.creationTime}</td>
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
          Save Meta Data
        </Button>
      </div>
    </>
  );
};

export default TeamDetailTable;
