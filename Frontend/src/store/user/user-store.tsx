import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tUser } from '../../types/authentication'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'

type SliceState = {
    users: tUser[] | null,
    userStatus: tPaginationDTO<tEnum> | null,
    roles: tPaginationDTO<tEnum> | null,
    privileges: tPaginationDTO<tEnum> | null
}

const initialState: SliceState = {
    users: null,
    userStatus: null,
    roles: null,
    privileges: null
}

export const userSlice = createSlice({
    name: 'users',
    initialState: initialState,
    reducers: {
        storeUsers: (state, action: PayloadAction<tUser[]>) => {
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
        addUser: (state, action: PayloadAction<tUser>) => {
            const newUser = action.payload;
            if (state.users) {
                const existingUser = state.users.find(users => users.id === newUser.id);
                if (!existingUser) {
                    state.users = state.users.concat(newUser);
                }
            } else {
                state.users = [newUser];
            }
        },
        changeRole: (state, action: PayloadAction<tUser>) => {
            const existingUser = state.users?.findIndex(user => user.id === action.payload.id);

            if (existingUser && existingUser > -1) {
                const newUsers = state.users;
                newUsers![existingUser] = action.payload;
                state.users = newUsers;
            }
        }
    }
})

export const {
    storeUsers,
    addUser,
    changeRole,
    storeUserStatus,
    storeRoles,
    storePrivileges
} = userSlice.actions

export const teamStore = configureStore({
    reducer: userSlice.reducer
})

export type UserState = ReturnType<typeof userSlice.reducer>