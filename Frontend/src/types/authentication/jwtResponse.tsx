// --------- CUSTOM Datatypes --------- //

import tUser from "../user/user";

type tJwtResponse = {
  jwtToken: string;
  user: tUser;
};

export default tJwtResponse;
