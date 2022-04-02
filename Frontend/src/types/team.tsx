import { tTeamUser, tUser, tUserIDAndGamerTag } from "./authentication"
import { tCompetition } from "./competition"
import { tEnum, tID } from "./defaults/generics"
import { tAdditionalAttribute } from "./defaults/tables"
import { tTimestamp } from "./defaults/timestamp"
import { tTeamStatusType } from "./enums/teamStatusType"

export type tTeamStatus = tEnum & {
    teamStatusId: number,
    teamStatusDescription: tTeamStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp,
    additionalAttributes?: tAdditionalAttribute[]
}

export type tTeamInvitationLink = tEnum & {
    teamInvitationLink: string,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp,
    additionalAttributes?: tAdditionalAttribute[]
}

export type tTeam = tEnum & {
    teamName: string,
    playerTeam: boolean,
    creationTime: tTimestamp,
    teamInvitationLinks: tTeamInvitationLink[],
    teamStatus: tTeamStatus[],
    additionalAttributes?: tAdditionalAttribute[]
}

export type tTeamComposition = tEnum & {
    team: tTeam,
    user: tUser,
    admin: boolean,
    creationTime: tTimestamp
}

// --------- CUSTOM DTOs --------- //

export type tUserTeam = tID & {
    team: tTeam,
    admin: boolean,
    creationTime: tTimestamp
}

export type tTeamDetail = {
    team: tTeam,
    users: tTeamUser[],
    competitions: tCompetition[],
    possibleUsers: tUser[]
}

export type tTeamIDAndName = tID & {
    teamName: string
}

export type tTeamAndUser = {
    team: tTeamIDAndName,
    users: tUserIDAndGamerTag[]
}