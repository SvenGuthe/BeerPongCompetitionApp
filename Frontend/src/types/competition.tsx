import { tTimestamp } from "./defaults/timestamp"
import { BillingStatus } from "./enums/billingStatus"
import { CompetitionAdminStatus } from "./enums/competitionAdminStatus"
import { CompetitionPlayerStatus } from "./enums/competitionPlayerStatus"
import { CompetitionStatus } from "./enums/competitionStatus"
import { RegistrationStatus } from "./enums/registrationStatus"

export type tCompetitionAdminStatusHistory = {
    competitionAdminStatusType: CompetitionAdminStatus,
    id: number,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionAdmin = {
    competitionAdminStatusHistories: tCompetitionAdminStatusHistory[],
    gamerTag: string,
    id: number,
    userId: number
}

export type tCompetitionStatusHistory = {
    competitionStatusType: CompetitionStatus,
    id: number,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tBillingStatusHistory = {
    billingStatusType: BillingStatus,
    id: number,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionPlayer = {
    competitionPlayerStatusType: CompetitionPlayerStatus,
    gamerTag: string,
    id: number,
    userId: number
}

export type tRegistrationStatusHistory = {
    id: number,
    registrationStatusType: RegistrationStatus
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tTeam = {
    id: number,
    teamName: string
}

export type tCompetitionTeam = {
    billingStatusHistories: tBillingStatusHistory[],
    competitionPlayers: tCompetitionPlayer[],
    competitionTeamName: string,
    creationTime: tTimestamp,
    id: number,
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