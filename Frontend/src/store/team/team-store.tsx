import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'
import { tTeam, tTeamComposition, tTeamDetail, tTeamInvitationLink, tTeamStatus } from '../../types/team'

type SliceState = {
    teams: tPaginationDTO<tTeam> | null,
    teamStatus: tPaginationDTO<tEnum> | null,
    teamDetail: tTeamDetail | null,
}

const initialState: SliceState = {
    teams: null,
    teamStatus: null,
    teamDetail: null
}

export const teamSlice = createSlice({
    name: 'teams',
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
        storeTeamDetail: (state, action: PayloadAction<tTeamDetail>) => {
            state.teamDetail = action.payload;
        },
        removeTeamDetail: (state) => {
            state.teamDetail = null;
        },
        updateTeams: (state, action: PayloadAction<tTeam>) => {
            const fetchedTeam = action.payload;
            if (state.teams) {
                state.teams.data = state.teams.data.map(team => {
                    if (team.id === fetchedTeam.id) {
                        return fetchedTeam;
                    } else {
                        return team;
                    }
                })
            }
        },
        addTeam: (state, action: PayloadAction<tTeamDetail>) => {
            const newTeam = action.payload.team;
            if (state.teams) {
                const existingTeam = state.teams.data.find(team => team.id === newTeam.id);
                if (!existingTeam) {
                    state.teams.data = state.teams.data.concat(newTeam);
                }
            } else {
                state.teams = {
                    size: 1,
                    pages: 1,
                    data: [newTeam]
                };
            }
        },
        updateTeam: (state, action: PayloadAction<tTeam>) => {
            state.teamDetail!.team = action.payload;
        },
        updateTeamStatus: (state, action: PayloadAction<tTeamStatus[]>) => {

            const newTeamStatus = state.teamDetail!.team.teamStatus.map(singleTeamStatus => {
                if (singleTeamStatus.id === action.payload[0].id) {
                    return action.payload[0];
                } else {
                    return singleTeamStatus
                }
            });
            newTeamStatus.push(action.payload[1]);

            state.teamDetail!.team.teamStatus = newTeamStatus

        },
        addTeamInvitationLink: (state, action: PayloadAction<tTeamInvitationLink>) => {
            state.teamDetail?.team.teamInvitationLinks.push(action.payload);
        },
        updateTeamComposition: (state, action: PayloadAction<tTeamComposition>) => {
            const userId = action.payload.user.id;

            const newUsers = state.teamDetail!.users.map(user => {
                if (user.id === userId) {
                    user.admin = action.payload.admin
                }
                return user;
            });

            state.teamDetail!.users = newUsers;
        },
        addTeamComposition: (state, action: PayloadAction<tTeamComposition>) => {
            const teamComposition = action.payload;

            state.teamDetail!.users.push({
                id: teamComposition.id,
                user: teamComposition.user,
                admin: teamComposition.admin,
                creationTime: teamComposition.creationTime
            }) ;

            state.teamDetail!.possibleUsers = state.teamDetail!.possibleUsers.filter(possibleUser => {
                return possibleUser.id !== teamComposition.user.id;
            })
        }
    }
})

export const {
    storeTeams,
    updateTeams,
    addTeam,
    storeTeamStatus,
    storeTeamDetail,
    removeTeamDetail,
    removeTeams,
    updateTeam,
    updateTeamStatus,
    addTeamInvitationLink,
    updateTeamComposition,
    addTeamComposition
} = teamSlice.actions

export const teamStore = configureStore({
    reducer: teamSlice.reducer
})

export type TeamState = ReturnType<typeof teamSlice.reducer>