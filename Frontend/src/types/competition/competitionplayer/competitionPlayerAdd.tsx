// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = CompetitionTeamID
type tCompetitionPlayerAdd = tID & {
    userId: number;
};

export default tCompetitionPlayerAdd;