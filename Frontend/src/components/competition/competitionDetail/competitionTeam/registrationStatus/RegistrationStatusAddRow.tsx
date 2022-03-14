import { RegistrationStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

const RegistrationStatusAddRow: React.FC = (props) => {

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Registrations Status setzen:</td>
        <td colSpan={2}><RegistrationStatusTypeInput saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;

};

export default RegistrationStatusAddRow;