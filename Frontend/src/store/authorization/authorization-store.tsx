import { createSlice, configureStore, PayloadAction } from "@reduxjs/toolkit";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";

type SliceState = {
  aclClasses: tPaginationDTO<tEnum> | null;
};

const initialState: SliceState = {
  aclClasses: null,
};

export const authorizationSlice = createSlice({
  name: "authorization",
  initialState: initialState,
  reducers: {
    storeACLClasses: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
      state.aclClasses = action.payload;
    },
  },
});

export const { storeACLClasses } = authorizationSlice.actions;

export const teamStore = configureStore({
  reducer: authorizationSlice.reducer,
});

export type AuthorizationState = ReturnType<typeof authorizationSlice.reducer>;
