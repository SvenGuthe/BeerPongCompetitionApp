// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tRegistrationStatusType } from "../../enums/registrationStatusType";

// id = RegistrationStatusID
// value = RegistrationStatusType
type tRegistrationStatus = tEnum & {
  registrationStatusDescription: tRegistrationStatusType;
  creationTime: tTimestamp;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

export default tRegistrationStatus;
