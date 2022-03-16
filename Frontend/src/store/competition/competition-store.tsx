import { createSlice, configureStore, PayloadAction } from '@reduxjs/toolkit'
import { tCompetition, tCompetitionDetail, tCompetitionStatus } from '../../types/competition'
import { tEnum, tPaginationDTO } from '../../types/defaults/generics'

type SliceState = {
    competitions: tPaginationDTO<tCompetition> | null,
    competitionAdminStatus: tPaginationDTO<tEnum> | null,
    competitionStatus: tPaginationDTO<tEnum> | null,
    registrationStatus: tPaginationDTO<tEnum> | null,
    competitionPlayerStatus: tPaginationDTO<tEnum> | null,
    billingStatus: tPaginationDTO<tEnum> | null,
    competitionDetail: tCompetitionDetail | null
}

const initialState: SliceState = {
    competitions: null,
    competitionAdminStatus: null,
    competitionStatus: null,
    registrationStatus: null,
    competitionPlayerStatus: null,
    billingStatus: null,
    competitionDetail: null
}

export const competitionSlice = createSlice({
    name: 'competitions',
    initialState: initialState,
    reducers: {
        storeCompetitions: (state, action: PayloadAction<tPaginationDTO<tCompetition>>) => {
            state.competitions = action.payload;
        },
        removeCompetitions: (state) => {
            state.competitions = null;
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
        addCompetition: (state, action: PayloadAction<tCompetitionDetail>) => {
            const newCompetition = action.payload;
            if (state.competitions) {
                const existingCompetition = state.competitions.data.find(competition => competition.id === newCompetition.competition.id);
                if (!existingCompetition) {
                    state.competitions.data = state.competitions.data.concat(newCompetition.competition);
                }
            } else {
                state.competitions = {
                    size: 1,
                    pages: 1,
                    data: [newCompetition.competition]
                };
            }
        },
        storeCompetitionDetail: (state, action: PayloadAction<tCompetitionDetail>) => {
            state.competitionDetail = action.payload;
        },
        removeCompetitionDetail: (state) => {
            state.competitionDetail = null;
        },
        updateCompetitionStatus: (state, action: PayloadAction<tCompetitionStatus[]>) => {

            const newCompetitionState = state.competitionDetail!.competition.competitionStatus.map(singleCompetitionStatus => {
                if (singleCompetitionStatus.id === action.payload[0].id) {
                    return action.payload[0];
                } else {
                    return singleCompetitionStatus
                }
            })
            newCompetitionState.push(action.payload[1])

            state.competitionDetail!.competition.competitionStatus = newCompetitionState

        }
    }
})

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
    updateCompetitionStatus
} = competitionSlice.actions

export const competitionStore = configureStore({
    reducer: competitionSlice.reducer
})

export type CompetitionState = ReturnType<typeof competitionSlice.reducer>