import { useDispatch } from "react-redux";
import { changeBillingStatus } from "../../../../../store/competition/competition-store-actions";
import tBillingStatusUpdate from "../../../../../types/competition/billing/billingStatusUpdate";
import { tBillingStatusType } from "../../../../../types/enums/billingStatusType";
import { BillingStatusTypeSelectInput } from "../../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new billing status
 * @param props the id of the competition team
 * @returns JSX with a single table row (4 columns) and the possibility (select input) to choose a new billing status for the competition team
 */
const BillingStatusAddRow: React.FC<{
  id: number;
}> = (props) => {
  // get the competition team id from the props
  const id = props.id;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewBillingStatusType = (newValue: string[]) => {
    // Create the DTO with the competition team id and the selected billing status
    const billingStatus: tBillingStatusUpdate = {
      id: id,
      billingStatusType: newValue[0] as tBillingStatusType,
    };

    // Send a PUT request to the billing status route to set the new billin status
    dispatch(changeBillingStatus(billingStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Zahlungs Status setzen:
      </td>
      <td colSpan={2}>
        <BillingStatusTypeSelectInput
          saveValue={(newValue, changed) => {
            onSaveNewBillingStatusType(newValue as string[]);
          }}
          add
        />
      </td>
    </tr>
  );
};

export default BillingStatusAddRow;
