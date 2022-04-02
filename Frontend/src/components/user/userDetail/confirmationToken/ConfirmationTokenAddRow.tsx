import { useRef } from "react";
import { useDispatch } from "react-redux";
import { addConfirmationToken } from "../../../../store/user/user-store-actions";
import FormItem from "../../../ui/form/FormItem";

const ConfirmationTokenAddRow: React.FC<{
    id: number
}> = (props) => {

    const confirmationTokenRef = useRef<HTMLInputElement>(null);

    const dispatch = useDispatch();

    return <tr style={{borderTop: "2px dashed black"}}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Token erzeugen:</td>
        <td><FormItem ref={confirmationTokenRef} defaultValue="" saveValue={(newValue, changed) => {
            dispatch(addConfirmationToken({
                id: props.id,
                confirmationToken: newValue as string
            }))
            confirmationTokenRef.current!.value = "";
        }} /></td>
    </tr>;

};

export default ConfirmationTokenAddRow;