// --------- CUSTOM Datatypes --------- //

import tUser from "../user/user";

type tJwtRequest = {
  jwtToken: string;
  user: tUser;
};

export default tJwtRequest;
