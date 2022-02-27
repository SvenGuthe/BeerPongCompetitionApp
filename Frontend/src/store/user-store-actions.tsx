import { Dispatch } from "react";
import axios from "axios";
import { login, setAuthenticatedUser } from "./user-store";

export const sendLoginRequest = (email: String, password: String) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.post('http://localhost:9999/authenticate', {
            username: email,
            password: password
        }).then((response) => {
            dispatch(login({ token: response.data.token }))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const sendAuthenticatedUserRequest = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('http://localhost:9999/authenticateduser', {
            headers: { Authorization: `Bearer ${token}` }
        }).then((response) => {
            dispatch(setAuthenticatedUser(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}