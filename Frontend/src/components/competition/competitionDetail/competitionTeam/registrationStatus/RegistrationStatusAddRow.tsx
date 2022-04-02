import { useDispatch } from "react-redux";
import { changeRegistrationStatus } from "../../../../../store/competition/competition-store-actions";
import { tRegistrationStatusType } from "../../../../../types/enums/registrationStatusType";
import { RegistrationStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

const RegistrationStatusAddRow: React.FC<{
    id: number
}> = (props) => {

    const id = props.id;
    const dispatch = useDispatch();

    const onSaveNewRegistrationStatusType = (newValue: string[]) => {
        const newStatus = newValue[0] as tRegistrationStatusType;
        dispatch(changeRegistrationStatus(id, newStatus));
    }

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Registrations Status setzen:</td>
        <td colSpan={2}><RegistrationStatusTypeInput saveValue={(newValue, changed) => {
            onSaveNewRegistrationStatusType(newValue as string[]);
        }} add /></td>
    </tr>;

};

export default RegistrationStatusAddRow;