import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import useUserRoleCheckboxes from "../UserRoleCheckboxes";
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
    const linkToDetails = `${user.id}`

    return <tr key={user.id}>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{user.id}</td>
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