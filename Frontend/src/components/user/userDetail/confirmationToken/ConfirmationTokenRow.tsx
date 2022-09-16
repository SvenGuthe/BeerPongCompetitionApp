import { Button } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { toggleConfirmationToken } from "../../../../store/user/user-store-actions";
import { tConfirmationToken } from "../../../../types/user";

/**
 * Component which defines a single row (a single confirmation token)
 * @param props the confirmation token DTO with all required information
 * @returns JSX with is a single row of the confirmation token table
 */
const ConfirmationTokenRow: React.FC<{
  confirmationToken: tConfirmationToken;
}> = (props) => {
  // get the confirmation token from props
  const confirmationToken = props.confirmationToken;
  const additionalAttributes = confirmationToken.additionalAttributes
    ? confirmationToken.additionalAttributes
    : [];

  const dispatch = useDispatch();

  // Handler to change a confirmation token
  const onToggleValidationHandler = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    // a PUT request will be send to the confirmation token route to change the current confirmation token
    dispatch(toggleConfirmationToken(confirmationToken));
  };

  return (
    <tr>
      <td>{confirmationToken.id}</td>
      <td>{confirmationToken.validFrom}</td>
      <td>{confirmationToken.validTo}</td>
      <td>{confirmationToken.confirmationToken}</td>
      <td style={{ textAlign: "right" }}>
        <Button
          variant="secondary"
          size="sm"
          style={{ width: "100px" }}
          onClick={onToggleValidationHandler}
        >
          {confirmationToken.validTo ? "Revalidate" : "Invalidate"}
        </Button>
      </td>
      {additionalAttributes.map((additionalAttribute) => {
        return additionalAttribute.reactElement ? (
          <td key={additionalAttribute.id}>
            {additionalAttribute.reactElement}
          </td>
        ) : (
          <td key={additionalAttribute.id}>{additionalAttribute.value}</td>
        );
      })}
    </tr>
  );
};

export default ConfirmationTokenRow;
