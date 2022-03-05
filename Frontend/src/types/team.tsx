import { tTimestamp } from "./defaults/timestamp"
import { TeamStatus } from "./enums/teamStatus"
import { UserStatus } from "./enums/userStatus"

export type tTeamDetail = {
    creationTime: tTimestamp,
    id: number,
    password: string,
    playerTeam: boolean,
    members: tTeamMemers[],
    teamInvitationLinkHistories: tTeamInvitationLink[],
    teamName: string,
    teamStatusHistories: tTeamStatusHistory[]
}

export type tTeamStatusHistory = {
    id: number,
    teamStatusDescription: TeamStatus,
    validFrom: tTimestamp,
    validTo: tTimestamp
};

export type tTeamInvitationLink = {
    id: number,
    teamInvitationLink: string,
    validFrom: tTimestamp,
    validTo: tTimestamp
};

export type tTeamMemers = {
    admin: boolean,
    creationTime: tTimestamp,
    enabled: boolean,
    firstName: string,
    gamerTag: string,
    lastName: string,
    userId: number,
    userStatus: UserStatus
};

export type tTeamMetaData = {
    id: number,
    teamName: string,
    currentTeamStatusType: TeamStatus
}