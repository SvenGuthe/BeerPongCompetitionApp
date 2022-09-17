// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tAdditionalAttributes } from "../../defaults/tables";
import { tTimestamp } from "../../defaults/timestamp";

// id = ConfirmationTokenID
type tConfirmationToken = tID &
  tAdditionalAttributes & {
    confirmationToken: string;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
  };

export default tConfirmationToken;
