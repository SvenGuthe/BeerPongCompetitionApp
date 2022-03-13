import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addTeam, storeTeamDetail } from "../../../store/team/team-store";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import TableSection from "../../layout/TableSection";
import FormItem from "../../ui/form/FormItem";
import UserTable from "../../user/userOverview/UserTable";
import TeamDetailTable from "./TeamDetailTable";
import TeamInvitationLinkTable from "./teamInvitationLink/TeamInvitationLinkTable";
import TeamStatusTable from "./teamStatus/TeamStatusTable";

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

    }, [id, dispatch]);

    return <>
        {teamDetail && <>
            <TeamDetailTable team={teamDetail.team} />
            {teamStatus && <TableSection>
                <h3>Team Status</h3>
                <TeamStatusTable teamStatus={teamStatus} wrapped />
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