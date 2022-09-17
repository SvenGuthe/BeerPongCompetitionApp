// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = TeamCompositionID
type tTeamCompositionUpdate = tID & {
    isAdmin: boolean;
}

export default tTeamCompositionUpdate;