// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tCompetitionAdminStatusType } from "../../enums/competitionAdminStatusType";

// id = CompetitionAdminID
type tCompetitionAdminStatusUpdate = tID & {
    competitionAdminStatusType: tCompetitionAdminStatusType;
};

export default tCompetitionAdminStatusUpdate;