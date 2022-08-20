import { tConfirmationToken } from "../../../../types/user";
import FormItem from "../../../ui/form/FormItem";

const ConfirmationTokenRow: React.FC<{
    confirmationToken: tConfirmationToken
}> = (props) => {

    const confirmationToken = props.confirmationToken;
    const additionalAttributes = confirmationToken.additionalAttributes ? confirmationToken.additionalAttributes : []

    return <tr>
        <td>{confirmationToken.id}</td>
        <td>{confirmationToken.validFrom}</td>
        <td><FormItem defaultValue={confirmationToken.confirmationToken} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default ConfirmationTokenRow;