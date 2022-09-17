import { createSlice, configureStore, PayloadAction } from "@reduxjs/toolkit";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";
import tTeam from "../../types/team/team";
import tTeamComposition from "../../types/team/teamcomposition/teamComposition";
import tTeamCompositionStatus from "../../types/team/teamcomposition/teamCompositionStatus";
import tTeamDetail from "../../types/team/teamDetail";
import tTeamInvitationLink from "../../types/team/teaminvitationlink/teamInvitationLink";
import tTeamStatus from "../../types/team/teamstatus/teamStatus";
import tTeamUser from "../../types/user/teamUser";

type SliceState = {
  teams: tPaginationDTO<tTeam> | null;
  teamStatus: tPaginationDTO<tEnum> | null;
  teamCompositionStatus: tPaginationDTO<tEnum> | null;
  teamDetail: tTeamDetail | null;
};

const initialState: SliceState = {
  teams: null,
  teamStatus: null,
  teamCompositionStatus: null,
  teamDetail: null,
};

export const teamSlice = createSlice({
  name: "teams",
  initialState: initialState,
  reducers: {
    storeTeams: (state, action: PayloadAction<tPaginationDTO<tTeam>>) => {
      state.teams = action.payload;
    },
    removeTeams: (state) => {
      state.teams = null;
    },
    storeTeamStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
      state.teamStatus = action.payload;
    },
    storeTeamCompositionStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.teamCompositionStatus = action.payload;
    },
    storeTeamDetail: (state, action: PayloadAction<tTeamDetail>) => {
      state.teamDetail = action.payload;
    },
    removeTeamDetail: (state) => {
      state.teamDetail = null;
    },
    updateTeams: (state, action: PayloadAction<tTeam>) => {
      const fetchedTeam = action.payload;
      if (state.teams) {
        state.teams.data = state.teams.data.map((team) => {
          if (team.id === fetchedTeam.id) {
            return fetchedTeam;
          } else {
            return team;
          }
        });
      }
    },
    addTeam: (state, action: PayloadAction<tTeamDetail>) => {
      const newTeam = action.payload.team;
      if (state.teams) {
        const existingTeam = state.teams.data.find(
          (team) => team.id === newTeam.id
        );
        if (!existingTeam) {
          state.teams.data = state.teams.data.concat(newTeam);
        }
      } else {
        state.teams = {
          size: 1,
          pages: 1,
          data: [newTeam],
        };
      }
    },
    updateTeam: (state, action: PayloadAction<tTeam>) => {
      state.teamDetail!.team = action.payload;
    },
    updateTeamStatus: (state, action: PayloadAction<tTeamStatus[]>) => {
      const newTeamStatus = state.teamDetail!.team.teamStatus.map(
        (singleTeamStatus) => {
          if (singleTeamStatus.id === action.payload[0].id) {
            return action.payload[0];
          } else {
            return singleTeamStatus;
          }
        }
      );
      newTeamStatus.push(action.payload[1]);

      state.teamDetail!.team.teamStatus = newTeamStatus;
    },
    addTeamInvitationLink: (
      state,
      action: PayloadAction<tTeamInvitationLink>
    ) => {
      state.teamDetail?.team.teamInvitationLinks.push(action.payload);
    },
    updateTeamComposition: (state, action: PayloadAction<tTeamComposition>) => {
      const userId = action.payload.user.id;

      const newUsers = state.teamDetail!.users.map((user) => {
        if (user.id === userId) {
          user.isAdmin = action.payload.isAdmin;
        }
        return user;
      });

      state.teamDetail!.users = newUsers;
    },
    updateTeamCompositionStatus: (
      state,
      action: PayloadAction<tTeamCompositionStatus[]>
    ) => {
      const newUsers = state.teamDetail!.users.map((user) => {
        const newTeamCompositionStatus = user.teamCompositionStatus.map(
          (teamCompositionStatus) => {
            if (teamCompositionStatus.id === action.payload[0].id) {
              return action.payload[0];
            } else {
              return teamCompositionStatus;
            }
          }
        );

        if (
          user.teamCompositionStatus.find(
            (singleTeamCompositionStatus) =>
              singleTeamCompositionStatus.id === action.payload[0].id
          )
        ) {
          newTeamCompositionStatus.push(action.payload[1]);
        }

        const newUser: tTeamUser = {
          ...user,
          teamCompositionStatus: newTeamCompositionStatus,
        };

        return newUser;
      });

      state.teamDetail!.users = newUsers;
    },
    addTeamComposition: (state, action: PayloadAction<tTeamComposition>) => {
      const teamComposition = action.payload;

      state.teamDetail!.users.push({
        id: teamComposition.id,
        user: teamComposition.user,
        isAdmin: teamComposition.isAdmin,
        creationTime: teamComposition.creationTime,
        teamCompositionStatus: teamComposition.teamCompositionStatus,
      });

      state.teamDetail!.possibleUsers = state.teamDetail!.possibleUsers.filter(
        (possibleUser) => {
          return possibleUser.id !== teamComposition.user.id;
        }
      );
    },
  },
});

export const {
  storeTeams,
  updateTeams,
  addTeam,
  storeTeamStatus,
  storeTeamCompositionStatus,
  storeTeamDetail,
  removeTeamDetail,
  removeTeams,
  updateTeam,
  updateTeamStatus,
  addTeamInvitationLink,
  updateTeamComposition,
  updateTeamCompositionStatus,
  addTeamComposition,
} = teamSlice.actions;

export const teamStore = configureStore({
  reducer: teamSlice.reducer,
});

export type TeamState = ReturnType<typeof teamSlice.reducer>;
