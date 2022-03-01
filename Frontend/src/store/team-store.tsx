import { createSlice, configureStore } from '@reduxjs/toolkit'

type SliceState = {

}

const initialState: SliceState = {

}

export const teamSlice = createSlice({
    name: 'teams',
    initialState: initialState,
    reducers: {
        test: (state, action) => {
            console.log(action);
        }
    }
})

export const { test } = teamSlice.actions

export const teamStore = configureStore({
    reducer: teamSlice.reducer
})

export type UserState = ReturnType<typeof teamSlice.reducer>