import axios from "axios"
import { Dispatch } from "react"
import { TeamStatus } from "../types/enums/teamStatus";
import { tTeamMetaData, tTeamWithUsers } from "../types/team";
import { storeTeams, updateTeam } from "./team-store";

export const sendFetchTeamsRequest = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('/team/team').then((response) => {
            dispatch(storeTeams(response.data))
        }).catch((error) => {
            console.log(error);
        });

        return sendRequest();
    }
}

export const changeTeamStatus = (team: tTeamWithUsers, status: TeamStatus) => {
    return async (dispatch: Dispatch<any>) => {

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