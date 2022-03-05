import AuthenticatedUser from "../../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { default as ConfirmWaitComponent } from "../../../components/confirm/wait/ConfirmWait";

const ConfirmWait = () => {

    const registeredUser = useSelector((state: RootState) => {
        return state.authentication.registeredUser;
    });

    return <>
        <h2>ConfirmWait</h2>
        <ConfirmWaitComponent />
        {registeredUser && <AuthenticatedUser authenticatedUser={registeredUser} />}
    </>;
};

export default ConfirmWait;