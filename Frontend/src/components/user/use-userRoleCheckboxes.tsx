import { ChangeEvent, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { changeUserStatus } from "../../store/user/user-store-actions";
import { Role } from "../../types/enums/role";
import { tUserDetail } from "../../types/user";

const UserRoleCheckboxes: React.FC<{
    user: tUserDetail,
    role: Role
}> = (props) => {

    const user = props.user;
    const role = props.role;

    const initialRoleState = !!user.roles.find(currentRole => currentRole.name === role);
    const dispatch = useDispatch();

    const [roleState, setRoleState] = useState<boolean>(initialRoleState);
    const [sendRequest, setSendRequest] = useState<boolean>(false);

    useEffect(() => {
        if (sendRequest) {
            dispatch(changeUserStatus(user.userId, role, roleState));
            setSendRequest(false);
        }
    }, [sendRequest, roleState, user.userId, role, dispatch])

    const onToggleRoleHandler = (event: ChangeEvent<HTMLInputElement>) => {
       setRoleState(oldRoleState => !oldRoleState);
    
       setSendRequest(true);
    }

    return <input type="checkbox" defaultChecked={roleState} onChange={onToggleRoleHandler} />;

};

export default UserRoleCheckboxes;