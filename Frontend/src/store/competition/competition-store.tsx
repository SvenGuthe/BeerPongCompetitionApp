import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tCompetitionDetail } from '../../types/competition'

type SliceState = {
    competitions: tCompetitionDetail[] | null
}

const initialState: SliceState = {
    competitions: null
}

export const competitionSlice = createSlice({
    name: 'competitions',
    initialState: initialState,
    reducers: {
        storeCompetitions: (state, action: PayloadAction<tCompetitionDetail[]>) => {
            state.competitions = action.payload;
        },
        updateCompetition: (state, action: PayloadAction<tCompetitionDetail[]>) => {
            const fetchedCompetition = action.payload[0];
            if (state.competitions) {
                state.competitions = state.competitions.map(competition => {
                    if (competition.id === fetchedCompetition.id) {
                        return fetchedCompetition;
                    } else {
                        return competition;
                    }
                })
            }
        },
        addCompetition: (state, action: PayloadAction<tCompetitionDetail>) => {
            const newCompetition = action.payload;
            if (state.competitions) {
                const existingCompetition = state.competitions.find(competition => competition.id === newCompetition.id);
                if (!existingCompetition) {
                    state.competitions = state.competitions.concat(newCompetition);
                }
            } else {
                state.competitions = [newCompetition];
            }
        },
    }
})

export const { storeCompetitions, updateCompetition, addCompetition } = competitionSlice.actions

export const competitionStore = configureStore({
    reducer: competitionSlice.reducer
})

export type CompetitionState = ReturnType<typeof competitionSlice.reducer>