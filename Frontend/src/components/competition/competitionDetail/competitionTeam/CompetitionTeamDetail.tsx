import { useMemo } from "react";
import { tUserIDAndGamerTag } from "../../../../types/authentication";
import { tCompetitionTeam } from "../../../../types/competition";
import { tTeamAndUser } from "../../../../types/team";
import EnumTable from "../../../enums/EnumTable";
import TableSection from "../../../layout/TableSection";
import BillingStatusAddRow from "./billingStatus/BillingStatusAddRow";
import CompetitionPlayerAdd from "./competitionPlayer/CompetitionPlayerAdd";
import CompetitionPlayerDetail from "./competitionPlayer/CompetitionPlayerDetail";
import CompetitionTeamDetailTable from "./CompetitionTeamDetailTable";
import RegistrationStatusAddRow from "./registrationStatus/RegistrationStatusAddRow";

const CompetitionTeamDetail: React.FC<{
    competitionTeamDetail: tCompetitionTeam,
    teams: tTeamAndUser[],
    users: tUserIDAndGamerTag[]
}> = (props) => {

    const competitionTeamDetail = props.competitionTeamDetail;

    const competitionTeamRegistrationStatus = useMemo(() => {
        return competitionTeamDetail.registrationStatus;
    }, [competitionTeamDetail])

    const competitionTeamBillingStatus = useMemo(() => {
        return competitionTeamDetail.billingStatus;
    }, [competitionTeamDetail])

    const competitionPlayer = useMemo(() => {
        return competitionTeamDetail.competitionPlayer;
    }, [competitionTeamDetail])

    const team = props.teams.filter(team => team.team.id === competitionTeamDetail.team.id)[0];

    return <>
        {competitionTeamDetail && <>
            <h4>{competitionTeamDetail.competitionTeamName}</h4>
            <CompetitionTeamDetailTable competitionTeamDetail={competitionTeamDetail} />
            {competitionTeamRegistrationStatus && <TableSection>
                <h5>Turnier Team Registrations Status</h5>
                <EnumTable enumData={competitionTeamRegistrationStatus.map(singleCompetitionTeamRegistrationStatus => {

                    const additionalAttributes = [
                        {
                            id: singleCompetitionTeamRegistrationStatus.id + "_validFrom",
                            value: singleCompetitionTeamRegistrationStatus.validFrom
                        },
                        {
                            id: singleCompetitionTeamRegistrationStatus.id + "_validTo",
                            value: singleCompetitionTeamRegistrationStatus.validTo
                        }
                    ]

                    const newCompetitionTeamRegistrationStatus = {
                        ...singleCompetitionTeamRegistrationStatus,
                        additionalAttributes: additionalAttributes
                    }

                    return newCompetitionTeamRegistrationStatus;

                })} wrapped addRow={<RegistrationStatusAddRow id={competitionTeamDetail.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {competitionTeamBillingStatus && <TableSection>
                <h5>Turnier Team Zahlungs Status</h5>
                <EnumTable enumData={competitionTeamBillingStatus.map(singleCompetitionTeamBillingStatus => {

                    const additionalAttributes = [
                        {
                            id: singleCompetitionTeamBillingStatus.id + "_validFrom",
                            value: singleCompetitionTeamBillingStatus.validFrom
                        },
                        {
                            id: singleCompetitionTeamBillingStatus.id + "_validTo",
                            value: singleCompetitionTeamBillingStatus.validTo
                        }
                    ]

                    const newCompetitionTeamBillingStatus = {
                        ...singleCompetitionTeamBillingStatus,
                        additionalAttributes: additionalAttributes
                    }

                    return newCompetitionTeamBillingStatus;

                })} wrapped addRow={<BillingStatusAddRow id={competitionTeamDetail.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {competitionPlayer && <TableSection>
                <h5>Turnier Spieler</h5>
                {competitionPlayer.map(singleCompetitionPlayer => {
                    return <TableSection key={singleCompetitionPlayer.id}>
                        <CompetitionPlayerDetail competitionPlayerDetail={singleCompetitionPlayer} />
                    </TableSection>
                })}
                <CompetitionPlayerAdd team={team} user={props.users} id={competitionTeamDetail.id} />
            </TableSection>}
        </>}
    </>;

};

export default CompetitionTeamDetail;