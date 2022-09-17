// --------- MODEL Datatypes --------- //

import { tID } from "../defaults/generics";
import { tTimestamp } from "../defaults/timestamp";
import tCompetitionAdmin from "./competitionadmin/competitionAdmin";
import tCompetitionStatus from "./competitionstatus/competitionStatus";
import tCompetitionTeam from "./competitionteam/competitionTeam";

// id = CompetitionID
type tCompetition = tID & {
    competitionName: string;
    competitionStartTimestamp?: tTimestamp;
    minTeams?: number;
    maxTeams?: number;
    fee?: number;
    registrationStart?: tTimestamp;
    registrationEnd?: tTimestamp;
    setOfRules?: string;
    creationTime: tTimestamp;
    competitionStatus: tCompetitionStatus[];
    competitionTeams: tCompetitionTeam[];
    competitionAdmins: tCompetitionAdmin[];
};

export default tCompetition;