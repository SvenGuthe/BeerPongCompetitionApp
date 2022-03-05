import { Table } from "react-bootstrap";
import { Role } from "../../../types/enums/role";
import { tUserDetail } from "../../../types/user";
import UserRoleCheckboxes from "../UserRoleCheckboxes";

const RolesTable: React.FC<{ user: tUserDetail }> = (props) => {

    const user = props.user;

    const checkboxAdmin = UserRoleCheckboxes({
        user: user,
        role: Role.ROLE_ADMINISTRATOR
    });
    const checkboxModerator = UserRoleCheckboxes({
        user: user,
        role: Role.ROLE_MODERATOR
    });
    const checkboxPlayer = UserRoleCheckboxes({
        user: user,
        role: Role.ROLE_PLAYER
    });

    return <>
        <h4>Rollen</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>Administrator</th>
                    <th>Moderator</th>
                    <th>Player</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>{checkboxAdmin}</td>
                    <td>{checkboxModerator}</td>
                    <td>{checkboxPlayer}</td>
                </tr>
            </tbody>
        </Table>
    </>;

}

export default RolesTable;