// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";

// id = TeamID
type tTeamIDAndName = tID & {
  teamName: string;
};

export default tTeamIDAndName;
