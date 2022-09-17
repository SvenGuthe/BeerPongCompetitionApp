// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tSecurityPrivilege } from "../../enums/securityPrivilege";

// id = PrivilegeID
// value = SecurityPrivilege
type tPrivilege = tEnum & {
  privilege: tSecurityPrivilege;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

export default tPrivilege;
