// --------- CUSTOM Datatypes --------- //

import tCompetition from "../competition/competition";
import { tTeam } from "../team"
import { tTeamUser, tUser } from "../user";

type tTeamDetail = {
    team: tTeam;
    users: tTeamUser[];
    competitions: tCompetition[];
    possibleUsers: tUser[];
}

export default tTeamDetail;