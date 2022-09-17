// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";

// id = TeamID
type tTeamUpdate = tID & {
  teamName: string;
};

export default tTeamUpdate;
