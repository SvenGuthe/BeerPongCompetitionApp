import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSearchParams } from "react-router-dom";
import { sendConfirmRequest } from "../../../store/authentication/authentication-store-actions";
import { Alert } from "react-bootstrap";

const ConfirmResult: React.FC = () => {
    const [searchParams] = useSearchParams();
    const dispatch = useDispatch();

    useEffect(() => {
        const searchParamToken = searchParams.get('token');
        if (searchParamToken) {
            dispatch(sendConfirmRequest(searchParamToken));
        }
    }, [searchParams, dispatch])

    return <Alert variant="success">Account wurde erfolgreich aktiviert!</Alert>
};

export default ConfirmResult;