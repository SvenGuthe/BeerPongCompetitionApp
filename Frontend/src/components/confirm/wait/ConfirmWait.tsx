import { Alert } from "react-bootstrap";

/**
 * Component to return the message that the user have to activate his account via the link in the confirmation email
 * @returns JSX with an Alert that the account was to be activated
 */
const ConfirmWait: React.FC = () => {
  return (
    <Alert variant="info">
      Aktiviere deinen Account durch den Bestätigungslink in deinen Mails!
    </Alert>
  );
};

export default ConfirmWait;
