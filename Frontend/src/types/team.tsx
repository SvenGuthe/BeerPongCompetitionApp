import { tTimestamp } from "./defaults/timestamp"
import { TeamStatus } from "./enums/teamStatus"
import { UserStatus } from "./enums/userStatus"

export type tTeam = {
    creationTime: tTimestamp,
    id: number,
    password: string,
    playerTeam: boolean,
    teamCompositions: {
        admin: boolean,
        creationTime: tTimestamp,
        id: number
    }[],
    teamInvitationLinks: {
        creationTime: tTimestamp,
        id: number,
        teamInvitationLink: string
    }[],
    teamName: string,
    teamStatusHistories: [
        {
            id: number,
            validFrom: tTimestamp,
            validTo: tTimestamp
        }
    ]
}

export type tTeamWithUsers = {
    creationTime: tTimestamp,
    id: number,
    members: {
          admin: boolean,
          creationTime: tTimestamp,
          enabled: boolean,
          firstName: string,
          gamerTag: string,
          lastName: string,
          userId: number,
          userStatus: UserStatus
        }[],
      playerTeam: boolean,
      teamName: string,
      teamStatusHistories: {
        id: number,
        teamStatusDescription: TeamStatus,
        validFrom: tTimestamp,
        validTo: tTimestamp
      }[]
}