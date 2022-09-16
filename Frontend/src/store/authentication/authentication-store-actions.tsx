import { Dispatch } from "react";
import axios from "axios";
import {
  login,
  register,
  confirm,
  setAuthenticatedUser,
  setLoading,
} from "./authentication-store";
import { tRegister } from "../../types/authentication";
import {
  authenticatedUserRoute,
  confirmRoute,
  loginRoute,
  registerRoute,
} from "../../api-routes/authentication";

export const sendLoginRequest = (email: String, password: String) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${loginRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(loginRoute, {
          username: email,
          password: password,
        })
        .then((response) => {
          dispatch(
            login({
              token: response.data.jwtToken,
              userDetail: response.data.userDetailDTO,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const sendRegisterRequest = (registerFormData: tRegister) => {
  console.log(`Send ${registerRoute} [POST] Request`);
  return async (dispatch: Dispatch<any>) => {
    const sendRequest = async () =>
      await axios
        .post(registerRoute, {
          ...registerFormData,
        })
        .then((response) => {
          dispatch(register(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const sendConfirmRequest = (token: string) => {
  console.log(`Send ${confirmRoute} [GET] Request`);
  return async (dispatch: Dispatch<any>) => {
    const sendRequest = async () =>
      await axios
        .get(`${confirmRoute}?token=${token}`)
        .then((response) => {
          dispatch(confirm(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const sendAuthenticationRequest = () => {
  console.log(`Send ${authenticatedUserRoute} [GET] Request`);
  return async (dispatch: Dispatch<any>) => {
    const sendRequest = async () =>
      await axios
        .get(authenticatedUserRoute)
        .then((response) => {
          dispatch(setAuthenticatedUser(response.data));
        })
        .catch(function (error) {
          dispatch(setAuthenticatedUser(null));
          console.log(error);
        })
        .then(() => {
          dispatch(setLoading(false));
        });

    return sendRequest();
  };
};
