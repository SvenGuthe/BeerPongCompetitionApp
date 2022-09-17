// --------- CUSTOM Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tRegistrationStatusType } from "../../enums/registrationStatusType";

// id = CompetitionTeamID
type tRegistrationStatusUpdate = tID & {
  registrationStatusType: tRegistrationStatusType;
};

export default tRegistrationStatusUpdate;
