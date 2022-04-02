import axios from "axios";
import { Dispatch } from "react";
import { tBillingStatusUpdate, tCompetitionAdminAdd, tCompetitionAdminStatusUpdate, tCompetitionPlayerAdd, tCompetitionPlayerStatusUpdate, tCompetitionStatusUpdate, tCompetitionTeamAdd, tCompetitionTeamUpdate, tCompetitionUpdate, tRegistrationStatusUpdate } from "../../types/competition";
import { updateBillingStatus, updateCompetitionAdminStatus, updateCompetitionStatus, updateRegistrationStatus, addCompetitionAdmin as addCompetitionAdminState, addCompetitionPlayer as addCompetitionPlayerState, addCompetitionTeam as addCompetitionTeamState, updateCompetition as updateCompetitionState, updateCompetitionPlayerStatus as updateCompetitionPlayerStatusState, updateCompetitionTeam as updateCompetitionTeamState } from "./competition-store";

export const changeCompetitionStatus = (competitionStatus: tCompetitionStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionstatus', competitionStatus).then((response) => {
            dispatch(updateCompetitionStatus(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeCompetitionAdminStatus = (competitionAdminStatus: tCompetitionAdminStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionadminstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionadminstatus', competitionAdminStatus).then((response) => {
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
        console.log("Send /competition/registration/registrationstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/registration/registrationstatus', registrationStatus).then((response) => {
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
        console.log("Send /competition/billing/billingstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/billing/billingstatus', billingStatus).then((response) => {
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
        console.log("Send /competition/competitionadmin [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionadmin', competitionAdmin).then((response) => {
            dispatch(addCompetitionAdminState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addCompetitionPlayer = (competitionPlayer: tCompetitionPlayerAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionplayer [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionplayer', competitionPlayer).then((response) => {
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
        console.log("Send /competition/competitionteam [POST] Request");
        const sendRequest = async () => await axios.post('/competition/competitionteam', competitionTeam).then((response) => {
            dispatch(addCompetitionTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateCompetition = (competition: tCompetitionUpdate) => {

    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competition [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competition', competition).then((response) => {
            dispatch(updateCompetitionState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }

}

export const updateCompetitionPlayerStatus = (competitionPlayerStatus: tCompetitionPlayerStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionplayerstatus [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionplayerstatus', competitionPlayerStatus).then((response) => {
            dispatch(updateCompetitionPlayerStatusState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateCompetitionTeam = (competitionTeam: tCompetitionTeamUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /competition/competitionteam [PUT] Request");
        const sendRequest = async () => await axios.put('/competition/competitionteam', competitionTeam).then((response) => {
            dispatch(updateCompetitionTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}