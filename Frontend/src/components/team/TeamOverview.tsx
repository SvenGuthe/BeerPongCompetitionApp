import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { sendFetchTeamsRequest } from "../../store/team-store-actions";
import UserTeamTable from "./UserTeamTable";
import RealTeamTable from "./RealTeamTable";
import { TeamStatus } from "../../types/enums/teamStatus";
import { Button } from "react-bootstrap";

const stateToButtons = (status: TeamStatus) => {
    if (status === TeamStatus.ACTIVE) {
        return <>
            <Button variant="warning" size="sm">Deaktivieren</Button> { }
            <Button variant="danger" size="sm">Bannen</Button>
        </>
    } else if (status === TeamStatus.INACTIVE) {
        return <>
            <Button variant="success" size="sm">Aktivieren</Button> { }
            <Button variant="danger" size="sm">Bannen</Button>
        </>

    } else if (status === TeamStatus.BANNED) {
        return <Button variant="success" size="sm">Entbannen</Button>
    }
}

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
        {teams && <RealTeamTable stateToButtons={stateToButtons} teams={teams.filter(team => !team.playerTeam)} />}
        <h3>Einzelspieler</h3>
        {teams && <UserTeamTable stateToButtons={stateToButtons} teams={teams.filter(team => team.playerTeam)} />}
    </>;
}

export default Team;