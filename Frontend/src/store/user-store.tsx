import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tPrivilege, tRole, tToken } from '../types/authenticate';
import { tAuthenticatedUser } from '../types/user';

type SliceState = {
    loggedIn: boolean,
    redirectToHome: boolean,
    redirectToConfirmWait: boolean,
    authenticatedUser: tAuthenticatedUser | null,
    registeredUser: tAuthenticatedUser | null,
    confirmedUser: tAuthenticatedUser | null,
    token: string | null,
    privileges: tPrivilege[] | null,
    roles: tRole[] | null
}

const initialState: SliceState = {
    loggedIn: false,
    redirectToHome: false,
    redirectToConfirmWait: false,
    authenticatedUser: null,
    registeredUser: null,
    confirmedUser: null,
    token: null,
    privileges: null,
    roles: null
}

export const userSlice = createSlice({
    name: 'userinformation',
    initialState: initialState,
    reducers: {
        instantiation: (state) => {
            const token = localStorage.getItem('token');
            if (token) {
                state.token = token;
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
        validateToken: (state, action: PayloadAction<boolean>) => {
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

export const { login, logout, afterLoginCleanup, instantiation, setAuthenticatedUser, register, afterRegisterCleanup, confirm, validateToken } = userSlice.actions

export const userStore = configureStore({
    reducer: userSlice.reducer
})

export type UserState = ReturnType<typeof userSlice.reducer>