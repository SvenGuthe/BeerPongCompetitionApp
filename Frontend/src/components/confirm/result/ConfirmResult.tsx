import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSearchParams } from "react-router-dom";
import { sendConfirmRequest } from "../../../store/authentication/authentication-store-actions";
import { Alert } from "react-bootstrap";

/**
 * Component to return the message that the user was successfully activated
 * @returns JSX with an Alert that the account was activated
 */
const ConfirmResult: React.FC = () => {
  const [searchParams] = useSearchParams();
  const dispatch = useDispatch();

  // Get the token from the URL
  useEffect(() => {
    const searchParamToken = searchParams.get("token");
    if (searchParamToken) {
      // Send a GET request to the confirmation route to set the current user which was activated
      dispatch(sendConfirmRequest(searchParamToken));
    }
  }, [searchParams, dispatch]);

  return <Alert variant="success">Account wurde erfolgreich aktiviert!</Alert>;
};

export default ConfirmResult;
