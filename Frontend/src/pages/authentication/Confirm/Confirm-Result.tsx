import UserDetails from "../../../components/userdetails/UserDetails";
import { useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import {default as ConfirmResultComponent} from "../../../components/confirm/result/ConfirmResult";

const ConfirmResult = () => {
    const confirmedUser = useSelector((state: RootState) => {
        return state.user.confirmedUser;
    });

    return <div>
        <h2>ConfirmResult</h2>
        <ConfirmResultComponent/>
        {confirmedUser && <UserDetails authenticatedUser={confirmedUser} />}
    </div>;
};

export default ConfirmResult;