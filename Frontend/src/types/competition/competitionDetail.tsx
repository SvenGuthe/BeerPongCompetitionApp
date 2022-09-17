// --------- CUSTOM Datatypes --------- //

import tTeamAndUser from "../team/teamAndUser";
import tUserIDAndGamerTag from "../user/userIDAndGamerTag";
import tCompetition from "./competition";

type tCompetitionDetail = {
  competition: tCompetition;
  possibleAdminUsers: tUserIDAndGamerTag[];
  possiblePlayers: tUserIDAndGamerTag[];
  teams: tTeamAndUser[];
};

export default tCompetitionDetail;
