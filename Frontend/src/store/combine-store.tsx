import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { userSlice } from "./user-store";

const store = configureStore({
    reducer: {
        user: userSlice.reducer,
    },
})

export const rootReducer = combineReducers({
    user: userSlice.reducer,
});

export type RootState = ReturnType<typeof rootReducer>

export default store;