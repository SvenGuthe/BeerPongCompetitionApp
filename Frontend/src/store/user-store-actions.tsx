import { Dispatch } from "react";
import axios from "axios";
import { login, register, setAuthenticatedUser, confirm, validateToken } from "./user-store";
import { tRegister } from "../types/authenticate";

export const sendLoginRequest = (email: String, password: String) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.post('/authentication/login', {
            username: email,
            password: password
        }).then((response) => {
            dispatch(login({
                token: response.data.token,
                privileges: response.data.privileges,
                roles: response.data.roles
            }))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const sendAuthenticatedUserRequest = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/authentication/authenticateduser').then((response) => {
            dispatch(setAuthenticatedUser(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const sendRegisterRequest = (registerFormData: tRegister) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.post('/authentication/register', {
            ...registerFormData
        }).then((response) => {
            dispatch(register(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const sendConfirmRequest = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/authentication/confirm-account?token=' + token).then((response) => {
            dispatch(confirm(response.data))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const checkToken = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/authentication/checktoken').then((response) => {
            dispatch(validateToken(true));
        }).catch(function (error) {
            dispatch(validateToken(false));
            console.log(error);
        });

        return sendRequest();
    }
}
