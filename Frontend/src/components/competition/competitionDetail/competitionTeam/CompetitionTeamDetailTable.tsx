import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateCompetitionTeam } from "../../../../store/competition/competition-store-actions";
import { tCompetitionTeam, tCompetitionTeamUpdate } from "../../../../types/competition";
import { tTeamAndUser } from "../../../../types/team";
import FormItem from "../../../ui/form/FormItem";

const CompetitionTeamDetailTable: React.FC<{
    competitionTeamDetail: tCompetitionTeam,
    teams: tTeamAndUser[]
}> = (props) => {

    const competitionTeamDetail = props.competitionTeamDetail;
    const competitionPlayerSize = competitionTeamDetail.competitionPlayer.length;

    const possibileTeams = props.teams.filter(team => {
        const checkAllTeamCompetitionPlayerInTeams = competitionTeamDetail.competitionPlayer.filter(singleCompetitionPlayer => {
            return team.users.find(user => user.id === singleCompetitionPlayer.user.id);
        })
        return checkAllTeamCompetitionPlayerInTeams.length === competitionPlayerSize
    }).map(possibileTeam => possibileTeam.team)

    const dispatch = useDispatch();

    const [isChanged, setIsChanged] = useState<boolean>(false);

    const competitionTeamNameRef = useRef<HTMLInputElement>(null);
    const competitionTeamMainTeamRef = useRef<HTMLSelectElement>(null);

    const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {

        event.preventDefault();

        if (isChanged) {

            const teamId = props.teams.find(team => team.team.teamName === competitionTeamMainTeamRef.current!.value)?.team.id

            const competitionTeam: tCompetitionTeamUpdate = {
                id: competitionTeamDetail.id,
                teamname: competitionTeamNameRef.current!.value,
                teamId: teamId
            };

            dispatch(updateCompetitionTeam(competitionTeam));
            setIsChanged(false);
        }

    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Competition Team ID</th>
                    <td>{competitionTeamDetail.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><FormItem ref={competitionTeamNameRef} defaultValue={competitionTeamDetail.competitionTeamName} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Hauptteam</th>
                    <td><FormItem ref={competitionTeamMainTeamRef} possibleValues={possibileTeams.map(possibileTeam => possibileTeam.teamName)} defaultValue={competitionTeamDetail.team && [competitionTeamDetail.team.teamName]} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Hinzugefügt am</th>
                    <td>{competitionTeamDetail.creationTime}</td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }} onClick={onSaveHandler}>Save Meta Data</Button>
        </div>
    </>;

};

export default CompetitionTeamDetailTable;