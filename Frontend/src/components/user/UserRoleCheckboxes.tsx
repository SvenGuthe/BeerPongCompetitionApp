import { ChangeEvent, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { tUser } from "../../types/authentication";
import { tSecurityRole } from "../../types/enums/securityRole";

const UserRoleCheckboxes: React.FC<{
    user: tUser,
    role: tSecurityRole
}> = (props) => {

    const user = props.user;
    const role = props.role;

    const initialRoleState = !!user.roles.find(currentRole => currentRole.role === role);
    const dispatch = useDispatch();

    const [roleState, setRoleState] = useState<boolean>(initialRoleState);
    const [sendRequest, setSendRequest] = useState<boolean>(false);

    useEffect(() => {
        if (sendRequest) {
            // Send Change Role Rquest
            setSendRequest(false);
        }
    }, [sendRequest, roleState, user.id, role, dispatch])

    const onToggleRoleHandler = (event: ChangeEvent<HTMLInputElement>) => {
       setRoleState(oldRoleState => !oldRoleState);
       setSendRequest(true);
    }

    return <input type="checkbox" defaultChecked={roleState} onChange={onToggleRoleHandler} />;

};

export default UserRoleCheckboxes;