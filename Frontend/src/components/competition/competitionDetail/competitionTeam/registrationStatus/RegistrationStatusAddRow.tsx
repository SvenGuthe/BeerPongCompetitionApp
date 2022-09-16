import { useDispatch } from "react-redux";
import { changeRegistrationStatus } from "../../../../../store/competition/competition-store-actions";
import { tRegistrationStatusUpdate } from "../../../../../types/competition";
import { tRegistrationStatusType } from "../../../../../types/enums/registrationStatusType";
import { RegistrationStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new registration status
 * @param props the id of the competition team
 * @returns JSX with a single table row (4 columns) and the possibility (select input) to choose a new registration status for the competition team
 */
const RegistrationStatusAddRow: React.FC<{
  id: number;
}> = (props) => {
  // get the competition team id from the props
  const id = props.id;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewRegistrationStatusType = (newValue: string[]) => {
    // Create the DTO with the competition team id and the selcted registration status
    const registrationStatus: tRegistrationStatusUpdate = {
      id: id,
      registrationStatusType: newValue[0] as tRegistrationStatusType,
    };

    // Send a PUT request to the registration status route to set the new registration status
    dispatch(changeRegistrationStatus(registrationStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Registrations Status setzen:
      </td>
      <td colSpan={2}>
        <RegistrationStatusTypeInput
          saveValue={(newValue, changed) => {
            onSaveNewRegistrationStatusType(newValue as string[]);
          }}
          add
        />
      </td>
    </tr>
  );
};

export default RegistrationStatusAddRow;
