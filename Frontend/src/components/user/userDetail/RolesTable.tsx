import { Table } from "react-bootstrap";
import { tUser } from "../../../types/authentication";
import { tSecurityRole } from "../../../types/enums/securityRole";
import UserRoleCheckboxes from "../UserRoleCheckboxes";

const RolesTable: React.FC<{ user: tUser }> = (props) => {

    const user = props.user;

    const checkboxAdmin = UserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_ADMINISTRATOR
    });
    const checkboxModerator = UserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_MODERATOR
    });
    const checkboxPlayer = UserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_PLAYER
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