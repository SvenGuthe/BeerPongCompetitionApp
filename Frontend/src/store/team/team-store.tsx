import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tEnum } from '../../types/enum'
import { tTeamDetail } from '../../types/team'

type SliceState = {
    teams: tTeamDetail[] | null,
    teamStatus: tEnum[] | null
}

const initialState: SliceState = {
    teams: null,
    teamStatus: null
}

export const teamSlice = createSlice({
    name: 'teams',
    initialState: initialState,
    reducers: {
        storeTeams: (state, action: PayloadAction<tTeamDetail[]>) => {
            state.teams = action.payload;
        },
        storeTeamStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.teamStatus = action.payload;
        },
        updateTeam: (state, action: PayloadAction<tTeamDetail[]>) => {
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
        addTeam: (state, action: PayloadAction<tTeamDetail>) => {
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