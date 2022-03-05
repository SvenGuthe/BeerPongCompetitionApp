import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import axios from 'axios';
import { tPrivilege, tRole, tToken } from '../../types/authenticate';
import { tUserDetail } from '../../types/user';

type SliceState = {
    loggedIn: boolean | null,
    redirectToHome: boolean,
    redirectToConfirmWait: boolean,
    authenticatedUser: tUserDetail | null,
    registeredUser: tUserDetail | null,
    confirmedUser: tUserDetail | null,
    token: string | null,
    privileges: tPrivilege[] | null,
    roles: tRole[] | null
}

const initialState: SliceState = {
    loggedIn: null,
    redirectToHome: false,
    redirectToConfirmWait: false,
    authenticatedUser: null,
    registeredUser: null,
    confirmedUser: null,
    token: null,
    privileges: null,
    roles: null
}

export const authenticationSlice = createSlice({
    name: 'authenticationinformation',
    initialState: initialState,
    reducers: {
        instantiation: (state) => {
            const token = localStorage.getItem('token');
            if (token) {
                state.token = token;
                axios.defaults.headers.common['Authorization'] =  `Bearer ${token}`;
            }
            const privileges = localStorage.getItem('privileges');
            if (privileges) {
                state.privileges = JSON.parse(privileges);
            }
            const roles = localStorage.getItem('roles');
            if (roles) {
                state.roles = JSON.parse(roles);
            }
        },
        validateToken: (state, action: PayloadAction<tUserDetail | null>) => {
            if (action.payload) {
                state.loggedIn = true;
            } else {
                localStorage.removeItem('token');
                localStorage.removeItem('privileges');
                localStorage.removeItem('roles');
                state.loggedIn = false;
                state.token = null;
                state.privileges = null;
                state.roles = null;
                axios.defaults.headers.common['Authorization'] =  false;
            }
        },
        login: (state, action: PayloadAction<tToken>) => {
            localStorage.setItem('token', action.payload.token);
            localStorage.setItem('privileges', JSON.stringify(action.payload.privileges));
            localStorage.setItem('roles', JSON.stringify(action.payload.roles));
            state.token = action.payload.token;
            state.privileges = action.payload.privileges;
            state.roles = action.payload.roles;
            state.redirectToHome = true;
            state.loggedIn = true;
            axios.defaults.headers.common['Authorization'] =  `Bearer ${action.payload.token}`;
        },
        afterLoginCleanup: state => {
            state.redirectToHome = false;
        },
        logout: state => {
            localStorage.removeItem('token');
            localStorage.removeItem('privileges');
            localStorage.removeItem('roles');
            state.loggedIn = false;
            state.token = null;
            state.authenticatedUser = null;
            state.privileges = null;
            state.roles = null;
            axios.defaults.headers.common['Authorization'] =  false;
        },
        setAuthenticatedUser: (state, action: PayloadAction<tUserDetail>) => {
            state.authenticatedUser = action.payload
        },
        register: (state, action: PayloadAction<tUserDetail>) => {
            state.redirectToConfirmWait = true;
            state.registeredUser = action.payload;
        },
        afterRegisterCleanup: state => {
            state.redirectToConfirmWait = false;
        },
        confirm: (state, action: PayloadAction<tUserDetail>) => {
            state.confirmedUser = action.payload
        }
    }
})

export const { login, logout, afterLoginCleanup, instantiation, setAuthenticatedUser, register, afterRegisterCleanup, confirm, validateToken } = authenticationSlice.actions

export const authenticationStore = configureStore({
    reducer: authenticationSlice.reducer
})

export type AuthenticationState = ReturnType<typeof authenticationSlice.reducer>