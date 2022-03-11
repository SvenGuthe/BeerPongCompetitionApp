import { tCompetition } from "./competition";
import { tEnum, tID } from "./defaults/generics";
import { tTimestamp } from "./defaults/timestamp";
import { tSecurityPrivilege } from "./enums/securityPrivilege";
import { tSecurityRole } from "./enums/securityRole";
import { tUserStatusType } from "./enums/userStatusType";
import { tUserTeam } from "./team";

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

export type tConfirmationToken = tID & {
    confirmationToken: string,
    createdDate: tTimestamp
}

export type tPrivilege = tEnum & {
    privilege: tSecurityPrivilege
}

export type tRole = tEnum & {
    role: tSecurityRole,
    privileges: tPrivilege[]
}

export type tUserStatus = tEnum & {
    userStatus: tUserStatusType
}

export type tUser = tID & {
    firstName: string,
    lastName: string,
    gamerTag: string,
    email: string,
    enabled: boolean,
    creationTime: tTimestamp,
    roles: tRole[],
    userStatus: tUserStatus,
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
    isAdmin: boolean,
    creationTime: tTimestamp
}