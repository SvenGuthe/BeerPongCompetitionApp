import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tCompetitionDetail } from '../../types/competition'
import { tPaginationDTO } from '../../types/enum'

type SliceState = {
    competitions: tCompetitionDetail[] | null,
    competitionAdminStatus: tPaginationDTO | null,
    competitionStatus: tPaginationDTO | null,
    registrationStatus: tPaginationDTO | null,
    competitionPlayerStatus: tPaginationDTO | null,
    billingStatus: tPaginationDTO | null
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
        storeCompetitionAdminStatus: (state, action: PayloadAction<tPaginationDTO>) => {
            state.competitionAdminStatus = action.payload;
        },
        storeCompetitionStatus: (state, action: PayloadAction<tPaginationDTO>) => {
            state.competitionStatus = action.payload;
        },
        storeRegistrationStatus: (state, action: PayloadAction<tPaginationDTO>) => {
            state.registrationStatus = action.payload;
        },
        storeCompetitionPlayerStatus: (state, action: PayloadAction<tPaginationDTO>) => {
            state.competitionPlayerStatus = action.payload;
        },
        storeBillingStatus: (state, action: PayloadAction<tPaginationDTO>) => {
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