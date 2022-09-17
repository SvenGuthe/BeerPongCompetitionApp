// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tTeam } from "../../team";
import { tUser } from "../../user";
import tTeamCompositionStatus from "./teamCompositionStatus";

// id = TeamCompositionID
type tTeamComposition = tID & {
    team: tTeam;
    user: tUser;
    isAdmin: boolean;
    creationTime: tTimestamp;
    teamCompositionStatus: tTeamCompositionStatus[];
};

export default tTeamComposition;