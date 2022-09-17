// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = TeamID
type tTeamCompositionAdd = tID & {
    userId: number;
    isAdmin: boolean;
};

export default tTeamCompositionAdd;