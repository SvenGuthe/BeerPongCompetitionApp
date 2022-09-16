import { combineReducers, configureStore } from "@reduxjs/toolkit";
import { teamSlice } from "./team/team-store";
import { authenticationSlice } from "./authentication/authentication-store";
import { userSlice } from "./user/user-store";
import { competitionSlice } from "./competition/competition-store";
import { authorizationSlice } from "./authorization/authorization-store";

const store = configureStore({
  reducer: {
    authentication: authenticationSlice.reducer,
    team: teamSlice.reducer,
    user: userSlice.reducer,
    competition: competitionSlice.reducer,
    authorization: authorizationSlice.reducer,
  },
});

export const rootReducer = combineReducers({
  authentication: authenticationSlice.reducer,
  team: teamSlice.reducer,
  user: userSlice.reducer,
  competition: competitionSlice.reducer,
  authorization: authorizationSlice.reducer,
});

export type RootState = ReturnType<typeof rootReducer>;

export default store;
