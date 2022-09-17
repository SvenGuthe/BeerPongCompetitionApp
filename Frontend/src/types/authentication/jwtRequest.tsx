// --------- CUSTOM Datatypes --------- //

import { tUser } from "../user";

type tJwtRequest = {
    jwtToken: string;
    user: tUser
};

export default tJwtRequest;