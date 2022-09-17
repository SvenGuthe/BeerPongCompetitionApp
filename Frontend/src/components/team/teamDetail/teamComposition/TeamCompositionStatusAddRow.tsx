import { useDispatch } from "react-redux";
import { updateTeamCompositionStatus } from "../../../../store/team/team-store-actions";
import { tTeamCompositionStatusType } from "../../../../types/enums/teamCompositionStatusType";
import tTeamCompositionStatusUpdate from "../../../../types/team/teamcomposition/teamCompositionStatusUpdate";
import { TeamCompositionStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new team composition status
 * @param props the id of the team composition
 * @returns JSX with a single table row (4 columns) and the possibility (select input) to choose a new status for the team composition
 */
const TeamCompositionStatusAddRow: React.FC<{
  teamCompositionId: number;
}> = (props) => {
  // get the team composition id from the props
  const teamCompositionId = props.teamCompositionId;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewTeamCompositionStatusType = (newValue: string[]) => {
    const newStatus = newValue[0] as tTeamCompositionStatusType;

    // Create the DTO with the team composition id and the selected team composition status
    const teamCompositionStatus: tTeamCompositionStatusUpdate = {
      id: teamCompositionId,
      teamCompositionStatusType: newStatus,
    };

    // Send a PUT request to the team composition status route to set the new team composition status
    dispatch(updateTeamCompositionStatus(teamCompositionStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Team Composition Status setzen:
      </td>
      <td colSpan={2}>
        <TeamCompositionStatusTypeInput
          saveValue={(newValue, changed) =>
            onSaveNewTeamCompositionStatusType(newValue as string[])
          }
          add
        />
      </td>
    </tr>
  );
};

export default TeamCompositionStatusAddRow;
