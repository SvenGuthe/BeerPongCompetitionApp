import { tCompetition } from "./competition";
import { tEnum, tID } from "./defaults/generics";
import { tAdditionalAttributes } from "./defaults/tables";
import { tTimestamp } from "./defaults/timestamp";
import { tSecurityPrivilege } from "./enums/securityPrivilege";
import { tSecurityRole } from "./enums/securityRole";
import { tUserStatusType } from "./enums/userStatusType";
import { tUserTeam } from "./team";

export type tConfirmationToken = tID & tAdditionalAttributes & {
    confirmationToken: string,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tPrivilege = tEnum & {
    privilege: tSecurityPrivilege,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tRole = tEnum & {
    role: tSecurityRole,
    validFrom: tTimestamp,
    validTo?: tTimestamp,
    privileges: tPrivilege[]
}

export type tUserStatus = tEnum & {
    userStatus: tUserStatusType,
    validFrom: tTimestamp,
    validTo?: tTimestamp
}

export type tUser = tID & tAdditionalAttributes & {
    firstName: string,
    lastName: string,
    gamerTag: string,
    email: string,
    enabled: boolean,
    creationTime: tTimestamp,
    roles: tRole[],
    userStatus: tUserStatus[],
    confirmationToken: tConfirmationToken[]
}

// --------- CUSTOM DTOs --------- //

export type tUserDetail = {
    user: tUser,
    teams: tUserTeam[],
    competitionsWhereAdmin: tCompetition[],
    competitionsWherePlayer: tCompetition[]
}

export type tTeamUser = tID & {
    user: tUser,
    admin: boolean,
    creationTime: tTimestamp
}

export type tUserIDAndGamerTag = tID & {
    gamerTag: string
}

export type tUserUpdate = tID & {
    firstName: string,
    lastName: string,
    gamerTag: string,
    email: string,
    enabled: boolean,
    userStatusType: tUserStatusType,
    roles: tSecurityRole[]
}

export type tConfirmationTokenAdd = tID & {
    confirmationToken: string
}

// --------- CUSTOM Datatypes (no DTO for now) --------- //

// TODO: Transform to DTOs

export type tLogin = {
    email: string,
    password: string
};

export type tRegister = {
    email: string,
    firstName: string,
    gamerTag: string,
    lastName: string,
    password: string
};