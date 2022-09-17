import { createSlice, configureStore, PayloadAction } from "@reduxjs/toolkit";
import axios from "axios";
import tJwtResponse from "../../types/authentication/jwtResponse";
import tUser from "../../types/user/user";

type SliceState = {
  redirectToHome: boolean;
  redirectToConfirmWait: boolean;
  authenticatedUser: tUser | null;
  loadAuthentication: boolean;
  registeredUser: tUser | null;
  confirmedUser: tUser | null;
};

const initialState: SliceState = {
  redirectToHome: false,
  redirectToConfirmWait: false,
  authenticatedUser: null,
  loadAuthentication: false,
  registeredUser: null,
  confirmedUser: null,
};

export const authenticationSlice = createSlice({
  name: "authenticationinformation",
  initialState: initialState,
  reducers: {
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loadAuthentication = action.payload;
    },
    login: (
      state,
      action: PayloadAction<{
        jwtResponse: tJwtResponse;
      }>
    ) => {
      localStorage.setItem("token", action.payload.jwtResponse.jwtToken);
      state.authenticatedUser = action.payload.jwtResponse.user;
      state.redirectToHome = true;
      axios.defaults.headers.common[
        "Authorization"
      ] = `Bearer ${action.payload.jwtResponse.jwtToken}`;
    },
    afterLoginCleanup: (state) => {
      state.redirectToHome = false;
    },
    logout: (state) => {
      localStorage.removeItem("token");
      state.authenticatedUser = null;
      axios.defaults.headers.common["Authorization"] = false;
    },
    setAuthenticatedUser: (state, action: PayloadAction<tUser | null>) => {
      state.authenticatedUser = action.payload;
    },
    register: (state, action: PayloadAction<tUser>) => {
      state.redirectToConfirmWait = true;
      state.registeredUser = action.payload;
    },
    afterRegisterCleanup: (state) => {
      state.redirectToConfirmWait = false;
    },
    confirm: (state, action: PayloadAction<tUser>) => {
      state.confirmedUser = action.payload;
    },
  },
});

export const {
  setLoading,
  login,
  logout,
  afterLoginCleanup,
  setAuthenticatedUser,
  register,
  afterRegisterCleanup,
  confirm,
} = authenticationSlice.actions;

export const authenticationStore = configureStore({
  reducer: authenticationSlice.reducer,
});

export type AuthenticationState = ReturnType<
  typeof authenticationSlice.reducer
>;
