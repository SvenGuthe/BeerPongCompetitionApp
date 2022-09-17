// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = CompetitionTeamID
type tCompetitionTeamUpdate = tID & {
    teamname: string;
    teamId?: number;
};

export default tCompetitionTeamUpdate;