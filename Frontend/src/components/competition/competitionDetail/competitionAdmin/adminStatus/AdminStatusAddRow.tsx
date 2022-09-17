import { useDispatch } from "react-redux";
import { changeCompetitionAdminStatus } from "../../../../../store/competition/competition-store-actions";
import tCompetitionAdminStatusUpdate from "../../../../../types/competition/competitionadmin/competitionAdminStatusUpdate";
import { tCompetitionAdminStatusType } from "../../../../../types/enums/competitionAdminStatusType";
import { CompetitionAdminStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a new competition admin status (embedded in a table row)
 * @param props id of the current competition admin
 * @returns JSX to select and save the new competition admin status
 */
const AdminStatusAddRow: React.FC<{
  id: number;
}> = (props) => {
  // Get the id of the competition admin
  const id = props.id;
  const dispatch = useDispatch();

  // Handler when clicked the save button
  const onSaveNewCompetitionAdminStatusType = (newValue: string[]) => {
    // Create the DTO with the competition admin id and the currently selected competition admin status type
    const competitionAdminStatus: tCompetitionAdminStatusUpdate = {
      id: id,
      competitionAdminStatusType: newValue[0] as tCompetitionAdminStatusType,
    };
    // Send a PUT request to the competition admin status route to set the new status which was selected in the input field
    dispatch(changeCompetitionAdminStatus(competitionAdminStatus));
  };

  // JSX just consists of a table row with an label and the Select-Input, with possible admin status types
  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Turnier Admin Status setzen:
      </td>
      <td colSpan={2}>
        <CompetitionAdminStatusTypeInput
          saveValue={(newValue, changed) => {
            onSaveNewCompetitionAdminStatusType(newValue as string[]);
          }}
          add
        />
      </td>
    </tr>
  );
};

export default AdminStatusAddRow;
