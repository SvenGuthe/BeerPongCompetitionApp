// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tCompetitionPlayerStatusType } from "../../enums/competitionPlayerStatusType";

// id = CompetitionPlayerStatusID
// value = CompetitionPlayerStatusType
type tCompetitionPlayerStatus = tEnum & {
    competitionPlayerStatusDescription: tCompetitionPlayerStatusType;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
}

export default tCompetitionPlayerStatus;