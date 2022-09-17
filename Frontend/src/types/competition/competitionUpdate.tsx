// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";
import { tTimestamp } from "../defaults/timestamp";

// id = CompetitionID
type tCompetitionUpdate = tID & {
    competitionName?: string;
    competitionStartTimestamp?: tTimestamp;
    minTeams?: number;
    maxTeams?: number;
    fee?: number;
    registrationStart?: tTimestamp;
    registrationEnd?: tTimestamp;
    setOfRules?: string;
};

export default tCompetitionUpdate;