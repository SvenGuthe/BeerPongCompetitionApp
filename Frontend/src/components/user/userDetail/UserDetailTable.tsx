import { Table } from "react-bootstrap";
import { tUser } from "../../../types/authentication";
import FormItem from "../../ui/form/FormItem";
import { MultiSecurityRoleInput, UserStatusTypeInput } from "../../ui/form/PredefinedSelectInputs";

const UserDetailsTable: React.FC<{
    user: tUser
}> = (props) => {

    const user = props.user;

    return <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <th>User ID</th>
                <td>{user.id}</td>
            </tr>
            <tr>
                <th>Vorname</th>
                <td><FormItem defaultValue={user.firstName} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Nachname</th>
                <td><FormItem defaultValue={user.lastName} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Spielername</th>
                <td><FormItem defaultValue={user.gamerTag} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>E-Mail</th>
                <td><FormItem defaultValue={user.email} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Profil angelegt am</th>
                <td>{user.creationTime}</td>
            </tr>
            <tr>
                <th>Freigeschalten</th>
                <td><FormItem defaultValue={user.enabled} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Status</th>
                <td><UserStatusTypeInput defaultValue={user.userStatus.userStatus} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>User Roles</th>
                <td><MultiSecurityRoleInput defaultValue={user.roles.map(role => role.role)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
        </tbody>
    </Table>;

};

export default UserDetailsTable;