import { Table } from "react-bootstrap";
import { tPrivilege, tUser } from "../../../types/authentication";
import { tSecurityPrivilege } from "../../../types/enums/securityPrivilege";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import FormItem from "../../ui/form/FormItem";

interface tProps {
    authenticatedUser?: tUser
}

const AuthenticatedUser: React.FC<tProps> = (props) => {

    const authenticatedUser = props.authenticatedUser;
    const roles = authenticatedUser?.roles;
    
    const privileges = removeDuplicates(authenticatedUser?.roles.flatMap(role => role.privileges));

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
                    return <tr key={role.id}>
                        <td>{role.role}</td>
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
                    return <tr key={privilege.id}>
                        <td>{privilege.privilege}</td>
                    </tr>
                })}
            </tbody>
        </Table>);
    }

    const allPrivileges: tPrivilege[] = [
        {
            id: 1,
            privilege: tSecurityPrivilege.ADMIN_ACL_PRIVILEGE,
            value: tSecurityPrivilege.ADMIN_ACL_PRIVILEGE
        },
        {
            id: 2,
            privilege: tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE,
            value: tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE
        },
        {
            id: 3,
            privilege: tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE,
            value: tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE
        }
    ]

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
                    <td>
                        {authenticatedUser?.id}
                        <FormItem defaultValue="ABC" saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        <FormItem defaultValue={true} saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        <FormItem defaultValue={allPrivileges[0]} possibleValues={allPrivileges} saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        <FormItem defaultValue={[allPrivileges[0], allPrivileges[1]]} possibleValues={allPrivileges} multiSelect saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        </td>
                </tr>
                <tr>
                    <td>User Status</td>
                    <td>{authenticatedUser?.userStatus?.userStatus}</td>
                </tr>
                <tr>
                    <td>User Status ID</td>
                    <td>{authenticatedUser?.userStatus?.id}</td>
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