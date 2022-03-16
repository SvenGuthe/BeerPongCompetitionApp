import axios from "axios";
import { Dispatch } from "react";
import { tCompetitionStatusType } from "../../types/enums/competitionStatusType";
import { updateCompetitionStatus } from "./competition-store";

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