import { Table } from "react-bootstrap";
import { tAuthenticatedUser } from "../../types/user";

const UserDetails: React.FC<{ authenticatedUser: tAuthenticatedUser }> = (props) => {

    const authenticatedUser = props.authenticatedUser;

    return <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <td>E-Mail</td>
                <td>{authenticatedUser?.email}</td>
            </tr>
            <tr>
                <td>Vorname</td>
                <td>{authenticatedUser?.firstName}</td>
            </tr>
            <tr>
                <td>Nachname</td>
                <td>{authenticatedUser?.lastName}</td>
            </tr>
            <tr>
                <td>Spielername</td>
                <td>{authenticatedUser?.gamerTag}</td>
            </tr>
            <tr>
                <td>Aktiv</td>
                <td>{authenticatedUser?.enabled ? "true" : "false"}</td>
            </tr>
            <tr>
                <td>User ID</td>
                <td>{authenticatedUser?.userId}</td>
            </tr>
            <tr>
                <td>User Status</td>
                <td>{authenticatedUser?.userStatus.userStatus}</td>
            </tr>
            <tr>
                <td>User Status ID</td>
                <td>{authenticatedUser?.userStatus.userStatusId}</td>
            </tr>
            <tr>
                <td>Datum</td>
                <td>{authenticatedUser?.creationTime}</td>
            </tr>
        </tbody>
    </Table>

}

export default UserDetails;