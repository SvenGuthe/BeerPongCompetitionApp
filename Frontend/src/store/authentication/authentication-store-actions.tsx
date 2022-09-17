import { Dispatch } from "react";
import axios from "axios";
import {
  login,
  register,
  confirm,
  setAuthenticatedUser,
  setLoading,
} from "./authentication-store";
import {
  authenticatedUserRoute,
  confirmRoute,
  loginRoute,
  registerRoute,
} from "../../api-routes/authentication";
import tUserRegistration from "../../types/user/userRegistration";
import tJwtRequest from "../../types/authentication/jwtRequest";
import tUser from "../../types/user/user";
import tJwtResponse from "../../types/authentication/jwtResponse";

export const sendLoginRequest = (authenticationRequest: tJwtRequest) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${loginRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(loginRoute, authenticationRequest)
        .then((response) => {
          const jwtToken: string = response.data.jwtToken;
          const user: tUser = response.data.userDTO;
          const jwtResponse: tJwtResponse = {
            jwtToken,
            user,
          };
          dispatch(
            login({
              jwtResponse,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const sendRegisterRequest = (userRegistrationDTO: tUserRegistration) => {
  console.log(`Send ${registerRoute} [POST] Request`);
  return async (dispatch: Dispatch<any>) => {
    const sendRequest = async () =>
      await axios
        .post(registerRoute, userRegistrationDTO)
        .then((response) => {
          const user: tUser = response.data;
          dispatch(register(user));
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
          const user: tUser = response.data;
          dispatch(confirm(user));
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
          const user: tUser = response.data;
          dispatch(setAuthenticatedUser(user));
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
