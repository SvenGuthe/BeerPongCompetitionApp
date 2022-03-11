import { tEnum } from "./defaults/generics"
import { tTimestamp } from "./defaults/timestamp"
import { tTeamStatusType } from "./enums/teamStatusType"

export type tTeamStatus = tEnum & {
    teamStatusId: number,
    teamStatusDescription: tTeamStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tTeamInvitationLink = tEnum & {
    teamInvitationLink: string,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tTeam = tEnum & {
    teamName: string,
    isPlayerTeam: boolean,
    creationTime: tTimestamp,
    teamInvitationLinks: tTeamInvitationLink[],
    teamStatus: tTeamStatus[]
}