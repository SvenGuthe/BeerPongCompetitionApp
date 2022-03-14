import { BillingStatusTypeSelectInput } from "../../../../ui/form/PredefinedSelectInputs";

const BillingStatusAddRow: React.FC = (props) => {

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Zahlungs Status setzen:</td>
        <td colSpan={2}><BillingStatusTypeSelectInput saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;

};

export default BillingStatusAddRow;