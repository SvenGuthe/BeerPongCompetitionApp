// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import tUser from "../../user/user";
import tCompetitionPlayerStatus from "./competitionPlayerStatus";

// id = CompetitionPlayerID
type tCompetitionPlayer = tID & {
  user: tUser;
  competitionPlayerStatus: tCompetitionPlayerStatus[];
  creationTime: tTimestamp;
};

export default tCompetitionPlayer;
