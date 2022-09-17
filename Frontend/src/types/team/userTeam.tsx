// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";
import { tTimestamp } from "../defaults/timestamp";
import { tTeam } from "../team";

// id = TeamID
type tUserTeam = tID & {
    team: tTeam;
    isAdmin: boolean;
    creationTime: tTimestamp;
};

export default tUserTeam;