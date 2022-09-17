// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tCompetitionAdminStatusType } from "../../enums/competitionAdminStatusType";

// id = CompetitionAdminStatusID
// value = CompetitionAdminStatusType
type tCompetitionAdminStatus = tEnum & {
  competitionAdminStatusDescription: tCompetitionAdminStatusType;
  creationTime: tTimestamp;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

export default tCompetitionAdminStatus;
