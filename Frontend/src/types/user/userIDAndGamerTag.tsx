// --------- COSTUM Datatypes --------- //

import { tID } from "../defaults/generics";

// id = UserID
type tUserIDAndGamerTag = tID & {
  gamerTag: string;
};

export default tUserIDAndGamerTag;
