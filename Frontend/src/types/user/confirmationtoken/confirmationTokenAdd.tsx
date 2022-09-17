// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = UserID
type tConfirmationTokenAdd = tID & {
  confirmationToken: string;
};

export default tConfirmationTokenAdd;
