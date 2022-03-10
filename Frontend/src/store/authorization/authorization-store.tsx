import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tPaginationDTO } from '../../types/defaults/generics'
import { tEnum } from '../../types/enum'

type SliceState = {
    aclClasses: tPaginationDTO<tEnum> | null
}

const initialState: SliceState = {
    aclClasses: null
}

export const authorizationSlice = createSlice({
    name: 'authorization',
    initialState: initialState,
    reducers: {
        storeACLClasses: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.aclClasses = action.payload;
        }
    }
})

export const {
    storeACLClasses
} = authorizationSlice.actions

export const teamStore = configureStore({
    reducer: authorizationSlice.reducer
})

export type AuthorizationState = ReturnType<typeof authorizationSlice.reducer>