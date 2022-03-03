import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tTeamWithUsers } from '../types/team';

type SliceState = {
    teams: tTeamWithUsers[] | null
}

const initialState: SliceState = {
    teams: null
}

export const teamSlice = createSlice({
    name: 'teams',
    initialState: initialState,
    reducers: {
        storeTeams: (state, action: PayloadAction<tTeamWithUsers[]>) => {
            state.teams = action.payload;
        },
        updateTeam: (state, action: PayloadAction<tTeamWithUsers[]>) => {
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
        }
    }
})

export const { storeTeams, updateTeam } = teamSlice.actions

export const teamStore = configureStore({
    reducer: teamSlice.reducer
})

export type UserState = ReturnType<typeof teamSlice.reducer>