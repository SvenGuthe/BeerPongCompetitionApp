// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";
import { tTimestamp } from "../../defaults/timestamp";

// id = TeamInvitationLinkID
type tTeamInvitationLink = tID & {
    teamInvitationLink: string;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
}

export default tTeamInvitationLink;