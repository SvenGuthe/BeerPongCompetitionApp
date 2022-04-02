import axios from "axios";
import { Dispatch } from "react";
import { tTeamCompositionAdd, tTeamCompositionUpdate, tTeamInvitationLinkAdd, tTeamStatusUpdate, tTeamUpdate } from "../../types/team";
import { updateTeam as updateTeamState, updateTeamStatus as updateTeamStatusState, addTeamInvitationLink as addTeamInvitationLinkState, updateTeamComposition as updateTeamCompositionState, addTeamComposition as addTeamCompositionState } from "./team-store";

export const updateTeam = (team: tTeamUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/team [PUT] Request");
        const sendRequest = async () => await axios.put('/team/team', team).then((response) => {
            dispatch(updateTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateTeamStatus = (teamStatus: tTeamStatusUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teamstatus [POST] Request");
        const sendRequest = async () => await axios.post('/team/teamstatus', teamStatus).then((response) => {
            dispatch(updateTeamStatusState(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const addTeamInvitationLink = (teamInvitationLink: tTeamInvitationLinkAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teaminvitationlink [POST] Request");
        const sendRequest = async () => await axios.post('/team/teaminvitationlink', teamInvitationLink).then((response) => {
            dispatch(addTeamInvitationLinkState(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const updateTeamComposition = (teamComposition: tTeamCompositionUpdate) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teamcomposition [PUT] Request");
        const sendRequest = async () => await axios.put('/team/teamcomposition', teamComposition).then((response) => {
            dispatch(updateTeamCompositionState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const addTeamComposition = (teamComposition: tTeamCompositionAdd) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teamcomposition [POST] Request");
        const sendRequest = async () => await axios.post('/team/teamcomposition', teamComposition).then((response) => {
            dispatch(addTeamCompositionState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}