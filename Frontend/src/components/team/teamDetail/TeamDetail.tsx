import React, { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addTeam, removeTeamDetail, storeTeamDetail } from "../../../store/team/team-store";
import { updateTeamComposition } from "../../../store/team/team-store-actions";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import FormItem from "../../ui/form/FormItem";
import UserTable from "../../user/userOverview/UserTable";
import TeamCompositionAdd from "./teamComposition/TeamCompositionAdd";
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
        return removeDuplicates(teamDetail?.competitions);
    }, [teamDetail])

    const onSaveHandler = (teamCompositionId: number, newValue: boolean) => {
        dispatch(updateTeamComposition(teamCompositionId, newValue));
    }

    useEffect(() => {

        if (id) {
            dispatch(getRequestWithID(+id, "/team/team", [addTeam, storeTeamDetail]));
        }

        return () => {
            dispatch(removeTeamDetail());
        }

    }, [id, dispatch]);

    const refs = useMemo(() => {
        return Array.from({ length: users ? users!.length : 0 }).map(() => React.createRef<HTMLInputElement>())
    }, [users]);

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

                })} wrapped addRow={<TeamStatusAddRow id={teamDetail.team.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {teamInvitationLinks && <TableSection>
                <h3>Team Einladungslinks</h3>
                <TeamInvitationLinkTable id={teamDetail.team.id} teamInvitationLinks={teamInvitationLinks} wrapped />
            </TableSection>}
            {users && <TableSection>
                <h3>Nutzer</h3>
                <UserTable users={users.map((user, i) => {
                    const additionalAttributes = [
                        {
                            id: user.id + "_isAdmin",
                            value: String(user.admin),
                            reactElement: <FormItem ref={refs[i]} defaultValue={user.admin} saveValue={(newValue, changed) => onSaveHandler(user.id, newValue as boolean)} />
                        }
                    ]

                    const newUser = {
                        ...user.user,
                        additionalAttributes: additionalAttributes
                    }

                    return newUser;
                })} wrapped additionalAttributesHeader={["Admin"]} />
                <TeamCompositionAdd id={teamDetail.team.id} users={teamDetail.possibleUsers} />
            </TableSection>}
            {competitions && <TableSection>
                <h3>Turniere</h3>
                <CompetitionTable competitions={competitions} wrapped />
            </TableSection>}
        </>}
    </>;

}

export default TeamDetail;