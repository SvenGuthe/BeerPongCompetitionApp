import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tUser, tUserDetail } from '../../types/authentication'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'

type SliceState = {
    users: tPaginationDTO<tUser> | null,
    userStatus: tPaginationDTO<tEnum> | null,
    roles: tPaginationDTO<tEnum> | null,
    privileges: tPaginationDTO<tEnum> | null,
    userDetail: tUserDetail | null
}

const initialState: SliceState = {
    users: null,
    userStatus: null,
    roles: null,
    privileges: null,
    userDetail: null
}

export const userSlice = createSlice({
    name: 'users',
    initialState: initialState,
    reducers: {
        storeUsers: (state, action: PayloadAction<tPaginationDTO<tUser>>) => {
            state.users = action.payload;
        },
        storeUserStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.userStatus = action.payload;
        },
        storeRoles: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.roles = action.payload;
        },
        storePrivileges: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.privileges = action.payload;
        },
        storeUserDetail: (state, action: PayloadAction<tUserDetail>) => {
            state.userDetail = action.payload;
        },
        addUser: (state, action: PayloadAction<tUserDetail>) => {
            const newUser = action.payload.user;
            
            if (state.users) {
                const existingUser = state.users.data.find(users => users.id === newUser.id);
                if (!existingUser) {
                    state.users.data = state.users.data.concat(newUser);
                }
            } else {
                state.users = {
                    size: 1,
                    pages: 1,
                    data: [newUser]
                };
            }
        }
    }
})

export const {
    storeUsers,
    addUser,
    storeUserStatus,
    storeRoles,
    storePrivileges,
    storeUserDetail
} = userSlice.actions

export const teamStore = configureStore({
    reducer: userSlice.reducer
})

export type UserState = ReturnType<typeof userSlice.reducer>