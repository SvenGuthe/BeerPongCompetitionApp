// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tAdditionalAttributes } from "../../defaults/tables";
import { tTimestamp } from "../../defaults/timestamp";

// id = TeamInvitationLinkID
type tTeamInvitationLink = tID &
  tAdditionalAttributes & {
    teamInvitationLink: string;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
  };

export default tTeamInvitationLink;
