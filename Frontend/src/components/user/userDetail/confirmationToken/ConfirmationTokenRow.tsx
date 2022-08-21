import { Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { toggleConfirmationToken } from "../../../../store/user/user-store-actions";
import { tConfirmationToken } from "../../../../types/user";

const ConfirmationTokenRow: React.FC<{
    confirmationToken: tConfirmationToken
}> = (props) => {

    const confirmationToken = props.confirmationToken;
    const additionalAttributes = confirmationToken.additionalAttributes ? confirmationToken.additionalAttributes : []

    const dispatch = useDispatch();

    const onToggleValidationHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        dispatch(toggleConfirmationToken(confirmationToken));
    }

    return <tr>
        <td>{confirmationToken.id}</td>
        <td>{confirmationToken.validFrom}</td>
        <td>{confirmationToken.validTo}</td>
        <td>{confirmationToken.confirmationToken}</td>
        <td style={{ textAlign: "right" }}>
            <Button
                variant="secondary"
                size="sm"
                style={{ width: '100px' }}
                onClick={onToggleValidationHandler}>{confirmationToken.validTo ? "Revalidate" : "Invalidate"}</Button>
        </td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default ConfirmationTokenRow;