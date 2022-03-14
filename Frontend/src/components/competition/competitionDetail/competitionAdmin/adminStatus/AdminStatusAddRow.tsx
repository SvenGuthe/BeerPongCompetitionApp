import { CompetitionAdminStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

const AdminStatusAddRow: React.FC = (props) => {

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Turnier Admin Status setzen:</td>
        <td colSpan={2}><CompetitionAdminStatusTypeInput saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;

};

export default AdminStatusAddRow;