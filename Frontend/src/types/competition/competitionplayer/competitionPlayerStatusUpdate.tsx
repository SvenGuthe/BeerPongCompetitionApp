// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tCompetitionPlayerStatusType } from "../../enums/competitionPlayerStatusType";

// id = CompetitionPlayerID
type tCompetitionPlayerStatusUpdate = tID & {
    competitionPlayerStatusType: tCompetitionPlayerStatusType;
};

export default tCompetitionPlayerStatusUpdate;