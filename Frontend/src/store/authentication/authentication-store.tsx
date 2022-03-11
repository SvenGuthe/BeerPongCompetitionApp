import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import axios from 'axios';
import { tUser } from '../../types/authentication';

type SliceState = {
    redirectToHome: boolean,
    redirectToConfirmWait: boolean,
    authenticatedUser: tUser | null,
    loadAuthentication: boolean,
    registeredUser: tUser | null,
    confirmedUser: tUser | null
}

const initialState: SliceState = {
    redirectToHome: false,
    redirectToConfirmWait: false,
    authenticatedUser: null,
    loadAuthentication: false,
    registeredUser: null,
    confirmedUser: null
}

export const authenticationSlice = createSlice({
    name: 'authenticationinformation',
    initialState: initialState,
    reducers: {
        setLoading: (state, action: PayloadAction<boolean>) => {
            state.loadAuthentication = action.payload;
        },
        login: (state, action: PayloadAction<{
            userDetail: tUser | null,
            token: string
        }>) => {
            localStorage.setItem('token', action.payload.token);
            state.authenticatedUser = action.payload.userDetail;
            state.redirectToHome = true;
            axios.defaults.headers.common['Authorization'] =  `Bearer ${action.payload.token}`;
        },
        afterLoginCleanup: state => {
            state.redirectToHome = false;
        },
        logout: state => {
            localStorage.removeItem('token');
            state.authenticatedUser = null;
            axios.defaults.headers.common['Authorization'] =  false;
        },
        setAuthenticatedUser: (state, action: PayloadAction<tUser | null>) => {
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

export const { setLoading, login, logout, afterLoginCleanup, setAuthenticatedUser, register, afterRegisterCleanup, confirm } = authenticationSlice.actions

export const authenticationStore = configureStore({
    reducer: authenticationSlice.reducer
})

export type AuthenticationState = ReturnType<typeof authenticationSlice.reducer>