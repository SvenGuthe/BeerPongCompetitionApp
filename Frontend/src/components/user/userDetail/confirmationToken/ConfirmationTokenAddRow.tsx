import FormItem from "../../../ui/form/FormItem";

const ConfirmationTokenAddRow: React.FC = () => {

    return <tr style={{borderTop: "2px dashed black"}}>
        <td colSpan={2} style={{ textAlign: "right" }}>Neuen Token erzeugen:</td>
        <td><FormItem defaultValue="" saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
    </tr>;

};

export default ConfirmationTokenAddRow;