// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";

// id = CompetitionID
type tCompetitionUpdate = tID & {
  competitionName?: string;
  competitionStartTimestamp?: string; //TODO: Update to tTimestamp
  minTeams?: number;
  maxTeams?: number;
  fee?: number;
  registrationStart?: string; //TODO: Update to tTimestamp
  registrationEnd?: string; //TODO: Update to tTimestamp
  setOfRules?: string;
};

export default tCompetitionUpdate;
