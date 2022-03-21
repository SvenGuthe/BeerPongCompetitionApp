import axios from "axios";
import { Dispatch } from "react";
import { tTeamStatusType } from "../../types/enums/teamStatusType";
import { updateTeam as updateTeamState, updateTeamStatus as updateTeamStatusState, addTeamInvitationLink as addTeamInvitationLinkState } from "./team-store";

export const updateTeam = (metaData: {
    id: number,
    teamName: string | undefined
}) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/team [PUT] Request");
        const sendRequest = async () => await axios.put('/team/team', metaData).then((response) => {
            dispatch(updateTeamState(response.data));
        }).catch(function (error) {
            console.log(error);
        });
        return sendRequest();
    }
}

export const updateTeamStatus = (id: number, teamStatusType: tTeamStatusType) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teamstatus [POST] Request");
        const sendRequest = async () => await axios.post('/team/teamstatus', {
            id: id,
            teamStatusType: teamStatusType
        }).then((response) => {
            dispatch(updateTeamStatusState(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}

export const addTeamInvitationLink = (id: number, teamInvitationLink: string) => {
    return async (dispatch: Dispatch<any>) => {
        console.log("Send /team/teaminvitationlink [POST] Request");
        const sendRequest = async () => await axios.post('/team/teaminvitationlink', {
            id: id,
            teamInvitationLink: teamInvitationLink
        }).then((response) => {
            dispatch(addTeamInvitationLinkState(response.data));
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}