import { TeamStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

const TeamStatusAddRow: React.FC = (props) => {

    return <tr style={{borderTop: "2px dashed black"}}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Team Status setzen:</td>
        <td colSpan={2}><TeamStatusTypeInput saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;


}

export default TeamStatusAddRow;