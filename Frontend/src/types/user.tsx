import { tTimestamp } from "./defaults/timestamp";
import { Privilege } from "./enums/privilege";
import { Role } from "./enums/role";
import { UserStatus } from "./enums/userStatus";

export type tAuthenticatedUser = {
    creationTime: {
        date: number,
        day: number,
        hours: number,
        minutes: number,
        month: number,
        nanos: number,
        seconds: number,
        time: number,
        timezoneOffset: number,
        year: number
    },
    email: string,
    enabled: boolean,
    firstName: string,
    gamerTag: string,
    lastName: string,
    password: string,
    teamCompositions: [
        {
            admin: boolean,
            creationTime: {
                date: number,
                day: number,
                hours: number,
                minutes: number,
                month: number,
                nanos: number,
                seconds: number,
                time: number,
                timezoneOffset: number,
                year: number
            },
            id: number
        }
    ],
    userId: number,
    userStatus: {
        userStatus: UserStatus,
        userStatusId: number
    }
};

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
    roles: {
        name: Role,
        privileges:
        {
            name: Privilege,
            privilegeId: number
        }[],
        roleId: number
    }[],
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