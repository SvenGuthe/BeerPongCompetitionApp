import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tCompetitionDetail } from '../../types/competition'
import { tEnum } from '../../types/enum'

type SliceState = {
    competitions: tCompetitionDetail[] | null,
    competitionAdminStatus: tEnum[] | null,
    competitionStatus: tEnum[] | null,
    registrationStatus: tEnum[] | null,
    competitionPlayerStatus: tEnum[] | null,
    billingStatus: tEnum[] | null
}

const initialState: SliceState = {
    competitions: null,
    competitionAdminStatus: null,
    competitionStatus: null,
    registrationStatus: null,
    competitionPlayerStatus: null,
    billingStatus: null
}

export const competitionSlice = createSlice({
    name: 'competitions',
    initialState: initialState,
    reducers: {
        storeCompetitions: (state, action: PayloadAction<tCompetitionDetail[]>) => {
            state.competitions = action.payload;
        },
        storeCompetitionAdminStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.competitionAdminStatus = action.payload;
        },
        storeCompetitionStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.competitionStatus = action.payload;
        },
        storeRegistrationStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.registrationStatus = action.payload;
        },
        storeCompetitionPlayerStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.competitionPlayerStatus = action.payload;
        },
        storeBillingStatus: (state, action: PayloadAction<tEnum[]>) => {
            state.billingStatus = action.payload;
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

export const {
    storeCompetitions,
    updateCompetition,
    addCompetition,
    storeCompetitionAdminStatus,
    storeCompetitionStatus,
    storeRegistrationStatus,
    storeCompetitionPlayerStatus,
    storeBillingStatus
} = competitionSlice.actions

export const competitionStore = configureStore({
    reducer: competitionSlice.reducer
})

export type CompetitionState = ReturnType<typeof competitionSlice.reducer>