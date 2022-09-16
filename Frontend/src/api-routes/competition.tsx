// Routes to handle the competition requests

const base = "/competition";

export const competitionStatusRoute = `${base}/competitionstatus`;
export const competitionTeamRoute = `${base}/competitionteam`;
export const competitionRoute = `${base}/competition`;

const baseCompetitionAdmin = `${base}/competitionadmin`;
export const competitionAdminStatusRoute = `${baseCompetitionAdmin}/competitionadminstatus`;
export const competitionAdminRoute = `${baseCompetitionAdmin}/competitionadmin`;

const baseCompetitionPlayer = `${base}/competitionplayer`;
export const competitionPlayerStatusRoute = `${baseCompetitionPlayer}/competitionplayerstatus`;
export const competitionPlayerRoute = `${baseCompetitionPlayer}/competitionplayer`;

const baseBillingStatus = `${base}/billing`;
export const billingStatusRoute = `${baseBillingStatus}/billingstatus`;

const baseRegistrationStatus = `${base}/registration`;
export const registrationStatusRoute = `${baseRegistrationStatus}/registrationstatus`;
