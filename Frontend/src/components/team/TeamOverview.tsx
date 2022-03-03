import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { sendFetchTeamsRequest } from "../../store/team-store-actions";
import UserTeamTable from "./UserTeamTable";
import RealTeamTable from "./RealTeamTable";

const Team: React.FC = () => {

    const dispatch = useDispatch();

    const { token, teams } = useSelector((state: RootState) => {
        return {
            token: state.user.token,
            teams: state.team.teams
        }
    });

    useEffect(() => {
        if (token) {
            dispatch(sendFetchTeamsRequest(token));
        }
    }, [dispatch, token]);

    return <>
        <h3>Teams</h3>
        {teams && <RealTeamTable teams={teams.filter(team => !team.playerTeam)} />}
        <h3>Einzelspieler</h3>
        {teams && <UserTeamTable teams={teams.filter(team => team.playerTeam)} />}
    </>;
}

export default Team;