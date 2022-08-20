import { tConfirmationToken } from "../../../../types/user";

const ConfirmationTokenRow: React.FC<{
    confirmationToken: tConfirmationToken
}> = (props) => {

    const confirmationToken = props.confirmationToken;
    const additionalAttributes = confirmationToken.additionalAttributes ? confirmationToken.additionalAttributes : []

    return <tr>
        <td>{confirmationToken.id}</td>
        <td>{confirmationToken.validFrom}</td>
        <td>{confirmationToken.confirmationToken}</td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default ConfirmationTokenRow;