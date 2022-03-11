import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import axios from 'axios';
import { tUser } from '../../types/authentication';

type SliceState = {
    loggedIn: boolean | null,
    redirectToHome: boolean,
    redirectToConfirmWait: boolean,
    authenticatedUser: tUser | null,
    loadAuthentication: boolean,
    registeredUser: tUser | null,
    confirmedUser: tUser | null,
    token: string | null
}

const initialState: SliceState = {
    loggedIn: null,
    redirectToHome: false,
    redirectToConfirmWait: false,
    authenticatedUser: null,
    loadAuthentication: false,
    registeredUser: null,
    confirmedUser: null,
    token: null
}

export const authenticationSlice = createSlice({
    name: 'authenticationinformation',
    initialState: initialState,
    reducers: {
        setLoading: (state, action: PayloadAction<boolean>) => {
            state.loadAuthentication = action.payload;
        },
        instantiation: (state) => {
            const token = localStorage.getItem('token');
            if (token) {
                state.token = token;
                axios.defaults.headers.common['Authorization'] =  `Bearer ${token}`;
            }
        },
        validateToken: (state, action: PayloadAction<tUser | null>) => {
            if (action.payload) {
                state.loggedIn = true;
            } else {
                localStorage.removeItem('token');
                state.loggedIn = false;
                state.token = null;
                state.authenticatedUser = null;
                axios.defaults.headers.common['Authorization'] =  false;
            }
        },
        login: (state, action: PayloadAction<{
            userDetail: tUser | null,
            token: string
        }>) => {
            localStorage.setItem('token', action.payload.token);
            state.token = action.payload.token;
            state.authenticatedUser = action.payload.userDetail;
            state.redirectToHome = true;
            state.loggedIn = true;
            axios.defaults.headers.common['Authorization'] =  `Bearer ${action.payload.token}`;
        },
        afterLoginCleanup: state => {
            state.redirectToHome = false;
        },
        logout: state => {
            localStorage.removeItem('token');
            state.loggedIn = false;
            state.token = null;
            state.authenticatedUser = null;
            axios.defaults.headers.common['Authorization'] =  false;
        },
        setAuthenticatedUser: (state, action: PayloadAction<tUser>) => {
            state.authenticatedUser = action.payload
        },
        register: (state, action: PayloadAction<tUser>) => {
            state.redirectToConfirmWait = true;
            state.registeredUser = action.payload;
        },
        afterRegisterCleanup: state => {
            state.redirectToConfirmWait = false;
        },
        confirm: (state, action: PayloadAction<tUser>) => {
            state.confirmedUser = action.payload
        }
    }
})

export const { setLoading, login, logout, afterLoginCleanup, instantiation, setAuthenticatedUser, register, afterRegisterCleanup, confirm, validateToken } = authenticationSlice.actions

export const authenticationStore = configureStore({
    reducer: authenticationSlice.reducer
})

export type AuthenticationState = ReturnType<typeof authenticationSlice.reducer>