// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tAdditionalAttributes } from "../../defaults/tables";
import { tTimestamp } from "../../defaults/timestamp";
import { tTeamStatusType } from "../../enums/teamStatusType";

// id = TeamStatusID
// value = TeamStatusType
type tTeamStatus = tEnum &
  tAdditionalAttributes & {
    teamStatusDescription: tTeamStatusType;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
  };

export default tTeamStatus;
