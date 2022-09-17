// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tSecurityRole } from "../../enums/securityRole";
import tPrivilege from "../privilege/privilege";

// id = RoleID
// value = SecurityRole
type tRole = tEnum & {
    role: tSecurityRole;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
    privileges: tPrivilege[];
};

export default tRole;