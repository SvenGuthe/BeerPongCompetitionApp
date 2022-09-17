import { useEffect, useMemo } from "react";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import {
  addUser,
  removeUserDetail,
  storeUserDetail,
} from "../../../store/user/user-store";
import { removeDuplicates } from "../../../utility/arrayFunctions";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import { userRoute } from "../../../api-routes/user";
import CompetitionTable from "../../competition/competitionOverview/CompetitionTable";
import EnumTable from "../../enums/EnumTable";
import TableSection from "../../layout/TableSection";
import TeamTable from "../../team/teamOverview/TeamTable";
import ConfirmationTokenTable from "./confirmationToken/ConfirmationTokenTable";
import UserDetailsTable from "./UserDetailTable";

/**
 * Component to show the user details and the privileges + confirmationtoken + teams + competition (where admin) + competition (where player)
 * @returns JSX with all the information of a user
 */
const UserDetails: React.FC = () => {
  const dispatch = useDispatch();

  // get the user id from the url params
  const userId = useParams().id;

  // get the user details from redux
  const { userDetail } = useSelector((state: RootState) => {
    return {
      userDetail: state.user.userDetail,
    };
  });

  // just if the user detail changes, the privileges should be reloaded
  const privileges = useMemo(() => {
    return removeDuplicates(
      userDetail?.user.roles.flatMap((role) => role.privileges)
    );
  }, [userDetail]);

  // just if the user detail changes, the confirmation token should be reloaded
  const confirmationToken = useMemo(() => {
    return userDetail?.user.confirmationToken;
  }, [userDetail]);

  // just if the user detail changes, the teams should be reloaded (where the user is a part of it)
  const teams = useMemo(() => {
    return userDetail?.teams;
  }, [userDetail]);

  // just if the user detail changes, the competition should be reloaded (where the user is an admin)
  const competitionsAdmin = useMemo(() => {
    return userDetail?.competitionsWhereAdmin;
  }, [userDetail]);

  // just if the user detail changes, the competition should be reloaded (where the user is a player)
  const competitionsPlayer = useMemo(() => {
    return userDetail?.competitionsWherePlayer;
  }, [userDetail]);

  // if there is an id in the url, load the user details from the api
  useEffect(() => {
    if (userId) {
      // load the details for the given id, add the user to the local fetched users (if not already stored) and also add the user details
      dispatch(
        getRequestWithID(+userId, userRoute, [addUser, storeUserDetail])
      );
    }

    // if the page is unmount, remove the user detail
    return () => {
      dispatch(removeUserDetail());
    };

    // Reload if the id was changed
  }, [userId, dispatch]);

  return (
    <>
      {userDetail && (
        <>
          <UserDetailsTable user={userDetail.user} />
          {privileges && (
            <TableSection>
              <h3>Privileges</h3>
              <EnumTable enumData={privileges} wrapped />
            </TableSection>
          )}
          {confirmationToken && (
            <TableSection>
              <h3>Confirmation Token</h3>
              <ConfirmationTokenTable
                userId={userDetail.user.id}
                confirmationToken={confirmationToken}
                wrapped
              />
            </TableSection>
          )}
          {teams && (
            <TableSection>
              <h3>Teams</h3>
              <TeamTable
                teams={teams.map((team) => {
                  const additionalAttributes = [
                    {
                      id: team.id + "_admin",
                      value: String(team.isAdmin),
                    },
                    {
                      id: team.id + "_creationTime",
                      value: String(team.creationTime),
                    },
                  ];

                  const newTeam = {
                    ...team.team,
                    additionalAttributes: additionalAttributes,
                  };

                  return newTeam;
                })}
                additionalAttributesHeader={["Admin", "Eingetreten"]}
                wrapped
              />
            </TableSection>
          )}
          {competitionsAdmin && (
            <TableSection>
              <h3>Competitions (as Admin)</h3>
              <CompetitionTable
                competitions={competitionsAdmin.map((competition) => {
                  const competitionAdmin = competition.competitionAdmins.filter(
                    (competitionAdmin) => {
                      return competitionAdmin.user.id === userDetail.user.id;
                    }
                  )[0];

                  const competitionStatus =
                    competitionAdmin.competitionAdminStatus.filter(
                      (singleCompetitionAdminStatus) => {
                        return singleCompetitionAdminStatus.validTo === null;
                      }
                    )[0];

                  const additionalAttributes = [
                    {
                      id: competition.id + "_administratorStatus",
                      value:
                        competitionStatus.competitionAdminStatusDescription,
                    },
                  ];

                  const newCompetition = {
                    ...competition,
                    additionalAttributes: additionalAttributes,
                  };

                  return newCompetition;
                })}
                wrapped
                additionalAttributesHeader={["Admin Status"]}
              />
            </TableSection>
          )}
          {competitionsPlayer && (
            <TableSection>
              <h3>Competitions (as Player)</h3>
              <CompetitionTable
                competitions={competitionsPlayer.map((competition) => {
                  const competitionTeam = competition.competitionTeams.filter(
                    (competitionTeam) => {
                      return (
                        competitionTeam.competitionPlayer.filter(
                          (singleCompetitionPlayer) => {
                            return (
                              singleCompetitionPlayer.user.id ===
                              userDetail.user.id
                            );
                          }
                        ).length === 1
                      );
                    }
                  )[0];

                  const additionalAttributes = [
                    {
                      id: competition.id + "_mainTeam",
                      value:
                        competitionTeam.team && competitionTeam.team.teamName,
                    },
                    {
                      id: competition.id + "_competitionTeamName",
                      value: competitionTeam.competitionTeamName,
                    },
                  ];

                  const newCompetition = {
                    ...competition,
                    additionalAttributes: additionalAttributes,
                  };

                  return newCompetition;
                })}
                wrapped
                additionalAttributesHeader={[
                  "Registriert mit Hauptteam",
                  "Competition Team Name",
                ]}
              />
            </TableSection>
          )}
        </>
      )}
    </>
  );
};

export default UserDetails;
