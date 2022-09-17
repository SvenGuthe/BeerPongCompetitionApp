import axios from "axios";
import { Dispatch } from "react";
import {
  billingStatusRoute,
  competitionAdminRoute,
  competitionAdminStatusRoute,
  competitionPlayerRoute,
  competitionPlayerStatusRoute,
  competitionRoute,
  competitionStatusRoute,
  competitionTeamRoute,
  registrationStatusRoute,
} from "../../api-routes/competition";
import tBillingStatus from "../../types/competition/billing/billingStatus";
import tBillingStatusUpdate from "../../types/competition/billing/billingStatusUpdate";
import tCompetition from "../../types/competition/competition";
import tCompetitionAdmin from "../../types/competition/competitionadmin/competitionAdmin";
import tCompetitionAdminAdd from "../../types/competition/competitionadmin/competitionAdminAdd";
import tCompetitionAdminStatus from "../../types/competition/competitionadmin/competitionAdminStatus";
import tCompetitionAdminStatusUpdate from "../../types/competition/competitionadmin/competitionAdminStatusUpdate";
import tCompetitionPlayer from "../../types/competition/competitionplayer/competitionPlayer";
import tCompetitionPlayerAdd from "../../types/competition/competitionplayer/competitionPlayerAdd";
import tCompetitionPlayerStatus from "../../types/competition/competitionplayer/competitionPlayerStatus";
import tCompetitionPlayerStatusUpdate from "../../types/competition/competitionplayer/competitionPlayerStatusUpdate";
import tCompetitionStatus from "../../types/competition/competitionstatus/competitionStatus";
import tCompetitionStatusUpdate from "../../types/competition/competitionstatus/competitionStatusUpdate";
import tCompetitionTeam from "../../types/competition/competitionteam/competitionTeam";
import tCompetitionTeamAdd from "../../types/competition/competitionteam/competitionTeamAdd";
import tCompetitionTeamUpdate from "../../types/competition/competitionteam/competitionTeamUpdate";
import tCompetitionUpdate from "../../types/competition/competitionUpdate";
import tRegistrationStatus from "../../types/competition/registration/registrationStatus";
import tRegistrationStatusUpdate from "../../types/competition/registration/registrationStatusUpdate";
import {
  updateBillingStatus,
  updateCompetitionAdminStatus,
  updateCompetitionStatus,
  updateRegistrationStatus,
  addCompetitionAdmin as addCompetitionAdminState,
  addCompetitionPlayer as addCompetitionPlayerState,
  addCompetitionTeam as addCompetitionTeamState,
  updateCompetition as updateCompetitionState,
  updateCompetitionPlayerStatus as updateCompetitionPlayerStatusState,
  updateCompetitionTeam as updateCompetitionTeamState,
} from "./competition-store";

export const changeCompetitionStatus = (
  competitionStatus: tCompetitionStatusUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(competitionStatusRoute, competitionStatus)
        .then((response) => {
          const competitionStatusResponse: tCompetitionStatus[] = response.data;
          dispatch(updateCompetitionStatus(competitionStatusResponse));
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const changeCompetitionAdminStatus = (
  competitionAdminStatus: tCompetitionAdminStatusUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionAdminStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(competitionAdminStatusRoute, competitionAdminStatus)
        .then((response) => {
          const competitionAdminStatusResponse: tCompetitionAdminStatus[] =
            response.data;
          dispatch(
            updateCompetitionAdminStatus({
              competitionAdminStatus: competitionAdminStatusResponse,
              competitionAdminId: competitionAdminStatus.id,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const changeRegistrationStatus = (
  registrationStatus: tRegistrationStatusUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${registrationStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(registrationStatusRoute, registrationStatus)
        .then((response) => {
          const registrationStatusResponse: tRegistrationStatus[] =
            response.data;
          dispatch(
            updateRegistrationStatus({
              registrationStatus: registrationStatusResponse,
              competitionTeamId: registrationStatus.id,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const changeBillingStatus = (billingStatus: tBillingStatusUpdate) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${billingStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(billingStatusRoute, billingStatus)
        .then((response) => {
          const billingStatusResponse: tBillingStatus[] = response.data;
          dispatch(
            updateBillingStatus({
              billingStatus: billingStatusResponse,
              competitionTeamId: billingStatus.id,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });

    return sendRequest();
  };
};

export const addCompetitionAdmin = (competitionAdmin: tCompetitionAdminAdd) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionAdminRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(competitionAdminRoute, competitionAdmin)
        .then((response) => {
          const competitionAdmin: tCompetitionAdmin = response.data;
          dispatch(addCompetitionAdminState(competitionAdmin));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const addCompetitionPlayer = (
  competitionPlayer: tCompetitionPlayerAdd
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionPlayerRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(competitionPlayerRoute, competitionPlayer)
        .then((response) => {
          const competitionPlayerResponse: tCompetitionPlayer = response.data;
          dispatch(
            addCompetitionPlayerState({
              competitionPlayer: competitionPlayerResponse,
              competitionTeamId: competitionPlayer.id,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const addCompetitionTeam = (competitionTeam: tCompetitionTeamAdd) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionTeamRoute} [POST] Request`);
    const sendRequest = async () =>
      await axios
        .post(competitionTeamRoute, competitionTeam)
        .then((response) => {
          const competitionTeamResponse: tCompetitionTeam = response.data;
          dispatch(addCompetitionTeamState(competitionTeamResponse));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const updateCompetition = (competition: tCompetitionUpdate) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(competitionRoute, competition)
        .then((response) => {
          const competition: tCompetition = response.data;
          dispatch(updateCompetitionState(competition));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const updateCompetitionPlayerStatus = (
  competitionPlayerStatus: tCompetitionPlayerStatusUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionPlayerStatusRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(competitionPlayerStatusRoute, competitionPlayerStatus)
        .then((response) => {
          const competitionPlayerStatusResponse: tCompetitionPlayerStatus[] =
            response.data;
          dispatch(
            updateCompetitionPlayerStatusState({
              competitionPlayerId: competitionPlayerStatus.id,
              competitionPlayerStatus: competitionPlayerStatusResponse,
            })
          );
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};

export const updateCompetitionTeam = (
  competitionTeam: tCompetitionTeamUpdate
) => {
  return async (dispatch: Dispatch<any>) => {
    console.log(`Send ${competitionTeamRoute} [PUT] Request`);
    const sendRequest = async () =>
      await axios
        .put(competitionTeamRoute, competitionTeam)
        .then((response) => {
          const competitionTeamResponse: tCompetitionTeam = response.data;
          dispatch(updateCompetitionTeamState(competitionTeamResponse));
        })
        .catch(function (error) {
          console.log(error);
        });
    return sendRequest();
  };
};
