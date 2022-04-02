import { tUser, tUserIDAndGamerTag } from "./authentication";
import { tEnum, tID } from "./defaults/generics";
import { tAdditionalAttribute } from "./defaults/tables";
import { tTimestamp } from "./defaults/timestamp";
import { tBillingStatusType } from "./enums/billingStatusType";
import { tCompetitionAdminStatusType } from "./enums/competitionAdminStatusType";
import { tCompetitionPlayerStatusType } from "./enums/competitionPlayerStatusType";
import { tRegistrationStatusType } from "./enums/registrationStatusType";
import { tTeam, tTeamAndUser } from "./team";

export type tBillingStatus = tEnum & {
    billingStatusId: number,
    billingStatusDescription: tBillingStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionAdminStatus = tEnum & {
    competitionAdminStatusId: number,
    competitionAdminStatusDescription: tCompetitionAdminStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionPlayerStatus = tEnum & {
    competitionPlayerStatusDescription: tCompetitionPlayerStatusType,
    creationTime: tTimestamp
}

export type tCompetitionStatus = tEnum & {
    competitionStatusId: number,
    competitionStatusType: tCompetitionAdminStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp,
    additionalAttributes?: tAdditionalAttribute[]
}

export type tRegistrationStatus = tEnum & {
    registrationStatusId: number,
    registrationStatusDescription: tRegistrationStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo: tTimestamp
}

export type tCompetitionAdmin = tID & {
    user: tUser,
    creationTime: tTimestamp,
    competitionAdminStatus: tCompetitionAdminStatus[]
}

export type tCompetitionPlayer = tID & {
    user: tUser,
    competitionPlayerStatus: tCompetitionPlayerStatus,
    creationTime: tTimestamp
}

export type tCompetitionTeam = tID & {
    competitionTeamName: string,
    creationTime: tTimestamp,
    team: tTeam,
    competitionPlayer: tCompetitionPlayer[],
    billingStatus: tBillingStatus[],
    registrationStatus: tRegistrationStatus[]
}

export type tCompetition = tID & {
    competitionName: string,
    competitionStartTimestamp: tTimestamp,
    minTeams: number,
    maxTeams: number,
    fee: number,
    registrationStart: tTimestamp,
    registrationEnd: tTimestamp,
    setOfRules: string,
    creationTime: tTimestamp,
    competitionStatus: tCompetitionStatus[],
    competitionTeams: tCompetitionTeam[],
    competitionAdmins: tCompetitionAdmin[],
    additionalAttributes?: tAdditionalAttribute[]
}

// --------- CUSTOM DTOs --------- //

export type tCompetitionDetail = {
    competition: tCompetition,
    possibleAdminUsers: tUserIDAndGamerTag[],
    possiblePlayers: tUserIDAndGamerTag[],
    teams: tTeamAndUser[]
}