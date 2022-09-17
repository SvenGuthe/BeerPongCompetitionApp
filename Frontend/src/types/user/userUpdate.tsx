// --------- CUSTOM Datatypes --------- //

import { tID } from "../defaults/generics";
import { tSecurityRole } from "../enums/securityRole";
import { tUserStatusType } from "../enums/userStatusType";

// id = UserID
type tUserUpdate = tID & {
  firstName: string;
  lastName: string;
  gamerTag: string;
  email: string;
  enabled: boolean;
  userStatusType: tUserStatusType;
  roles: tSecurityRole[];
};

export default tUserUpdate;
