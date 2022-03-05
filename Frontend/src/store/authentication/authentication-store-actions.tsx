import { Dispatch } from "react";
import axios from "axios";
import { login, register, confirm, validateToken } from "./authentication-store";
import { tRegister } from "../../types/authenticate";

export const sendLoginRequest = (email: String, password: String) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /authentication/login [POST] Request");
        const sendRequest = async () => await axios.post('/authentication/login', {
            username: email,
            password: password
        }).then((response) => {
            dispatch(login({
                token: response.data.jwtToken,
                userDetail: response.data.userDetailDTO
            }))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const sendRegisterRequest = (registerFormData: tRegister) => {
    console.log("Send /authentication/register [POST] Request");
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
    console.log("Send /authentication/confirm-account [GET] Request");
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/authentication/confirm-account?token=' + token).then((response) => {
            dispatch(confirm(response.data))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const checkToken = () => {
    console.log("Send /authentication/authenticateduser [GET] Request");
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/authentication/authenticateduser').then((response) => {
            dispatch(validateToken(response.data));
        }).catch(function (error) {
            dispatch(validateToken(null));
            console.log(error);
        });

        return sendRequest();
    }
}
