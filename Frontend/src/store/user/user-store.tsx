import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tEnum } from '../../types/enum'
import { tUserDetail } from '../../types/user'

type SliceState = {
    users: tUserDetail[] | null,
    userStatus: tEnum[] | null,
    roles: tEnum[] | null,
    privileges: tEnum[] | null
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
        storeUsers: (state, action: PayloadAction<tUserDetail[]>) => {
            state.users = action.payload;
        },
        storeUserStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.userStatus = action.payload;
        },
        storeRoles: (state, action: PayloadAction<tEnum[]>) => {
            state.roles = action.payload;
        },
        storePrivileges: (state, action: PayloadAction<tEnum[]>) => {
            state.privileges = action.payload;
        },
        addUser: (state, action: PayloadAction<tUserDetail>) => {
            const newUser = action.payload;
            if (state.users) {
                const existingUser = state.users.find(users => users.userId === newUser.userId);
                if (!existingUser) {
                    state.users = state.users.concat(newUser);
                }
            } else {
                state.users = [newUser];
            }
        },
        changeRole: (state, action: PayloadAction<tUserDetail>) => {
            const existingUser = state.users?.findIndex(user => user.userId === action.payload.userId);

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