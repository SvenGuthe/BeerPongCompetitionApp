import { tID } from "./defaults/generics";
import { tTimestamp } from "./defaults/timestamp";
import { Privilege } from "./enums/privilege";
import { Role } from "./enums/role";
import { UserStatus } from "./enums/userStatus";

export type tPrivilege = tID & {
    name: Privilege
}

export type tRole = tID & {
    name: Role
    privileges: tPrivilege[]
}

export type tUserDetail = tID & {
    confirmationToken: {
        confirmationToken: string,
        createdDate: tTimestamp,
        id: number
    }[],
    creationTime: tTimestamp,
    email: string,
    enabled: boolean,
    firstName: string,
    gamerTag: string,
    lastName: string,
    roles: tRole[],
    userStatus: {
        userStatus: UserStatus,
        id: number
    },
    teams: {
        admin: boolean,
        creationTime: tTimestamp,
        id: number,
        playerTeam: boolean,
        teamName: string
    }[]
}