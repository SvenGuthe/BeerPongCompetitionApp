import AuthenticatedUser from "../../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { default as ConfirmWaitComponent } from "../../../components/confirm/wait/ConfirmWait";
import Hierarchy from "../../../components/ui/Hierarchy";
import { confirmWaitHierarchy, homeHierarchy } from "../../../types/hierarchy";

const ConfirmWait = () => {

    const registeredUser = useSelector((state: RootState) => {
        return state.authentication.registeredUser;
    });

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, confirmWaitHierarchy]} />
        <h2>ConfirmWait</h2>
        <ConfirmWaitComponent />
        {registeredUser && <AuthenticatedUser authenticatedUser={registeredUser} />}
    </>;
};

export default ConfirmWait;