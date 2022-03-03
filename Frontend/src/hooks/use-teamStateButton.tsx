import { useEffect, useState } from "react";
import { Button, ButtonGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { changeTeamStatus } from "../store/team-store-actions";
import { TeamStatus } from "../types/enums/teamStatus";
import { tTeamWithUsers } from "../types/team";

const useTeamStateButton = (team: tTeamWithUsers) => {

    const dispatch = useDispatch();

    const [newStatus, setNewStatus] = useState<{
        team: tTeamWithUsers,
        status: TeamStatus
    } | null>(null);

    useEffect(() => {
        if (newStatus) {
            dispatch(changeTeamStatus(newStatus.team, newStatus.status));
            setNewStatus(null);
        }
    }, [dispatch, newStatus]);

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

    return createButtons(team, team.teamStatusHistories
        .filter(teamStatusHistory => teamStatusHistory.validTo === null)
        .map(teamStatusHistory => teamStatusHistory.teamStatusDescription)[0]
    )

}