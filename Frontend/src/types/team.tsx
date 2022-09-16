import { tTeamUser, tUser, tUserIDAndGamerTag } from "./user";
import { tCompetition } from "./competition";
import { tEnum, tID } from "./defaults/generics";
import { tAdditionalAttributes } from "./defaults/tables";
import { tTimestamp } from "./defaults/timestamp";
import { tTeamCompositionStatusType } from "./enums/teamCompositionStatusType";
import { tTeamStatusType } from "./enums/teamStatusType";

export type tTeamStatus = tEnum &
  tAdditionalAttributes & {
    teamStatusId: number;
    teamStatusDescription: tTeamStatusType;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
  };

export type tTeamInvitationLink = tEnum &
  tAdditionalAttributes & {
    teamInvitationLink: string;
    creationTime: tTimestamp;
    validFrom: tTimestamp;
    validTo?: tTimestamp;
  };

export type tTeam = tEnum &
  tAdditionalAttributes & {
    teamName: string;
    playerTeam: boolean;
    creationTime: tTimestamp;
    teamInvitationLinks: tTeamInvitationLink[];
    teamStatus: tTeamStatus[];
  };

export type tTeamComposition = tEnum & {
  team: tTeam;
  user: tUser;
  admin: boolean;
  creationTime: tTimestamp;
  teamCompositionStatus: tTeamCompositionStatus[];
};

export type tTeamCompositionStatus = tEnum & {
  teamCompositionStatusType: tTeamCompositionStatusType;
  validFrom: tTimestamp;
  validTo?: tTimestamp;
};

// --------- CUSTOM DTOs --------- //

export type tUserTeam = tID & {
  team: tTeam;
  admin: boolean;
  teamCompositionStatus: tTeamCompositionStatus[];
  creationTime: tTimestamp;
};

export type tTeamDetail = {
  team: tTeam;
  users: tTeamUser[];
  competitions: tCompetition[];
  possibleUsers: tUser[];
};

export type tTeamIDAndName = tID & {
  teamName: string;
};

export type tTeamAndUser = {
  team: tTeamIDAndName;
  users: tUserIDAndGamerTag[];
};

export type tTeamUpdate = tID & {
  teamName: string;
};

export type tTeamStatusUpdate = tID & {
  teamStatusType: tTeamStatusType;
};

export type tTeamInvitationLinkAdd = tID & {
  teamInvitationLink: string;
};

export type tTeamCompositionUpdate = tID & {
  isAdmin: boolean;
};

export type tTeamCompositionStatusUpdate = tID & {
  teamCompositionStatusType: tTeamCompositionStatusType;
};

export type tTeamCompositionAdd = tID & {
  userId: number;
  isAdmin: boolean;
};
