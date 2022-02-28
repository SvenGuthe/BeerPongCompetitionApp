import UserDetails from "../../../components/userdetails/UserDetails";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { default as ConfirmWaitComponent } from "../../../components/confirm/wait/ConfirmWait";

const ConfirmWait = () => {

    const registeredUser = useSelector((state: RootState) => {
        return state.user.registeredUser;
    });

    return <>
        <h1>ConfirmWait</h1>
        <ConfirmWaitComponent />
        {registeredUser && <UserDetails authenticatedUser={registeredUser} />}
    </>;
};

export default ConfirmWait;