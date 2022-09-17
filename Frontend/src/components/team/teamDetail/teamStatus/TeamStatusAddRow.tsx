import { useDispatch } from "react-redux";
import { updateTeamStatus } from "../../../../store/team/team-store-actions";
import { tTeamStatusType } from "../../../../types/enums/teamStatusType";
import tTeamStatusUpdate from "../../../../types/team/teamstatus/teamStatusUpdate";
import { TeamStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new team status
 * @param props the id of the team
 * @returns JSX with a single table row (4 columns) and the possibility (select input) to choose a new status for the team
 */
const TeamStatusAddRow: React.FC<{
  teamId: number;
}> = (props) => {
  // get the team id from the props
  const teamId = props.teamId;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewTeamStatusType = (newValue: string[]) => {
    const newStatus = newValue[0] as tTeamStatusType;

    // Create the DTO with the team id and the selected team status
    const teamStatus: tTeamStatusUpdate = {
      id: teamId,
      teamStatusType: newStatus,
    };

    // Send a PUT request to the team status route to set the new team status
    dispatch(updateTeamStatus(teamStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Team Status setzen:
      </td>
      <td colSpan={2}>
        <TeamStatusTypeInput
          saveValue={(newValue, changed) =>
            onSaveNewTeamStatusType(newValue as string[])
          }
          add
        />
      </td>
    </tr>
  );
};

export default TeamStatusAddRow;
