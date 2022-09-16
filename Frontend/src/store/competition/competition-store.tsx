import { createSlice, configureStore, PayloadAction } from "@reduxjs/toolkit";
import {
  tBillingStatus,
  tCompetition,
  tCompetitionAdmin,
  tCompetitionAdminStatus,
  tCompetitionDetail,
  tCompetitionPlayer,
  tCompetitionPlayerStatus,
  tCompetitionStatus,
  tCompetitionTeam,
  tRegistrationStatus,
} from "../../types/competition";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";

type SliceState = {
  competitions: tPaginationDTO<tCompetition> | null;
  competitionAdminStatus: tPaginationDTO<tEnum> | null;
  competitionStatus: tPaginationDTO<tEnum> | null;
  registrationStatus: tPaginationDTO<tEnum> | null;
  competitionPlayerStatus: tPaginationDTO<tEnum> | null;
  billingStatus: tPaginationDTO<tEnum> | null;
  competitionDetail: tCompetitionDetail | null;
};

const initialState: SliceState = {
  competitions: null,
  competitionAdminStatus: null,
  competitionStatus: null,
  registrationStatus: null,
  competitionPlayerStatus: null,
  billingStatus: null,
  competitionDetail: null,
};

export const competitionSlice = createSlice({
  name: "competitions",
  initialState: initialState,
  reducers: {
    storeCompetitions: (
      state,
      action: PayloadAction<tPaginationDTO<tCompetition>>
    ) => {
      state.competitions = action.payload;
    },
    removeCompetitions: (state) => {
      state.competitions = null;
    },
    storeCompetitionAdminStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.competitionAdminStatus = action.payload;
    },
    storeCompetitionStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.competitionStatus = action.payload;
    },
    storeRegistrationStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.registrationStatus = action.payload;
    },
    storeCompetitionPlayerStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.competitionPlayerStatus = action.payload;
    },
    storeBillingStatus: (
      state,
      action: PayloadAction<tPaginationDTO<tEnum>>
    ) => {
      state.billingStatus = action.payload;
    },
    addCompetition: (state, action: PayloadAction<tCompetitionDetail>) => {
      const newCompetition = action.payload;
      if (state.competitions) {
        const existingCompetition = state.competitions.data.find(
          (competition) => competition.id === newCompetition.competition.id
        );
        if (!existingCompetition) {
          state.competitions.data = state.competitions.data.concat(
            newCompetition.competition
          );
        }
      } else {
        state.competitions = {
          size: 1,
          pages: 1,
          data: [newCompetition.competition],
        };
      }
    },
    storeCompetitionDetail: (
      state,
      action: PayloadAction<tCompetitionDetail>
    ) => {
      state.competitionDetail = action.payload;
    },
    removeCompetitionDetail: (state) => {
      state.competitionDetail = null;
    },
    updateCompetitionStatus: (
      state,
      action: PayloadAction<tCompetitionStatus[]>
    ) => {
      const newCompetitionState =
        state.competitionDetail!.competition.competitionStatus.map(
          (singleCompetitionStatus) => {
            if (singleCompetitionStatus.id === action.payload[0].id) {
              return action.payload[0];
            } else {
              return singleCompetitionStatus;
            }
          }
        );
      newCompetitionState.push(action.payload[1]);

      state.competitionDetail!.competition.competitionStatus =
        newCompetitionState;
    },
    updateCompetitionAdminStatus: (
      state,
      action: PayloadAction<{
        competitionAdminStatus: tCompetitionAdminStatus[];
        competitionAdminId: number;
      }>
    ) => {
      const newCompetitionAdminState =
        state.competitionDetail!.competition.competitionAdmins.map(
          (singleCompetitionAdmin) => {
            if (
              singleCompetitionAdmin.id === action.payload.competitionAdminId
            ) {
              const newCompetitionAdminStatusState =
                singleCompetitionAdmin.competitionAdminStatus.map(
                  (singleCompetitionAdminStatus) => {
                    if (
                      singleCompetitionAdminStatus.id ===
                      action.payload.competitionAdminStatus[0].id
                    ) {
                      return action.payload.competitionAdminStatus[0];
                    } else {
                      return singleCompetitionAdminStatus;
                    }
                  }
                );

              newCompetitionAdminStatusState.push(
                action.payload.competitionAdminStatus[1]
              );

              const newSingleCompetitionAdmin: tCompetitionAdmin = {
                ...singleCompetitionAdmin,
                competitionAdminStatus: newCompetitionAdminStatusState,
              };

              return newSingleCompetitionAdmin;
            } else {
              return singleCompetitionAdmin;
            }
          }
        );

      state.competitionDetail!.competition.competitionAdmins =
        newCompetitionAdminState;
    },
    updateRegistrationStatus: (
      state,
      action: PayloadAction<{
        registrationStatus: tRegistrationStatus[];
        competitionTeamId: number;
      }>
    ) => {
      const newCompetitionTeamState =
        state.competitionDetail!.competition.competitionTeams.map(
          (singleCompetitionTeam) => {
            if (singleCompetitionTeam.id === action.payload.competitionTeamId) {
              const newRegistrationStatusState =
                singleCompetitionTeam.registrationStatus.map(
                  (singleRegistrationStatus) => {
                    if (
                      singleRegistrationStatus.id ===
                      action.payload.registrationStatus[0].id
                    ) {
                      return action.payload.registrationStatus[0];
                    } else {
                      return singleRegistrationStatus;
                    }
                  }
                );

              newRegistrationStatusState.push(
                action.payload.registrationStatus[1]
              );

              const newSingleCompetitionTeam: tCompetitionTeam = {
                ...singleCompetitionTeam,
                registrationStatus: newRegistrationStatusState,
              };

              return newSingleCompetitionTeam;
            } else {
              return singleCompetitionTeam;
            }
          }
        );

      state.competitionDetail!.competition.competitionTeams =
        newCompetitionTeamState;
    },
    updateBillingStatus: (
      state,
      action: PayloadAction<{
        billingStatus: tBillingStatus[];
        competitionTeamId: number;
      }>
    ) => {
      const newCompetitionTeamState =
        state.competitionDetail!.competition.competitionTeams.map(
          (singleCompetitionTeam) => {
            if (singleCompetitionTeam.id === action.payload.competitionTeamId) {
              const newBillingStatusState =
                singleCompetitionTeam.billingStatus.map(
                  (singleBillingStatus) => {
                    if (
                      singleBillingStatus.id ===
                      action.payload.billingStatus[0].id
                    ) {
                      return action.payload.billingStatus[0];
                    } else {
                      return singleBillingStatus;
                    }
                  }
                );

              newBillingStatusState.push(action.payload.billingStatus[1]);

              const newSingleCompetitionTeam: tCompetitionTeam = {
                ...singleCompetitionTeam,
                billingStatus: newBillingStatusState,
              };

              return newSingleCompetitionTeam;
            } else {
              return singleCompetitionTeam;
            }
          }
        );

      state.competitionDetail!.competition.competitionTeams =
        newCompetitionTeamState;
    },
    addCompetitionAdmin: (state, action: PayloadAction<tCompetitionAdmin>) => {
      state.competitionDetail?.competition.competitionAdmins.push(
        action.payload
      );
      state.competitionDetail!.possibleAdminUsers =
        state.competitionDetail!.possibleAdminUsers.filter(
          (possibleAdmin) => possibleAdmin.id !== action.payload.user.id
        );
    },
    addCompetitionPlayer: (
      state,
      action: PayloadAction<{
        competitionPlayer: tCompetitionPlayer;
        competitionTeamId: number;
      }>
    ) => {
      state.competitionDetail?.competition.competitionTeams.map(
        (competitionTeam) => {
          if (competitionTeam.id === action.payload.competitionTeamId) {
            competitionTeam.competitionPlayer.push(
              action.payload.competitionPlayer
            );
          }
          return competitionTeam;
        }
      );
      state.competitionDetail!.possiblePlayers =
        state.competitionDetail!.possiblePlayers.filter(
          (possiblePlayer) =>
            possiblePlayer.id !== action.payload.competitionPlayer.user.id
        );
    },
    addCompetitionTeam: (state, action: PayloadAction<tCompetitionTeam>) => {
      state.competitionDetail?.competition.competitionTeams.push(
        action.payload
      );
      state.competitionDetail!.possiblePlayers =
        state.competitionDetail!.possiblePlayers.filter((possiblePlayer) => {
          return !action.payload.competitionPlayer.find(
            (competitionPlayer) =>
              competitionPlayer.user.id === possiblePlayer.id
          );
        });
    },
    updateCompetition: (state, action: PayloadAction<tCompetition>) => {
      state.competitionDetail!.competition = action.payload;
    },
    updateCompetitionPlayerStatus: (
      state,
      action: PayloadAction<{
        competitionPlayerId: number;
        competitionPlayerStatus: tCompetitionPlayerStatus[];
      }>
    ) => {
      const newCompetitionTeams =
        state.competitionDetail!.competition.competitionTeams.map(
          (competitionTeam) => {
            const newCompetitionPlayer = competitionTeam.competitionPlayer.map(
              (singleCompetitionPlayer) => {
                const newCompetitionPlayerStatusState =
                  singleCompetitionPlayer.competitionPlayerStatus.map(
                    (singleCompetitionPlayerStatus) => {
                      if (
                        singleCompetitionPlayerStatus.id ===
                        action.payload.competitionPlayerStatus[0].id
                      ) {
                        return action.payload.competitionPlayerStatus[0];
                      } else {
                        return singleCompetitionPlayerStatus;
                      }
                    }
                  );

                if (
                  singleCompetitionPlayer.id ===
                  action.payload.competitionPlayerId
                ) {
                  newCompetitionPlayerStatusState.push(
                    action.payload.competitionPlayerStatus[1]
                  );
                }

                const newSingleCompetitionPlayer: tCompetitionPlayer = {
                  ...singleCompetitionPlayer,
                  competitionPlayerStatus: newCompetitionPlayerStatusState,
                };

                return newSingleCompetitionPlayer;
              }
            );

            const newCompetitionTeam: tCompetitionTeam = {
              ...competitionTeam,
              competitionPlayer: newCompetitionPlayer,
            };

            return newCompetitionTeam;
          }
        );

      state.competitionDetail!.competition.competitionTeams =
        newCompetitionTeams;
    },
    updateCompetitionTeam: (state, action: PayloadAction<tCompetitionTeam>) => {
      state.competitionDetail?.competition.competitionTeams.map(
        (competitionTeam) => {
          if (competitionTeam.id === action.payload.id) {
            return action.payload;
          } else {
            return competitionTeam;
          }
        }
      );
    },
  },
});

export const {
  storeCompetitions,
  storeCompetitionAdminStatus,
  storeCompetitionStatus,
  storeRegistrationStatus,
  storeCompetitionPlayerStatus,
  storeBillingStatus,
  addCompetition,
  storeCompetitionDetail,
  removeCompetitionDetail,
  removeCompetitions,
  updateCompetitionStatus,
  updateCompetitionAdminStatus,
  updateRegistrationStatus,
  updateBillingStatus,
  addCompetitionAdmin,
  addCompetitionPlayer,
  addCompetitionTeam,
  updateCompetition,
  updateCompetitionPlayerStatus,
  updateCompetitionTeam,
} = competitionSlice.actions;

export const competitionStore = configureStore({
  reducer: competitionSlice.reducer,
});

export type CompetitionState = ReturnType<typeof competitionSlice.reducer>;
