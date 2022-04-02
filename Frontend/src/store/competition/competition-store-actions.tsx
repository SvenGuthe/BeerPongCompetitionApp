import axios from "axios";
import { Dispatch } from "react";
import { billingStatusRoute, competitionAdminRoute, competitionAdminStatusRoute, competitionPlayerRoute, competitionPlayerStatusRoute, competitionRoute, competitionStatusRoute, competitionTeamRoute, registrationStatusRoute } from "../../api-routes/competition";
import { tBillingStatusUpdate, tCompetitionAdminAdd, tCompetitionAdminStatusUpdate, tCompetitionPlayerAdd, tCompetitionPlayerStatusUpdate, tCompetitionStatusUpdate, tCompetitionTeamAdd, tCompetitionTeamUpdate, tCompetitionUpdate, tRegistrationStatusUpdate } from "../../types/competition";
import { updateBillingStatus, updateCompetitionAdminStatus, updateCompetitionStatus, updateRegistrationStatus, addCompetitionAdmin as addCompetitionAdminState, addCompetitionPlayer as addCompetitionPlayerState, addCompetitionTeam as addCompetitionTeamState, updateCompetition as updateCompetitionState, updateCompetitionPlayerStatus as updateCompetitionPlayerStatusState, updateCompetitionTeam as updateCompetitionTeamState } from "./competition-store";

export const changeCompetitionStatus = (competitionStatus: tCompetitionStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionStatusRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(competitionStatusRoute, competitionStatus).then((response) => {
            dispatch(updateCompetitionStatus(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeCompetitionAdminStatus = (competitionAdminStatus: tCompetitionAdminStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionAdminStatusRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(competitionAdminStatusRoute, competitionAdminStatus).then((response) => {
            dispatch(updateCompetitionAdminStatus({
                competitionAdminStatus: response.data,
                competitionAdminId: competitionAdminStatus.id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeRegistrationStatus = (registrationStatus: tRegistrationStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${registrationStatusRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(registrationStatusRoute, registrationStatus).then((response) => {
            dispatch(updateRegistrationStatus({
                registrationStatus: response.data,
                competitionTeamId: registrationStatus.id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeBillingStatus = (billingStatus: tBillingStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${billingStatusRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(billingStatusRoute, billingStatus).then((response) => {
            dispatch(updateBillingStatus({
                billingStatus: response.data,
                competitionTeamId: billingStatus.id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const addCompetitionAdmin = (competitionAdmin: tCompetitionAdminAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionAdminRoute} [POST] Request`);
        const sendRequest = async () => await axios.post(competitionAdminRoute, competitionAdmin).then((response) => {
            dispatch(addCompetitionAdminState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addCompetitionPlayer = (competitionPlayer: tCompetitionPlayerAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionPlayerRoute} [POST] Request`);
        const sendRequest = async () => await axios.post(competitionPlayerRoute, competitionPlayer).then((response) => {
            dispatch(addCompetitionPlayerState({
                competitionPlayer: response.data,
                competitionTeamId: competitionPlayer.id
            }));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addCompetitionTeam = (competitionTeam: tCompetitionTeamAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionTeamRoute} [POST] Request`);
        const sendRequest = async () => await axios.post(competitionTeamRoute, competitionTeam).then((response) => {
            dispatch(addCompetitionTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateCompetition = (competition: tCompetitionUpdate) => {

    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(competitionRoute, competition).then((response) => {
            dispatch(updateCompetitionState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}

export const updateCompetitionPlayerStatus = (competitionPlayerStatus: tCompetitionPlayerStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionPlayerStatusRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(competitionPlayerStatusRoute, competitionPlayerStatus).then((response) => {
            dispatch(updateCompetitionPlayerStatusState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateCompetitionTeam = (competitionTeam: tCompetitionTeamUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log(`Send ${competitionTeamRoute} [PUT] Request`);
        const sendRequest = async () => await axios.put(competitionTeamRoute, competitionTeam).then((response) => {
            dispatch(updateCompetitionTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}