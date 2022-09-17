// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tCompetitionStatusType } from "../../enums/competitionStatusType";

// id = CompetitionID
type tCompetitionStatusUpdate = tID & {
    competitionStatusType: tCompetitionStatusType;
};

export default tCompetitionStatusUpdate;