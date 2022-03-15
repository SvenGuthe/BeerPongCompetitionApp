import { CompetitionStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

const CompetitionStatusAddRow: React.FC = (props) => {

    return <tr style={{ borderTop: "2px dashed black" }}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Team Status setzen:</td>
        <td colSpan={2}><CompetitionStatusTypeInput saveValue={(newValue, changed) => console.log(newValue, changed)} add /></td>
    </tr>;

};

export default CompetitionStatusAddRow;