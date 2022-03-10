import { tID } from "./defaults/generics"
import { tTimestamp } from "./defaults/timestamp"
import { BillingStatus } from "./enums/billingStatus"
import { CompetitionAdminStatus } from "./enums/competitionAdminStatus"
import { CompetitionPlayerStatus } from "./enums/competitionPlayerStatus"
import { CompetitionStatus } from "./enums/competitionStatus"
import { RegistrationStatus } from "./enums/registrationStatus"

export type tCompetitionAdminStatusHistory = tID & {
    competitionAdminStatusType: CompetitionAdminStatus,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionAdmin = tID & {
    competitionAdminStatusHistories: tCompetitionAdminStatusHistory[],
    gamerTag: string,
    userId: number
}

export type tCompetitionStatusHistory = tID & {
    competitionStatusType: CompetitionStatus,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tBillingStatusHistory = tID & {
    billingStatusType: BillingStatus,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionPlayer = tID & {
    competitionPlayerStatusType: CompetitionPlayerStatus,
    gamerTag: string,
    userId: number
}

export type tRegistrationStatusHistory = tID & {
    registrationStatusType: RegistrationStatus
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tTeam = tID & {
    teamName: string
}

export type tCompetitionTeam = tID & {
    billingStatusHistories: tBillingStatusHistory[],
    competitionPlayers: tCompetitionPlayer[],
    competitionTeamName: string,
    creationTime: tTimestamp,
    registrationStatusHistories: tRegistrationStatusHistory[],
    team: tTeam
}


export type tCompetitionDetail = {
    competitionAdmins: tCompetitionAdmin[],
    competitionName: string,
    competitionStartTimestamp: tTimestamp,
    competitionStatusHistories: tCompetitionStatusHistory[],
    competitionTeams: tCompetitionTeam[],
    creationTime: tTimestamp,
    fee: number,
    id: number,
    maxTeams: number,
    minTeams: number,
    registrationEnd: tTimestamp,
    registrationStart: tTimestamp,
    setOfRules: string
}