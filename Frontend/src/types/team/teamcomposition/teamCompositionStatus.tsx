// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tTeamCompositionStatusType } from "../../enums/teamCompositionStatusType";

// id = TeamCompositionStatusID
// value = TeamCompositionStatusType
type tTeamCompositionStatus = tEnum & {
  teamCompositionStatusType: tTeamCompositionStatusType;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

export default tTeamCompositionStatus;
