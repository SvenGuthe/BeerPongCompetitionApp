import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addUser, storeUserDetail } from "../../../store/user/user-store";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import TeamTable from "../../team/teamOverview/TeamTable";
import { CompetitionAdminStatusTypeInput } from "../../ui/form/PredefinedSelectInputs";
import ConfirmationTokenTable from "./confirmationToken/ConfirmationTokenTable";
import UserDetailsTable from "./UserDetailTable";

const UserDetails: React.FC = () => {

    const dispatch = useDispatch();
    const id = useParams().id;

    const { userDetail } = useSelector((state: RootState) => {
        return {
            userDetail: state.user.userDetail
        };
    });

    const privileges = useMemo(() => {
        return removeDuplicates(userDetail?.user.roles.flatMap(role => role.privileges));
    }, [userDetail]);

    const confirmationToken = useMemo(() => {
        return userDetail?.user.confirmationToken;
    }, [userDetail]);

    const teams = useMemo(() => {
        return userDetail?.teams;
    }, [userDetail]);

    const competitionsAdmin = useMemo(() => {
        return userDetail?.competitionsWhereAdmin;
    }, [userDetail]);

    const competitionsPlayer = useMemo(() => {
        return userDetail?.competitionsWherePlayer;
    }, [userDetail]);

    useEffect(() => {

        if (id) {
            dispatch(getRequestWithID(+id, "/authentication/user", [addUser, storeUserDetail]));
        }

    }, [id, dispatch]);

    return <>
        {userDetail && <>
            <UserDetailsTable user={userDetail.user} />
            {privileges && <TableSection>
                <h3>Privileges</h3>
                <EnumTable enumData={privileges} wrapped />
            </TableSection>}
            {confirmationToken && <TableSection>
                <h3>Confirmation Token</h3>
                <ConfirmationTokenTable confirmationToken={confirmationToken} wrapped />
            </TableSection>}
            {teams && <TableSection>
                <h3>Teams</h3>
                <TeamTable teams={teams.map(team => {

                    const additionalAttributes = [{
                        id: team.id + "_admin",
                        value: String(team.admin)
                    }, {
                        id: team.id + "_creationTime",
                        value: String(team.creationTime)
                    }]

                    const newTeam = {
                        ...team.team,
                        additionalAttributes: additionalAttributes
                    };

                    return newTeam;
                })} additionalAttributesHeader={["Admin", "Eingetreten"]} wrapped />
            </TableSection>}
            {competitionsAdmin && <TableSection>
                <h3>Competitions (as Admin)</h3>
                <CompetitionTable competitions={competitionsAdmin.map(competition => {

                    const competitionAdmin = competition.competitionAdmins.filter(competitionAdmin => {
                        return competitionAdmin.user.id === userDetail.user.id;
                    })[0];

                    const competitionStatus = competitionAdmin.competitionAdminStatus.filter(singleCompetitionAdminStatus => {
                        return singleCompetitionAdminStatus.validTo === null;
                    })[0];

                    const additionalAttributes = [
                        {
                            id: competition.id + "_administratorStatus",
                            value: competitionStatus.competitionAdminStatusDescription,
                            reactElement: <CompetitionAdminStatusTypeInput defaultValue={competitionStatus.competitionAdminStatusDescription} saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        }
                    ];

                    const newCompetition = {
                        ...competition,
                        additionalAttributes: additionalAttributes
                    };

                    return newCompetition;

                })} wrapped additionalAttributesHeader={["Admin Status"]} />
            </TableSection>}
            {competitionsPlayer && <TableSection>
                <h3>Competitions (as Player)</h3>
                <CompetitionTable competitions={competitionsPlayer.map(competition => {

                    const competitionTeam = competition.competitionTeams.filter(competitionTeam => {
                        return competitionTeam.competitionPlayer.filter(singleCompetitionPlayer => {
                            return singleCompetitionPlayer.user.id === userDetail.user.id;
                        }).length === 1
                    })[0];

                    const additionalAttributes = [
                        {
                            id: competition.id + "_mainTeam",
                            value: competitionTeam.team.teamName
                        },
                        {
                            id: competition.id + "_competitionTeamName",
                            value: competitionTeam.competitionTeamName
                        }
                    ];

                    const newCompetition = {
                        ...competition,
                        additionalAttributes: additionalAttributes
                    };

                    return newCompetition;

                })} wrapped additionalAttributesHeader={["Registriert mit Hauptteam", "Competition Team Name"]} />
            </TableSection>}
        </>
        }
    </>;
};

export default UserDetails;