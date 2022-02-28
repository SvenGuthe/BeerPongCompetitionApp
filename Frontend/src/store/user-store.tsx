import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tToken } from '../types/authenticate';
import { tAuthenticatedUser } from '../types/user';

type SliceState = {
    loggedIn: boolean,
    redirectToHome: boolean,
    redirectToConfirmWait: boolean,
    authenticatedUser: tAuthenticatedUser | null,
    registeredUser: tAuthenticatedUser | null,
    confirmedUser: tAuthenticatedUser | null,
    token: string
}

const initialState: SliceState = {
    loggedIn: false,
    redirectToHome: false,
    redirectToConfirmWait: false,
    authenticatedUser: null,
    registeredUser: null,
    confirmedUser: null,
    token: ""
}

export const userSlice = createSlice({
    name: 'userinformation',
    initialState: initialState,
    reducers: {
        instantiation: state => {
            const token: string | null = localStorage.getItem('token');
            if (token) {
                state.loggedIn = true;
                state.token = token;
            } else {
                state.loggedIn = false;
            }
        },
        login: (state, action: PayloadAction<tToken>) => {
            localStorage.setItem('token', action.payload.token);
            state.token = action.payload.token;
            state.redirectToHome = true;
            state.loggedIn = true;
        },
        afterLoginCleanup: state => {
            state.redirectToHome = false;
        },
        logout: state => {
            localStorage.removeItem('token');
            state.loggedIn = false;
            state.token = "";
            state.authenticatedUser = null;
        },
        setAuthenticatedUser: (state, action: PayloadAction<tAuthenticatedUser>) => {
            state.authenticatedUser = action.payload
        },
        register: (state, action: PayloadAction<tAuthenticatedUser>) => {
            state.redirectToConfirmWait = true;
            state.registeredUser = action.payload;
        },
        afterRegisterCleanup: state => {
            state.redirectToConfirmWait = false;
        },
        confirm: (state, action: PayloadAction<tAuthenticatedUser>) => {
            state.confirmedUser = action.payload
        }
    }
})

export const { login, logout, afterLoginCleanup, instantiation, setAuthenticatedUser, register, afterRegisterCleanup, confirm } = userSlice.actions

export const userStore = configureStore({
    reducer: userSlice.reducer
})

export type UserState = ReturnType<typeof userSlice.reducer>