import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'
import { tTeam } from '../../types/team'

type SliceState = {
    teams: tTeam[] | null,
    teamStatus: tPaginationDTO<tEnum> | null
}

const initialState: SliceState = {
    teams: null,
    teamStatus: null
}

export const teamSlice = createSlice({
    name: 'teams',
    initialState: initialState,
    reducers: {
        storeTeams: (state, action: PayloadAction<tTeam[]>) => {
            state.teams = action.payload;
        },
        storeTeamStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.teamStatus = action.payload;
        },
        updateTeam: (state, action: PayloadAction<tTeam[]>) => {
            const fetchedTeam = action.payload[0];
            if (state.teams) {
                state.teams = state.teams.map(team => {
                    if (team.id === fetchedTeam.id) {
                        return fetchedTeam;
                    } else {
                        return team;
                    }
                })
            }
        },
        addTeam: (state, action: PayloadAction<tTeam>) => {
            const newTeam = action.payload;
            if (state.teams) {
                const existingTeam = state.teams.find(team => team.id === newTeam.id);
                if (!existingTeam) {
                    state.teams = state.teams.concat(newTeam);
                }
            } else {
                state.teams = [newTeam];
            }
        },
    }
})

export const {
    storeTeams,
    updateTeam,
    addTeam,
    storeTeamStatus
} = teamSlice.actions

export const teamStore = configureStore({
    reducer: teamSlice.reducer
})

export type TeamState = ReturnType<typeof teamSlice.reducer>