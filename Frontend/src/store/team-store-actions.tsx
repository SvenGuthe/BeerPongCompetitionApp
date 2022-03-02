import axios from "axios"
import { Dispatch } from "react"
import { storeTeams } from "./team-store";

export const sendFetchTeamsRequest = (token: string) => {
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get('http://localhost:9999/team/team', {
            headers: {
                'Authorization': `Bearer ${token}`
            }
        }).then((response) => {
            dispatch(storeTeams(response.data))
        }).catch(function (error) {
            console.log(error);
        });

        return sendRequest();
    }
}
