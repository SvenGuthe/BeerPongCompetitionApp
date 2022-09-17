// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tBillingStatusType } from "../../enums/billingStatusType";

// id = BillingStatusID
type tBillingStatusUpdate = tID & {
    billingStatusType: tBillingStatusType;
};

export default tBillingStatusUpdate;