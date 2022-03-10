import axios from "axios"
import { Dispatch } from "react"
import { Role } from "../../types/enums/role"
import { changeRole } from "./user-store"

export const changeUserStatus = (id: number, role: Role, value: boolean) => {
    return async (dispatch: Dispatch<any>) => {

        console.log("Send /authentication/user [PUT] Request");
        
        const sendRequest = async () => await axios.put('/authentication/user', {
            id,
            role,
            value
        }).then((response) => {
            dispatch(changeRole(response.data))
        }).catch((error) => {
            console.log(error);
        });

        return sendRequest();
    }
}