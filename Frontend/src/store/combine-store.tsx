import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { teamSlice } from "./team-store";
import { userSlice } from "./user-store";

const store = configureStore({
    reducer: {
        user: userSlice.reducer,
        team: teamSlice.reducer
    },
})

export const rootReducer = combineReducers({
    user: userSlice.reducer,
    team: teamSlice.reducer
});

export type RootState = ReturnType<typeof rootReducer>

export default store;