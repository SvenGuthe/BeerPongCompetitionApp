import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tLogin } from '../types/authenticate';
import { tAuthenticatedUser } from '../types/user';

type SliceState = {
    loggedIn: boolean,
    redirect: boolean,
    authenticatedUser: tAuthenticatedUser | null,
    token: string
}

const initialState: SliceState = {
    loggedIn: false,
    redirect: false,
    authenticatedUser: null,
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
        login: (state, action: PayloadAction<tLogin>) => {
            localStorage.setItem('token', action.payload.token);
            state.token = action.payload.token;
            state.redirect = true;
            state.loggedIn = true;
        },
        afterLoginCleanup: state => {
            state.redirect = false;
        },
        logout: state => {
            localStorage.removeItem('token');
            state.loggedIn = false;
            state.token = "";
            state.authenticatedUser = null;
        },
        setAuthenticatedUser: (state, action: PayloadAction<tAuthenticatedUser>) => {
            state.authenticatedUser = action.payload
        }
    }
})

export const { login, logout, afterLoginCleanup, instantiation, setAuthenticatedUser } = userSlice.actions

export const userStore = configureStore({
    reducer: userSlice.reducer
})

export type UserState = ReturnType<typeof userSlice.reducer>