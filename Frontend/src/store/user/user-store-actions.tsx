import axios from "axios";
import { Dispatch } from "react";
import { confirmationTokenRoute, userRoute } from "../../api-routes/user";
import { tConfirmationTokenAdd, tUserUpdate } from "../../types/user";
import { updateUser as updateUserState, addConfirmationToken as addConfirmationTokenState } from "./user-store";

export const updateUser = (user: tUserUpdate) => {

    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${userRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(userRoute, user).then((response) => {
            dispatch(updateUserState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}

export const addConfirmationToken = (confirmationToken: tConfirmationTokenAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${confirmationTokenRoute} [POST] Request`);
        const sendRequest = async () => await axios.post(confirmationTokenRoute, confirmationToken).then((response) => {
            dispatch(addConfirmationTokenState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}