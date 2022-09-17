// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTeamStatusType } from "../../enums/teamStatusType";

// id = TeamID
type tTeamStatusUpdate = tID & {
  teamStatusType: tTeamStatusType;
};

export default tTeamStatusUpdate;
