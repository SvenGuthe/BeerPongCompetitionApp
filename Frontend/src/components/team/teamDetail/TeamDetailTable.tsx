import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateTeam } from "../../../store/team/team-store-actions";
import { tTeam } from "../../../types/team";
import FormItem from "../../ui/form/FormItem";

const TeamDetailTable: React.FC<{
    team: tTeam
}> = (props) => {

    const team = props.team;

    const dispatch = useDispatch();

    const [isChanged, setIsChanged] = useState<boolean>(false);

    const teamNameRef = useRef<HTMLInputElement>(null);

    const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        if (isChanged) {
            const newMetaData = {
                id: team.id,
                teamName: teamNameRef.current?.value
            }

            dispatch(updateTeam(newMetaData));
            setIsChanged(false);
        }
    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>{team.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><FormItem ref={teamNameRef} defaultValue={team.teamName} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Player Team</th>
                    <td>{String(team.playerTeam)}</td>
                </tr>
                <tr>
                    <th>Erzeugt am</th>
                    <td>{team.creationTime}</td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }} onClick={onSaveHandler}>Save Meta Data</Button>
        </div>
    </>;

}

export default TeamDetailTable;