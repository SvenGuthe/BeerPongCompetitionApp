import { tID } from "./defaults/generics"
import { tTimestamp } from "./defaults/timestamp"
import { TeamStatus } from "./enums/teamStatus"
import { UserStatus } from "./enums/userStatus"

export type tTeamDetail = tID & {
    creationTime: tTimestamp,
    password: string,
    playerTeam: boolean,
    members: tTeamMembers[],
    teamInvitationLinkHistories: tTeamInvitationLink[],
    teamName: string,
    teamStatusHistories: tTeamStatusHistory[]
}

export type tTeamStatusHistory = tID & {
    teamStatusDescription: TeamStatus,
    validFrom: tTimestamp,
    validTo: tTimestamp
};

export type tTeamInvitationLink = tID & {
    teamInvitationLink: string,
    validFrom: tTimestamp,
    validTo: tTimestamp
};

export type tTeamMembers = tID & {
    admin: boolean,
    creationTime: tTimestamp,
    enabled: boolean,
    firstName: string,
    gamerTag: string,
    lastName: string,
    userStatus: UserStatus
};

export type tTeamMetaData = tID & {
    teamName: string,
    currentTeamStatusType: TeamStatus
}