// --------- COSTUM Datatypes --------- //

import { tID } from "../defaults/generics";
import { tTimestamp } from "../defaults/timestamp";
import tTeamCompositionStatus from "../team/teamcomposition/teamCompositionStatus";
import tUser from "./user";

// id = TeamCompositionID
type tTeamUser = tID & {
  user: tUser;
  isAdmin: boolean;
  creationTime: tTimestamp;
  teamCompositionStatus: tTeamCompositionStatus[];
};

export default tTeamUser;
