import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tUser } from "../../../types/authentication";
import { tSecurityRole } from "../../../types/enums/securityRole";
import useUserRoleCheckboxes from "../UserRoleCheckboxes";

const UserRow: React.FC<{ user: tUser }> = (props) => {

    const user = props.user;
    const checkboxAdmin = useUserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_ADMINISTRATOR
    });
    const checkboxMod = useUserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_MODERATOR
    });
    const checkboxPlayer = useUserRoleCheckboxes({
        user: user,
        role: tSecurityRole.ROLE_PLAYER
    });
    const linkToDetails = `${user.id}`

    return <tr key={user.id}>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{user.id}</td>
        <td>{user.gamerTag}</td>
        <td>{user.enabled ? "true" : "false"}</td>
        <td>{user.userStatus.userStatus}</td>
        <td style={{ textAlign: 'center' }}>{checkboxAdmin}</td>
        <td style={{ textAlign: 'center' }}>{checkboxMod}</td>
        <td style={{ textAlign: 'center' }}>{checkboxPlayer}</td>
    </tr>

};

export default UserRow;