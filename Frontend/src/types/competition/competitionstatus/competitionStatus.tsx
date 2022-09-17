// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tCompetitionStatusType } from "../../enums/competitionStatusType";

// id = CompetitionStatusID
// value = CompetitionStatusType
type tCompetitionStatus = tEnum & {
    competitionStatusType: tCompetitionStatusType;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
}

export default tCompetitionStatus;