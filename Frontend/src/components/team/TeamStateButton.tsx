import { useEffect, useState } from "react";
import { Button, ButtonGroup } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { tTeamStatusType } from "../../types/enums/teamStatusType";
import { tTeam } from "../../types/team";

const TeamStateButton: React.FC<{
    team: tTeam
}> = (props) => {

    const team = props.team;

    const dispatch = useDispatch();

    const [newStatus, setNewStatus] = useState<{
        team: tTeam,
        status: tTeamStatusType
    } | null>(null);

    useEffect(() => {
        if (newStatus) {
            // Change Team Status Action
            setNewStatus(null);
        }
    }, [dispatch, newStatus]);

    const changeStatusHandler = (team: tTeam, status: tTeamStatusType) => (event: React.MouseEvent<HTMLButtonElement>) => {
        setNewStatus({
            team: team,
            status: status
        });
    };

    const createButtons = (team: tTeam, status: tTeamStatusType) => {
        if (status === tTeamStatusType.ACTIVE) {
            return <ButtonGroup aria-label="status">
                <Button variant="warning" size="sm" onClick={changeStatusHandler(team, tTeamStatusType.INACTIVE)}>Deaktivieren</Button> { }
                <Button variant="danger" size="sm" onClick={changeStatusHandler(team, tTeamStatusType.BANNED)}>Bannen</Button>
            </ButtonGroup>
        } else if (status === tTeamStatusType.INACTIVE) {
            return <ButtonGroup aria-label="status">
                <Button variant="success" size="sm" onClick={changeStatusHandler(team, tTeamStatusType.ACTIVE)}>Aktivieren</Button> { }
                <Button variant="danger" size="sm" onClick={changeStatusHandler(team, tTeamStatusType.BANNED)}>Bannen</Button>
            </ButtonGroup>

        } else if (status === tTeamStatusType.BANNED) {
            return <Button variant="info" size="sm" onClick={changeStatusHandler(team, tTeamStatusType.INACTIVE)}>Entbannen</Button>
        } else {
            return <></>
        }
    }

    return createButtons(team, team.teamStatus
        .filter(singelTeamStatus => singelTeamStatus.validTo === null)
        .map(singelTeamStatus => singelTeamStatus.teamStatusDescription)[0]
    );

}

export default TeamStateButton;