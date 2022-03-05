import { tTimestamp } from "./defaults/timestamp";
import { Privilege } from "./enums/privilege";
import { Role } from "./enums/role";
import { UserStatus } from "./enums/userStatus";

export type tPrivilege = {
    privilegeId: number,
    name: Privilege
}

export type tRole = {
    roleId: number,
    name: Role
    privileges: tPrivilege[]
}

export type tUserDetail = {
    confirmationToken: {
        confirmationToken: string,
        createdDate: tTimestamp,
        tokenid: number
    }[],
    creationTime: tTimestamp,
    email: string,
    enabled: boolean,
    firstName: string,
    gamerTag: string,
    lastName: string,
    roles: tRole[],
    userId: number,
    userStatus: {
        userStatus: UserStatus,
        userStatusId: number
    },
    teams: {
        admin: boolean,
        creationTime: tTimestamp,
        id: number,
        playerTeam: boolean,
        teamName: string
    }[]
}