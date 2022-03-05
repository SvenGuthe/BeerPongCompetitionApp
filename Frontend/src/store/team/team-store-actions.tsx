import axios from "axios"
import { Dispatch } from "react"
import { TeamStatus } from "../../types/enums/teamStatus";
import { tTeamDetail, tTeamMetaData } from "../../types/team";
import { updateTeam } from "./team-store";

export const changeTeamStatus = (team: tTeamDetail, status: TeamStatus) => {
    return async (dispatch: Dispatch<any>) => {

        console.log("Send /team/team [PUT] Request");
        const teamMetaData: tTeamMetaData = {
            id: team.id,
            teamName: team.teamName,
            currentTeamStatusType: status
        }

        const sendRequest = async () => await axios.put('/team/team', {
            ...teamMetaData
        }).then((response) => {
            dispatch(updateTeam(response.data))
        }).catch((error) => {
            console.log(error);
        })

        return sendRequest();
    }

}

