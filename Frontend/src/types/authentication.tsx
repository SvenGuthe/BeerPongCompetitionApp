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