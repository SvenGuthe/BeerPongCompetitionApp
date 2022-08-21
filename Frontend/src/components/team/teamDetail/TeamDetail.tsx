import React, { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addTeam, removeTeamDetail, storeTeamDetail } from "../../../store/team/team-store";
import { tEnum } from "../../../types/defaults/generics";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import UserDetailsTableTeam from "../../user/userDetail/UserDetailTableTeam";
import TeamCompositionAdd from "./teamComposition/TeamCompositionAdd";
import TeamCompositionStatusAddRow from "./teamComposition/TeamCompositionStatusAddRow";
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
                <EnumTable enumData={[...teamStatus.map(singleTeamStatus => {

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

                })].sort((a: tEnum, b: tEnum) => a.id - b.id)} wrapped addRow={<TeamStatusAddRow id={teamDetail.team.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
            </TableSection>}
            {teamInvitationLinks && <TableSection>
                <h3>Team Einladungslinks</h3>
                <TeamInvitationLinkTable id={teamDetail.team.id} teamInvitationLinks={teamInvitationLinks} wrapped />
            </TableSection>}
            {users && <TableSection>
                <h3>Team Mitglieder</h3>
                {users.map(user => {
                    return <TableSection key={user.id}>
                        <h4>{user.user.gamerTag}</h4>
                        <UserDetailsTableTeam user={user.user} admin={user.admin} teamCompositionId={user.id}></UserDetailsTableTeam>

                        <EnumTable enumData={[...user.teamCompositionStatus.map(singleTeamCompositionStatus => {

                            const additionalAttributes = [
                                {
                                    id: singleTeamCompositionStatus.id + "_validFrom",
                                    value: singleTeamCompositionStatus.validFrom
                                },
                                {
                                    id: singleTeamCompositionStatus.id + "_validTo",
                                    value: singleTeamCompositionStatus.validTo
                                }
                            ]

                            const newTeamCompositionStatus = {
                                ...singleTeamCompositionStatus,
                                additionalAttributes
                            }

                            return newTeamCompositionStatus;

                        })].sort((a: tEnum, b: tEnum) => a.id - b.id)} wrapped addRow={<TeamCompositionStatusAddRow id={user.id} />} additionalAttributesHeader={["Valide von", "Valide bis"]} />
                    </TableSection>
                })}
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