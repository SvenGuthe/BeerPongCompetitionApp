// --------- CUSTOM Datatypes --------- //

import tCompetition from "../competition/competition";
import tTeamUser from "../user/teamUser";
import tUser from "../user/user";
import tTeam from "./team";

type tTeamDetail = {
  team: tTeam;
  users: tTeamUser[];
  competitions: tCompetition[];
  possibleUsers: tUser[];
};

export default tTeamDetail;
