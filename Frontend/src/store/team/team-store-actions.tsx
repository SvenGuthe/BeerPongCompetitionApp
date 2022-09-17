import axios from "axios";
import { Dispatch } from "react";
import {
  teamCompositionRoute,
  teamCompositionStatusRoute,
  teamInvitationLinkRoute,
  teamRoute,
  teamStatusRoute,
} from "../../api-routes/team";
import tTeamCompositionAdd from "../../types/team/teamcomposition/teamCompositionAdd";
import tTeamCompositionStatusUpdate from "../../types/team/teamcomposition/teamCompositionStatusUpdate";
import tTeamCompositionUpdate from "../../types/team/teamcomposition/teamCompositionUpdate";
import tTeamInvitationLinkAdd from "../../types/team/teaminvitationlink/teamInvitationLinkAdd";
import tTeamStatusUpdate from "../../types/team/teamstatus/teamStatusUpdate";
import tTeamUpdate from "../../types/team/teamUpdate";
import {
  updateTeam as updateTeamState,
  updateTeamStatus as updateTeamStatusState,
  addTeamInvitationLink as addTeamInvitationLinkState,
  updateTeamComposition as updateTeamCompositionState,
  updateTeamCompositionStatus as updateTeamCompositionStatusState,
  addTeamComposition as addTeamCompositionState,
} from "./team-store";

export const updateTeam = (team: tTeamUpdate) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(teamRoute, team)
        .then((response) => {
          dispatch(updateTeamState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const updateTeamStatus = (teamStatus: tTeamStatusUpdate) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamStatusRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(teamStatusRoute, teamStatus)
        .then((response) => {
          dispatch(updateTeamStatusState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const addTeamInvitationLink = (
  teamInvitationLink: tTeamInvitationLinkAdd
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamInvitationLinkRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(teamInvitationLinkRoute, teamInvitationLink)
        .then((response) => {
          dispatch(addTeamInvitationLinkState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const updateTeamComposition = (
  teamComposition: tTeamCompositionUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamCompositionRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(teamCompositionRoute, teamComposition)
        .then((response) => {
          dispatch(updateTeamCompositionState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const updateTeamCompositionStatus = (
  teamCompositionStatus: tTeamCompositionStatusUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamCompositionStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(teamCompositionStatusRoute, teamCompositionStatus)
        .then((response) => {
          dispatch(updateTeamCompositionStatusState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const addTeamComposition = (teamComposition: tTeamCompositionAdd) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${teamCompositionRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(teamCompositionRoute, teamComposition)
        .then((response) => {
          dispatch(addTeamCompositionState(response.data));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};
