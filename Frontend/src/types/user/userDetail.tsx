// --------- CUSTOM Datatypes --------- //

import tCompetition from "../competition/competition";
import tUserTeam from "../team/userTeam";
import tUser from "./user";

type tUserDetail = {
  user: tUser;
  teams: tUserTeam[];
  competitionsWhereAdmin: tCompetition[];
  competitionsWherePlayer: tCompetition[];
};

export default tUserDetail;
