// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";

// id = ConfirmationTokenID
type tConfirmationToken = tID & {
    confirmationToken: string;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
};

export default tConfirmationToken;