// --------- MODEL Datatypes --------- //

import { tTeamIDAndName } from "../team"
import { tUserIDAndGamerTag } from "../user";

type tTeamAndUser = {
    team: tTeamIDAndName;
    users: tUserIDAndGamerTag[];
}

export default tTeamAndUser;