import { Privilege } from "./enums/privilege";
import { Role } from "./enums/role";

export type tPrivilege = {
    privilegeId: number,
    name: Privilege
}

export type tRole = {
    roleId: number,
    name: Role
}

export type tToken = {
    token: string,
    privileges: tPrivilege[],
    roles: tRole[]
};

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