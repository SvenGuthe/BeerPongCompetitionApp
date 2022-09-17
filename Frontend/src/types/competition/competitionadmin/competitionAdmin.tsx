// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tUser } from "../../user";
import tCompetitionAdminStatus from "./competitionAdminStatus";

// id = CompetitionAdminID
type tCompetitionAdmin = tID & {
    user: tUser;
    creationTime: tTimestamp;
    competitionAdminStatus: tCompetitionAdminStatus[];
};

export default tCompetitionAdmin;