// --------- MODEL Datatypes --------- //

import { tID } from "../defaults/generics";
import { tAdditionalAttributes } from "../defaults/tables";
import { tTimestamp } from "../defaults/timestamp";
import tTeamInvitationLink from "./teaminvitationlink/teamInvitationLink";
import tTeamStatus from "./teamstatus/teamStatus";

// id = TeamID
type tTeam = tID &
  tAdditionalAttributes & {
    teamName: string;
    isPlayerTeam: boolean;
    creationTime: tTimestamp;
    teamInvitationLinks: tTeamInvitationLink[];
    teamStatus: tTeamStatus[];
  };

export default tTeam;
