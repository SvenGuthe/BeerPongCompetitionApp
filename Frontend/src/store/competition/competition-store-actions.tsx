import axios from "axios";
import { Dispatch } from "react";
import { tBillingStatusType } from "../../types/enums/billingStatusType";
import { tCompetitionAdminStatusType } from "../../types/enums/competitionAdminStatusType";
import { tCompetitionStatusType } from "../../types/enums/competitionStatusType";
import { tRegistrationStatusType } from "../../types/enums/registrationStatusType";
import { updateBillingStatus, updateCompetitionAdminStatus, updateCompetitionStatus, updateRegistrationStatus, addCompetitionAdmin as addCompetitionAdminState, addCompetitionPlayer as addCompetitionPlayerState, addCompetitionTeam as addCompetitionTeamState } from "./competition-store";

export const changeCompetitionStatus = (id: number, newState: tCompetitionStatusType) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionstatus', {
            id: id,
            competitionStatusType: newState
        }).then((response) => {
            dispatch(updateCompetitionStatus(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeCompetitionAdminStatus = (id: number, newState: tCompetitionAdminStatusType) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionadminstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionadminstatus', {
            id: id,
            competitionAdminStatusType: newState
        }).then((response) => {
            dispatch(updateCompetitionAdminStatus({
                competitionAdminStatus: response.data,
                competitionAdminId: id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeRegistrationStatus = (id: number, newState: tRegistrationStatusType) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/registration/registrationstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/registration/registrationstatus', {
            id: id,
            registrationStatusType: newState
        }).then((response) => {
            dispatch(updateRegistrationStatus({
                registrationStatus: response.data,
                competitionTeamId: id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeBillingStatus = (id: number, newState: tBillingStatusType) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/billing/billingstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/billing/billingstatus', {
            id: id,
            billingStatusType: newState
        }).then((response) => {
            dispatch(updateBillingStatus({
                billingStatus: response.data,
                competitionTeamId: id
            }));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const addCompetitionAdmin = (competitionId: number, userId: number) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionadmin [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionadmin', {
            id: competitionId,
            userId: userId
        }).then((response) => {
            dispatch(addCompetitionAdminState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addCompetitionPlayer = (competitionTeamId: number, userId: number) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionplayer [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionplayer', {
            id: competitionTeamId,
            userId: userId
        }).then((response) => {
            dispatch(addCompetitionPlayerState({
                competitionPlayer: response.data,
                competitionTeamId: competitionTeamId
            }));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addCompetitionTeam = (competitionId: number, userIds: number[], teamname: string, password: string, teamId?: number) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionteam [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionteam', {
            id: competitionId,
            teamname: teamname,
            password: password,
            teamId: teamId,
            playerIds: userIds
        }).then((response) => {
            dispatch(addCompetitionTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}