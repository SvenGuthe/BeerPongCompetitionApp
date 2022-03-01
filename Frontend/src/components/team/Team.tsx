import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { sendFetchTeamsRequest } from "../../store/team-store-actions";

const Team: React.FC = () => {

    const dispatch = useDispatch();

    const token = useSelector((state: RootState) => {
        return state.user.token
    });

    useEffect(() => {
        if (token) {
            dispatch(sendFetchTeamsRequest(token));
        }
    }, [dispatch, token]);

    return <></>;
}

export default Team;