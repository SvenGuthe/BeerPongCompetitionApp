// --------- MODEL Datatypes --------- //

import { tEnum } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";
import { tBillingStatusType } from "../../enums/billingStatusType";

// id = BillingStatusID
// value = BillingStatusType
type tBillingStatus = tEnum & {
  billingStatusDescription: tBillingStatusType;
  creationTime: tTimestamp;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

export default tBillingStatus;
