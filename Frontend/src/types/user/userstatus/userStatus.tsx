// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tUserStatusType } from "../../enums/userStatusType";

// id = UserStatusID
// value = UserStatusType
type tUserStatus = tEnum & {
    userStatus: tUserStatusType;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
}

export default tUserStatus;