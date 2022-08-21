import { useRef } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateTeamComposition } from "../../../store/team/team-store-actions";
import { tTeamCompositionUpdate } from "../../../types/team";
import { tUser } from "../../../types/user";
import CheckboxInput from "../../ui/form/CheckboxInput";

const UserDetailsTableTeam: React.FC<{
    user: tUser,
    admin: boolean,
    teamCompositionId: number
}> = (props) => {

    const inputRef = useRef<HTMLInputElement>(null);;

    const user = props.user;
    const admin = props.admin;
    const teamCompositionId = props.teamCompositionId;

    const dispatch = useDispatch();

    const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {

        event.preventDefault();
        const newIsAdmin = inputRef.current!.checked;
        const teamCompositionUpdate: tTeamCompositionUpdate = {
            id: teamCompositionId,
            isAdmin: newIsAdmin
        }

        dispatch(updateTeamComposition(teamCompositionUpdate))

    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>User ID</th>
                    <td>{user.id}</td>
                </tr>
                <tr>
                    <th>Vorname</th>
                    <td>{user.firstName}</td>
                </tr>
                <tr>
                    <th>Nachname</th>
                    <td>{user.lastName}</td>
                </tr>
                <tr>
                    <th>Spielername</th>
                    <td>{user.gamerTag}</td>
                </tr>
                <tr>
                    <th>Admin im Team</th>
                    <td><CheckboxInput disabled={false} value={admin} ref={inputRef} /></td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }} onClick={onSaveHandler}>Save Data</Button>
        </div>
    </>;

};

export default UserDetailsTableTeam;