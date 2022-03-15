import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'
import { tTeam, tTeamDetail } from '../../types/team'

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
        updateTeam: (state, action: PayloadAction<tTeam>) => {
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
    }
})

export const {
    storeTeams,
    updateTeam,
    addTeam,
    storeTeamStatus,
    storeTeamDetail,
    removeTeamDetail,
    removeTeams
} = teamSlice.actions

export const teamStore = configureStore({
    reducer: teamSlice.reducer
})

export type TeamState = ReturnType<typeof teamSlice.reducer>