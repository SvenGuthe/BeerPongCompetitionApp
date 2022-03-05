import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import useUserRoleCheckboxes from "../use-userRoleCheckboxes";
import { Role } from "../../../types/enums/role";
import { tUserDetail } from "../../../types/user";

const UserRow: React.FC<{ user: tUserDetail }> = (props) => {

    const user = props.user;
    const checkboxAdmin = useUserRoleCheckboxes({
        user: user,
        role: Role.ROLE_ADMINISTRATOR
    });
    const checkboxMod = useUserRoleCheckboxes({
        user: user,
        role: Role.ROLE_MODERATOR
    });
    const checkboxPlayer = useUserRoleCheckboxes({
        user: user,
        role: Role.ROLE_PLAYER
    });
    const linkToDetails = `${user.userId}`

    return <tr key={user.userId}>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{user.userId}</td>
        <td>{user.gamerTag}</td>
        <td>{user.teams.filter(team => team.playerTeam === false).length}</td>
        <td>{user.enabled ? "true" : "false"}</td>
        <td>{user.userStatus.userStatus}</td>
        <td style={{ textAlign: 'center' }}>{checkboxAdmin}</td>
        <td style={{ textAlign: 'center' }}>{checkboxMod}</td>
        <td style={{ textAlign: 'center' }}>{checkboxPlayer}</td>
    </tr>

};

export default UserRow;