// --------- MODEL Datatypes --------- //

import { tID } from "../../defaults/generics";

// id = TeamID
type tTeamInvitationLinkAdd = tID & {
    teamInvitationLink: string;
};

export default tTeamInvitationLinkAdd;