import { createSlice, configureStore, PayloadAction } from "@reduxjs/toolkit";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";
import tConfirmationToken from "../../types/user/confirmationtoken/confirmationToken";
import tUser from "../../types/user/user";
import tUserDetail from "../../types/user/userDetail";

type SliceState = {
  users: tPaginationDTO<tUser> | null;
  userStatus: tPaginationDTO<tEnum> | null;
  roles: tPaginationDTO<tEnum> | null;
  privileges: tPaginationDTO<tEnum> | null;
  userDetail: tUserDetail | null;
};

const initialState: SliceState = {
  users: null,
  userStatus: null,
  roles: null,
  privileges: null,
  userDetail: null,
};

export const userSlice = createSlice({
  name: "users",
  initialState: initialState,
  reducers: {
    storeUsers: (state, action: PayloadAction<tPaginationDTO<tUser>>) => {
      state.users = action.payload;
    },
    removeUsers: (state) => {
      state.users = null;
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
    storeUserDetail: (state, action: PayloadAction<tUserDetail>) => {
      state.userDetail = action.payload;
    },
    removeUserDetail: (state) => {
      state.userDetail = null;
    },
    addUser: (state, action: PayloadAction<tUserDetail>) => {
      const newUser = action.payload.user;

      if (state.users) {
        const existingUser = state.users.data.find(
          (users) => users.id === newUser.id
        );
        if (!existingUser) {
          state.users.data = state.users.data.concat(newUser);
        }
      } else {
        state.users = {
          size: 1,
          pages: 1,
          data: [newUser],
        };
      }
    },
    updateUser: (state, action: PayloadAction<tUser>) => {
      state.userDetail!.user = action.payload;
    },
    addConfirmationToken: (
      state,
      action: PayloadAction<tConfirmationToken>
    ) => {
      state.userDetail?.user.confirmationToken.push(action.payload);
    },
    toggleConfirmationToken: (
      state,
      action: PayloadAction<tConfirmationToken>
    ) => {
      const confirmationToken = action.payload;
      const existingToken = state.userDetail?.user.confirmationToken.find(
        (token) => token.id === confirmationToken.id
      );

      if (existingToken) {
        state.userDetail!.user.confirmationToken =
          state.userDetail!.user.confirmationToken.map((token) => {
            if (token.id === confirmationToken.id) {
              return confirmationToken;
            } else {
              return token;
            }
          });
      } else {
        state.userDetail?.user.confirmationToken.push(action.payload);
      }
    },
  },
});

export const {
  storeUsers,
  addUser,
  storeUserStatus,
  storeRoles,
  storePrivileges,
  storeUserDetail,
  removeUserDetail,
  removeUsers,
  updateUser,
  addConfirmationToken,
  toggleConfirmationToken,
} = userSlice.actions;

export const teamStore = configureStore({
  reducer: userSlice.reducer,
});

export type UserState = ReturnType<typeof userSlice.reducer>;
