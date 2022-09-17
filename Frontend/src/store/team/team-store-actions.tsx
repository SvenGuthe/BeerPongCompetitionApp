import axios from "axios";
import { Dispatch } from "react";
import {
  teamCompositionRoute,
  teamCompositionStatusRoute,
  teamInvitationLinkRoute,
  teamRoute,
  teamStatusRoute,
} from "../../api-routes/team";
import tTeam from "../../types/team/team";
import tTeamComposition from "../../types/team/teamcomposition/teamComposition";
import tTeamCompositionAdd from "../../types/team/teamcomposition/teamCompositionAdd";
import tTeamCompositionStatus from "../../types/team/teamcomposition/teamCompositionStatus";
import tTeamCompositionStatusUpdate from "../../types/team/teamcomposition/teamCompositionStatusUpdate";
import tTeamCompositionUpdate from "../../types/team/teamcomposition/teamCompositionUpdate";
import tTeamInvitationLink from "../../types/team/teaminvitationlink/teamInvitationLink";
import tTeamInvitationLinkAdd from "../../types/team/teaminvitationlink/teamInvitationLinkAdd";
import tTeamStatus from "../../types/team/teamstatus/teamStatus";
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
          const teamResponse: tTeam = response.data;
          dispatch(updateTeamState(teamResponse));
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
          const teamStatusResponse: tTeamStatus[] = response.data;
          dispatch(updateTeamStatusState(teamStatusResponse));
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
          const teamInvitationLinkResponse: tTeamInvitationLink = response.data;
          dispatch(addTeamInvitationLinkState(teamInvitationLinkResponse));
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
          const teamCompositionResponse: tTeamComposition = response.data;
          dispatch(updateTeamCompositionState(teamCompositionResponse));
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
          const teamCompositionStatusResponse: tTeamCompositionStatus[] =
            response.data;
          dispatch(
            updateTeamCompositionStatusState(teamCompositionStatusResponse)
          );
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
          const teamCompositionResponse: tTeamComposition = response.data;
          dispatch(addTeamCompositionState(teamCompositionResponse));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};
