import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import UserTeamTable from "./userteam/UserTeamTable";
import RealTeamTable from "./realteam/RealTeamTable";
import { storeTeams } from "../../store/team/team-store";
import { getRequest } from "../../utility/genericHTTPFunctions";

const Team: React.FC = () => {

    const dispatch = useDispatch();

    const { teams } = useSelector((state: RootState) => {
        return {
            teams: state.team.teams
        }
    });

    useEffect(() => {
        dispatch(getRequest("/team/team", storeTeams));
    }, [dispatch]);

    return <>
        <h3>Richtige Teams</h3>
        {teams && <RealTeamTable teams={teams.filter(team => !team.playerTeam)} />}
        <h3>Einzelspieler</h3>
        {teams && <UserTeamTable teams={teams.filter(team => team.playerTeam)} />}
    </>;
}

export default Team;