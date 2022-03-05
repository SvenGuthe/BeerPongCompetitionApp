import { ChangeEvent, useEffect, useState } from "react";
import { changeUserStatus } from "../store/user/user-store-actions";
import { Role } from "../types/enums/role";
import { tUserDetail } from "../types/user";

export const useUserRoleCheckboxes = (user: tUserDetail, role: Role) => {

    const initialRoleState = !!user.roles.find(currentRole => currentRole.name === role);

    const [roleState, setRoleState] = useState<boolean>(initialRoleState);
    const [sendRequest, setSendRequest] = useState<boolean>(false);

    useEffect(() => {
        if (sendRequest) {
            changeUserStatus(user.userId, role, roleState);
            setSendRequest(false);
        }
    }, [sendRequest, roleState, user.userId, role])

    const onToggleRoleHandler = (event: ChangeEvent<HTMLInputElement>) => {
       setRoleState(oldRoleState => !oldRoleState);
       setSendRequest(true);
    }

    return <input type="checkbox" defaultChecked={roleState} onChange={onToggleRoleHandler} />;

};

export default useUserRoleCheckboxes;