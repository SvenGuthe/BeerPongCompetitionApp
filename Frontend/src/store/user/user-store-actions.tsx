import axios from "axios";
import { Dispatch } from "react";
import { tConfirmationTokenAdd, tUserUpdate } from "../../types/authentication";
import { updateUser as updateUserState, addConfirmationToken as addConfirmationTokenState } from "./user-store";

export const updateUser = (metaData: tUserUpdate) => {

    return async (dispatch: Dispatch<any>) => {
        console.log("Send /authentication/user [PUT] Request");
        const sendRequest = async () => await axios.put('/authentication/user', metaData).then((response) => {
            dispatch(updateUserState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}

export const addConfirmationToken = (confirmationTokenAdd: tConfirmationTokenAdd) => {    
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /authentication/confirmationtoken [POST] Request");
        const sendRequest = async () => await axios.post('/authentication/confirmationtoken', confirmationTokenAdd).then((response) => {
            dispatch(addConfirmationTokenState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}