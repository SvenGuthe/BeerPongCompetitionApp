// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = CompetitionID
type tCompetitionAdminAdd = tID & {
    userId: number;
};

export default tCompetitionAdminAdd;