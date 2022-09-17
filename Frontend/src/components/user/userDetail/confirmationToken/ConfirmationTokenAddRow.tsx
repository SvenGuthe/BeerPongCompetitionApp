import { useRef } from "react";
import { useDispatch } from "react-redux";
import { addConfirmationToken } from "../../../../store/user/user-store-actions";
import FormItem from "../../../ui/form/FormItem";

/**
 * Component to add a row to an existing table with an input field to add a new confirmation token
 * @param props the id of the user
 * @returns JSX with a single table row (5 columns) and the possibility (input) to add a new custom confirmation token
 */
const ConfirmationTokenAddRow: React.FC<{
  userId: number;
}> = (props) => {
  const confirmationTokenRef = useRef<HTMLInputElement>(null);

  const dispatch = useDispatch();

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={4} style={{ textAlign: "right" }}>
        Neuen Token erzeugen:
      </td>
      <td>
        <FormItem
          ref={confirmationTokenRef}
          defaultValue=""
          saveValue={(newValue, changed) => {
            // Send a POST request to the confirmation token route to add a new one
            dispatch(
              addConfirmationToken({
                id: props.userId,
                confirmationToken: newValue as string,
              })
            );
            confirmationTokenRef.current!.value = "";
          }}
        />
      </td>
    </tr>
  );
};

export default ConfirmationTokenAddRow;
