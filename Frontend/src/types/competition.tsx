import { tUser, tUserIDAndGamerTag } from "./authentication";
import { tEnum, tID } from "./defaults/generics";
import { tAdditionalAttributes } from "./defaults/tables";
import { tTimestamp } from "./defaults/timestamp";
import { tBillingStatusType } from "./enums/billingStatusType";
import { tCompetitionAdminStatusType } from "./enums/competitionAdminStatusType";
import { tCompetitionPlayerStatusType } from "./enums/competitionPlayerStatusType";
import { tCompetitionStatusType } from "./enums/competitionStatusType";
import { tRegistrationStatusType } from "./enums/registrationStatusType";
import { tTeam, tTeamAndUser } from "./team";

export type tBillingStatus = tEnum & {
    billingStatusId: number,
    billingStatusDescription: tBillingStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tCompetitionAdminStatus = tEnum & {
    competitionAdminStatusId: number,
    competitionAdminStatusDescription: tCompetitionAdminStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tCompetitionPlayerStatus = tEnum & {
    competitionPlayerStatusId: number,
    competitionPlayerStatusDescription: tCompetitionPlayerStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tCompetitionStatus = tEnum & tAdditionalAttributes & {
    competitionStatusId: number,
    competitionStatusType: tCompetitionAdminStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tRegistrationStatus = tEnum & {
    registrationStatusId: number,
    registrationStatusDescription: tRegistrationStatusType,
    creationTime: tTimestamp,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tCompetitionAdmin = tID & {
    user: tUser,
    creationTime: tTimestamp,
    competitionAdminStatus: tCompetitionAdminStatus[]
}

export type tCompetitionPlayer = tID & {
    user: tUser,
    competitionPlayerStatus: tCompetitionPlayerStatus[],
    creationTime: tTimestamp
}

export type tCompetitionTeam = tID & {
    competitionTeamName: string,
    creationTime: tTimestamp,
    team?: tTeam,
    competitionPlayer: tCompetitionPlayer[],
    billingStatus: tBillingStatus[],
    registrationStatus: tRegistrationStatus[]
}

export type tCompetition = tID & tAdditionalAttributes & {
    competitionName: string,
    competitionStartTimestamp?: tTimestamp,
    minTeams?: number,
    maxTeams?: number,
    fee?: number,
    registrationStart?: tTimestamp,
    registrationEnd?: tTimestamp,
    setOfRules?: string,
    creationTime: tTimestamp,
    competitionStatus: tCompetitionStatus[],
    competitionTeams: tCompetitionTeam[],
    competitionAdmins: tCompetitionAdmin[]
}

// --------- CUSTOM DTOs --------- //

export type tCompetitionDetail = {
    competition: tCompetition,
    possibleAdminUsers: tUserIDAndGamerTag[],
    possiblePlayers: tUserIDAndGamerTag[],
    teams: tTeamAndUser[]
}

export type tCompetitionStatusUpdate = tID & {
    competitionStatusType: tCompetitionStatusType
};

export type tCompetitionAdminStatusUpdate = tID & {
    competitionAdminStatusType: tCompetitionAdminStatusType
}

export type tRegistrationStatusUpdate = tID & {
    registrationStatusType: tRegistrationStatusType
}

export type tBillingStatusUpdate = tID & {
    billingStatusType: tBillingStatusType
}

export type tCompetitionAdminAdd = tID & {
    userId: number
}

export type tCompetitionPlayerAdd = tID & {
    userId: number
}

export type tCompetitionTeamAdd = tID & {
    teamname: string,
    password: string,
    teamId?: number,
    playerIds: number[]
}

export type tCompetitionUpdate = tID & {
    competitionName: string,
    competitionStartTimestamp: string, // TODO: Convert to tTimestamp
    minTeams: number,
    maxTeams: number,
    fee: number,
    registrationStart: string, // TODO: Convert to tTimestamp
    registrationEnd: string, // TODO: Convert to tTimestamp
    setOfRules: string
}

export type tCompetitionPlayerStatusUpdate = tID & {
    competitionPlayerStatusType: tCompetitionPlayerStatusType
}

export type tCompetitionTeamUpdate = tID & {
    teamname: string,
    teamId?: number
}