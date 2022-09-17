// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTeamCompositionStatusType } from "../../enums/teamCompositionStatusType";

// id = TeamCompositionID
type tTeamCompositionStatusUpdate = tID & {
  teamCompositionStatusType: tTeamCompositionStatusType;
};

export default tTeamCompositionStatusUpdate;
