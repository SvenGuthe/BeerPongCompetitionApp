import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateUser } from "../../../store/user/user-store-actions";
import { tUser, tUserUpdate } from "../../../types/authentication";
import { tSecurityRole } from "../../../types/enums/securityRole";
import { tUserStatusType } from "../../../types/enums/userStatusType";
import FormItem from "../../ui/form/FormItem";
import { MultiSecurityRoleInput, UserStatusTypeInput } from "../../ui/form/PredefinedSelectInputs";

const UserDetailsTable: React.FC<{
    user: tUser
}> = (props) => {

    const user = props.user;

    const firstNameRef = useRef<HTMLInputElement>(null);
    const lastNameRef = useRef<HTMLInputElement>(null);
    const gamerTagRef = useRef<HTMLInputElement>(null);
    const emailRef = useRef<HTMLInputElement>(null);
    const enabledRef = useRef<HTMLInputElement>(null);

    const userStatusTypeRef = useRef<HTMLSelectElement>(null);
    const [roles, setRoles] = useState<tSecurityRole[]>(user.roles.map(role => role.role));

    const [isChanged, setIsChanged] = useState<boolean>(false);

    const dispatch = useDispatch();

    const onMultiSelectChangeHandler = (values: string | number | boolean | string[] | number[]) => {
        setRoles((values as string[]).map(val => val as tSecurityRole));
    }

    const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {

        event.preventDefault();

        if (isChanged) {
            const newMetaData: tUserUpdate = {
                id: user.id,
                firstName: firstNameRef.current!.value,
                lastName: lastNameRef.current!.value,
                gamerTag: gamerTagRef.current!.value,
                email: emailRef.current!.value,
                enabled: enabledRef.current!.checked,
                userStatusType: userStatusTypeRef.current!.value as tUserStatusType,
                roles: roles
            }

            dispatch(updateUser(newMetaData));
            
        }

    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>User ID</th>
                    <td>{user.id}</td>
                </tr>
                <tr>
                    <th>Vorname</th>
                    <td><FormItem ref={firstNameRef} defaultValue={user.firstName} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Nachname</th>
                    <td><FormItem ref={lastNameRef} defaultValue={user.lastName} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Spielername</th>
                    <td><FormItem ref={gamerTagRef} defaultValue={user.gamerTag} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>E-Mail</th>
                    <td><FormItem ref={emailRef} defaultValue={user.email} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Profil angelegt am</th>
                    <td>{user.creationTime}</td>
                </tr>
                <tr>
                    <th>Freigeschalten</th>
                    <td><FormItem ref={enabledRef} defaultValue={user.enabled} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><UserStatusTypeInput ref={userStatusTypeRef} defaultValue={user.userStatus.filter(status => status.validTo === null)[0].userStatus} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>User Roles</th>
                    <td><MultiSecurityRoleInput defaultValue={roles} saveValue={(newValue, changed) => {
                        onMultiSelectChangeHandler(newValue)
                        changed && setIsChanged(changed)
                    }} /></td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }} onClick={onSaveHandler}>Save Meta Data</Button>
        </div>
    </>;

};

export default UserDetailsTable;