import axios from "axios";
import { Dispatch } from "react";
import { tConfirmationTokenAdd, tUserUpdate } from "../../types/authentication";
import { updateUser as updateUserState, addConfirmationToken as addConfirmationTokenState } from "./user-store";

export const updateUser = (user: tUserUpdate) => {

    return async (dispatch: Dispatch<any>) => {
        console.log("Send /authentication/user [PUT] Request");
        const sendRequest = async () => await axios.put('/authentication/user', user).then((response) => {
            dispatch(updateUserState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}

export const addConfirmationToken = (confirmationToken: tConfirmationTokenAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /authentication/confirmationtoken [POST] Request");
        const sendRequest = async () => await axios.post('/authentication/confirmationtoken', confirmationToken).then((response) => {
            dispatch(addConfirmationTokenState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}