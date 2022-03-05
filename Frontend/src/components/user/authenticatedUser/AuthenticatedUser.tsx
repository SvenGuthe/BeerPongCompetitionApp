import { Table } from "react-bootstrap";
import { tPrivilege, tRole } from "../../../types/authenticate";
import { tAuthenticatedUser } from "../../../types/user";

interface tProps {
    authenticatedUser?: tAuthenticatedUser,
    roles?: tRole[],
    privileges?: tPrivilege[]
}

const AuthenticatedUser: React.FC<tProps> = (props) => {

    const authenticatedUser = props.authenticatedUser;
    const roles = props.roles;
    const privileges = props.privileges;

    let roleTable;
    let privilegesTable;

    if (roles) {
        roleTable = (<Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>Rolle</th>
                </tr>
            </thead>
            <tbody>
                {roles.map(role => {
                    return <tr key={role.roleId}>
                        <td>{role.name}</td>
                    </tr>
                })}
            </tbody>
        </Table>);
    }

    if (privileges) {
        privilegesTable = (<Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>Privileges</th>
                </tr>
            </thead>
            <tbody>
                {privileges.map(privilege => {
                    return <tr key={privilege.privilegeId}>
                        <td>{privilege.name}</td>
                    </tr>
                })}
            </tbody>
        </Table>);
    }

    return <>
        <Table striped bordered hover size="sm">
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
                    <td>{authenticatedUser?.userStatus?.userStatus}</td>
                </tr>
                <tr>
                    <td>User Status ID</td>
                    <td>{authenticatedUser?.userStatus?.userStatusId}</td>
                </tr>
                <tr>
                    <td>Datum</td>
                    <td>{authenticatedUser?.creationTime}</td>
                </tr>
            </tbody>

        </Table>

        {roleTable}

        {privilegesTable}
    </>

}

export default AuthenticatedUser;