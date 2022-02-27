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
        userStatus: string,
        userStatusId: number
    }
};