import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { teamSlice } from "./team/team-store";
import { authenticationSlice } from "./authentication/authentication-store";
import { userSlice } from "./user/user-store";

const store = configureStore({
    reducer: {
        authentication: authenticationSlice.reducer,
        team: teamSlice.reducer,
        user: userSlice.reducer
    },
})

export const rootReducer = combineReducers({
    authentication: authenticationSlice.reducer,
    team: teamSlice.reducer,
    user: userSlice.reducer
});

export type RootState = ReturnType<typeof rootReducer>

export default store;