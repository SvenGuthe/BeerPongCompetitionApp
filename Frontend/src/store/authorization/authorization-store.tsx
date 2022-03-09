import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tPaginationDTO } from '../../types/enum'

type SliceState = {
    aclClasses: tPaginationDTO | null
}

const initialState: SliceState = {
    aclClasses: null
}

export const authorizationSlice = createSlice({
    name: 'authorization',
    initialState: initialState,
    reducers: {
        storeACLClasses: (state, action: PayloadAction<tPaginationDTO>) => {
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