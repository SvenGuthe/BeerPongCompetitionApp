import AuthenticatedUser from "../../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import {default as ConfirmResultComponent} from "../../../components/confirm/result/ConfirmResult";

const ConfirmResult = () => {
    const confirmedUser = useSelector((state: RootState) => {
        return state.authentication.confirmedUser;
    });

    return <div>
        <h2>ConfirmResult</h2>
        <ConfirmResultComponent/>
        {confirmedUser && <AuthenticatedUser authenticatedUser={confirmedUser} />}
    </div>;
};

export default ConfirmResult;