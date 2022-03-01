export type tPrivilege = {
    privilegeId: number,
    name: string
}

export type tRole = {
    roleId: number,
    name: string
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