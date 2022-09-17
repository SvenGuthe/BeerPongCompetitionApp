// --------- MODEL Datatypes --------- //

import tUserIDAndGamerTag from "../user/userIDAndGamerTag";
import tTeamIDAndName from "./teamIDAndName";

type tTeamAndUser = {
  team: tTeamIDAndName;
  users: tUserIDAndGamerTag[];
};

export default tTeamAndUser;
