// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import tTeam from "../../team/team";
import tBillingStatus from "../billing/billingStatus";
import tCompetitionPlayer from "../competitionplayer/competitionPlayer";
import tRegistrationStatus from "../registration/registrationStatus";

// id = CompetitionTeamID
type tCompetitionTeam = tID & {
  competitionTeamName: string;
  creationTime: tTimestamp;
  team?: tTeam;
  competitionPlayer: tCompetitionPlayer[];
  billingStatus: tBillingStatus[];
  registrationStatus: tRegistrationStatus[];
};

export default tCompetitionTeam;
