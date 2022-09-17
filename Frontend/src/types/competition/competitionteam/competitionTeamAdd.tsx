// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = CompetitionID
type tCompetitionTeamAdd = tID & {
  teamname: string;
  password: string;
  teamId?: number;
  playerIds: number[];
};

export default tCompetitionTeamAdd;
