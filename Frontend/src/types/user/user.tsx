// --------- MODEL Datatypes --------- //

import { tID } from "../defaults/generics";
import { tAdditionalAttributes } from "../defaults/tables";
import { tTimestamp } from "../defaults/timestamp";
import tConfirmationToken from "./confirmationtoken/confirmationToken";
import tRole from "./role/role";
import tUserStatus from "./userstatus/userStatus";

// id = UserID
type tUser = tID &
  tAdditionalAttributes & {
    firstName: string;
    lastName: string;
    gamerTag: string;
    email: string;
    enabled: boolean;
    creationTime: tTimestamp;
    roles: tRole[];
    userStatus: tUserStatus[];
    confirmationToken: tConfirmationToken[];
  };

export default tUser;
