import { Button } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tUser } from "../../../types/user";

const UserRow: React.FC<{ user: tUser }> = (props) => {

    const user = props.user;
    const additionalAttributes = user.additionalAttributes ? user.additionalAttributes : []

    const linkToDetails = `/user/${user.id}`

    return <tr>
        <td style={{ textAlign: 'center' }}>
            <Link to={linkToDetails}>
                <Button variant="secondary" size="sm">Details</Button>
            </Link>
        </td>
        <td>{user.id}</td>
        <td>{user.gamerTag}</td>
        <td>{user.enabled ? "true" : "false"}</td>
        <td>{user.userStatus.filter(status => status.validTo === null)[0].userStatus}</td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>

};

export default UserRow;