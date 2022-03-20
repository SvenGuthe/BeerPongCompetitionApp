import { useDispatch } from "react-redux";
import { changeBillingStatus } from "../../../../../store/competition/competition-store-actions";
import { tBillingStatusType } from "../../../../../types/enums/billingStatusType";
import { BillingStatusTypeSelectInput } from "../../../../ui/form/PredefinedSelectInputs";

const BillingStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewBillingStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tBillingStatusType;
        dispatch(changeBillingStatus(id, newStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Zahlungs Status setzen:</td>
        <td colSpan={2}><BillingStatusTypeSelectInput saveValue={(newValue, changed) => {
            onSaveNewBillingStatusType(newValue as string[]);
        }} add /></td>
    </tr>;

};

export default BillingStatusAddRow;