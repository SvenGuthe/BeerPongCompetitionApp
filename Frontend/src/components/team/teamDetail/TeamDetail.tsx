import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addTeam, removeTeamDetail, storeTeamDetail } from "../../../store/team/team-store";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import FormItem from "../../ui/form/FormItem";
import UserTable from "../../user/userOverview/UserTable";
import TeamDetailTable from "./TeamDetailTable";
import TeamInvitationLinkTable from "./teamInvitationLink/TeamInvitationLinkTable";
import TeamStatusAddRow from "./teamStatus/TeamStatusAddRow";

const TeamDetail: React.FC = () => {

    const dispatch = useDispatch();
    const id = useParams().id;

    const { teamDetail } = useSelector((state: RootState) => {
        return {
            teamDetail: state.team.teamDetail
        };
    });

    const teamStatus = useMemo(() => {
        return teamDetail?.team.teamStatus;
    }, [teamDetail]);

    const teamInvitationLinks = useMemo(() => {
        return teamDetail?.team.teamInvitationLinks;
    }, [teamDetail]);

    const users = useMemo(() => {
        return teamDetail?.users;
    }, [teamDetail]);

    const competitions = useMemo(() => {
        return teamDetail?.competitions;
    }, [teamDetail])

    useEffect(() => {

        if (id) {
            dispatch(getRequestWithID(+id, "/team/team", [addTeam, storeTeamDetail]));
        }

        return () => {
            dispatch(removeTeamDetail());
        }

    }, [id, dispatch]);

    return <>
        {teamDetail && <>
            <TeamDetailTable team={teamDetail.team} />
            {teamStatus && <TableSection>
                <h3>Team Status</h3>
                <EnumTable enumData={teamStatus.map(singleTeamStatus => {

                    const additionalAttributes = [
                        {
                            id: singleTeamStatus.id + "_validFrom",
                            value: singleTeamStatus.validFrom
                        },
                        {
                            id: singleTeamStatus.id + "_validTo",
                            value: singleTeamStatus.validTo
                        }
                    ]

                    const newTeamStatus = {
                        ...singleTeamStatus,
                        additionalAttributes
                    }

                    return newTeamStatus;

                })} wrapped addRow={<TeamStatusAddRow />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {teamInvitationLinks && <TableSection>
                <h3>Team Einladungslinks</h3>
                <TeamInvitationLinkTable teamInvitationLinks={teamInvitationLinks} wrapped />
            </TableSection>}
            {users && <TableSection>
                <h3>Nutzer</h3>
                <UserTable users={users.map(user => {

                    const additionalAttributes = [
                        {
                            id: user.id + "_isAdmin",
                            value: String(user.isAdmin),
                            reactElement: <FormItem defaultValue={user.isAdmin} saveValue={(newValue, changed) => console.log(newValue, changed)} />
                        }
                    ]

                    const newUser = {
                        ...user.user,
                        additionalAttributes: additionalAttributes
                    }

                    return newUser;

                })} wrapped additionalAttributesHeader={["Admin"]} />
            </TableSection>}
            {competitions && <TableSection>
                <h3>Turniere</h3>
                <CompetitionTable competitions={competitions} wrapped />
            </TableSection>}
        </>}
    </>;

}

export default TeamDetail;