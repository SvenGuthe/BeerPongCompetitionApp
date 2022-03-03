import { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { changeTeamStatus, sendFetchTeamsRequest } from "../../store/team-store-actions";
import UserTeamTable from "./UserTeamTable";
import RealTeamTable from "./RealTeamTable";
import { TeamStatus } from "../../types/enums/teamStatus";
import { tTeamWithUsers } from "../../types/team";
import { Button } from "react-bootstrap";
import { ButtonGroup } from "react-bootstrap";

const Team: React.FC = () => {

    const dispatch = useDispatch();

    const [newStatus, setNewStatus] = useState<{
        team: tTeamWithUsers,
        status: TeamStatus
    } | null>(null);

    const { token, teams } = useSelector((state: RootState) => {
        return {
            token: state.user.token,
            teams: state.team.teams
        }
    });

    const changeStatusHandler = (team: tTeamWithUsers, status: TeamStatus) => (event: React.MouseEvent<HTMLButtonElement>) => {
        setNewStatus({
            team: team,
            status: status
        });
    };

    const createButtons = (team: tTeamWithUsers, status: TeamStatus) => {
        if (status === TeamStatus.ACTIVE) {
            return <ButtonGroup aria-label="status">
                <Button variant="warning" size="sm" onClick={changeStatusHandler(team, TeamStatus.INACTIVE)}>Deaktivieren</Button> { }
                <Button variant="danger" size="sm" onClick={changeStatusHandler(team, TeamStatus.BANNED)}>Bannen</Button>
            </ButtonGroup>
        } else if (status === TeamStatus.INACTIVE) {
            return <ButtonGroup aria-label="status">
                <Button variant="success" size="sm" onClick={changeStatusHandler(team, TeamStatus.ACTIVE)}>Aktivieren</Button> { }
                <Button variant="danger" size="sm" onClick={changeStatusHandler(team, TeamStatus.BANNED)}>Bannen</Button>
            </ButtonGroup>

        } else if (status === TeamStatus.BANNED) {
            return <Button variant="info" size="sm" onClick={changeStatusHandler(team, TeamStatus.INACTIVE)}>Entbannen</Button>
        }
    }

    useEffect(() => {
        if (newStatus) {
            dispatch(changeTeamStatus(newStatus.team, newStatus.status));
            setNewStatus(null);
        }
    }, [dispatch, newStatus]);

    useEffect(() => {
        if (token) {
            dispatch(sendFetchTeamsRequest(token));
        }
    }, [dispatch, token]);

    return <>
        <h3>Teams</h3>
        {teams && <RealTeamTable createButtons={createButtons} teams={teams.filter(team => !team.playerTeam)} />}
        <h3>Einzelspieler</h3>
        {teams && <UserTeamTable createButtons={createButtons} teams={teams.filter(team => team.playerTeam)} />}
    </>;
}

export default Team;