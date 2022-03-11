import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tCompetition } from '../../types/competition'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'

type SliceState = {
    competitions: tCompetition[] | null,
    competitionAdminStatus: tPaginationDTO<tEnum> | null,
    competitionStatus: tPaginationDTO<tEnum> | null,
    registrationStatus: tPaginationDTO<tEnum> | null,
    competitionPlayerStatus: tPaginationDTO<tEnum> | null,
    billingStatus: tPaginationDTO<tEnum> | null
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
        storeCompetitions: (state, action: PayloadAction<tCompetition[]>) => {
            state.competitions = action.payload;
        },
        storeCompetitionAdminStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.competitionAdminStatus = action.payload;
        },
        storeCompetitionStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.competitionStatus = action.payload;
        },
        storeRegistrationStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.registrationStatus = action.payload;
        },
        storeCompetitionPlayerStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.competitionPlayerStatus = action.payload;
        },
        storeBillingStatus: (state, action: PayloadAction<tPaginationDTO<tEnum>>) => {
            state.billingStatus = action.payload;
        },
        updateCompetition: (state, action: PayloadAction<tCompetition[]>) => {
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
        addCompetition: (state, action: PayloadAction<tCompetition>) => {
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