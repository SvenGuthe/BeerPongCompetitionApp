import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateCompetitionTeam } from "../../../../store/competition/competition-store-actions";
import {
  tCompetitionTeam,
  tCompetitionTeamUpdate,
} from "../../../../types/competition";
import { tTeamAndUser } from "../../../../types/team";
import FormItem from "../../../ui/form/FormItem";

/**
 * Component to show the meta data of a single competition team
 * @param props competition team which should be displayed
 *              possible teams if the "real" team should be changed for a competition team
 * @returns JSX with a table filled with meta data of a single competition team
 */
const CompetitionTeamDetailTable: React.FC<{
  competitionTeamDetail: tCompetitionTeam;
  teams: tTeamAndUser[];
}> = (props) => {
  // get the competition team and calculate the size amount of players in the competition team
  const competitionTeamDetail = props.competitionTeamDetail;
  const competitionPlayerSize = competitionTeamDetail.competitionPlayer.length;

  // calculate possible teams (all users of the current competition team have to be in the same "real" team)
  const possibileTeams = props.teams
    .filter((team) => {
      const checkAllTeamCompetitionPlayerInTeams =
        competitionTeamDetail.competitionPlayer.filter(
          (singleCompetitionPlayer) => {
            return team.users.find(
              (user) => user.id === singleCompetitionPlayer.user.id
            );
          }
        );
      return (
        checkAllTeamCompetitionPlayerInTeams.length === competitionPlayerSize
      );
    })
    .map((possibileTeam) => possibileTeam.team);

  const dispatch = useDispatch();

  const [isChanged, setIsChanged] = useState<boolean>(false);

  const competitionTeamNameRef = useRef<HTMLInputElement>(null);
  const competitionTeamMainTeamRef = useRef<HTMLSelectElement>(null);

  // Handler to change the meta data of the competition team
  const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    // just do something, if there was a change
    if (isChanged) {
      // get the team id of the choosen "real" team
      const teamId = props.teams.find(
        (team) =>
          team.team.teamName === competitionTeamMainTeamRef.current!.value
      )?.team.id;

      // create the DTO to change the competition team info
      const competitionTeam: tCompetitionTeamUpdate = {
        id: competitionTeamDetail.id,
        teamname: competitionTeamNameRef.current!.value,
        teamId: teamId,
      };

      // send a PUT request to the competition team route to update the meta data
      dispatch(updateCompetitionTeam(competitionTeam));

      // set the flag if something was changed back to false
      setIsChanged(false);
    }
  };

  return (
    <>
      <Table striped bordered hover size="sm">
        <tbody>
          <tr>
            <th>Competition Team ID</th>
            <td>{competitionTeamDetail.id}</td>
          </tr>
          <tr>
            <th>Name</th>
            <td>
              <FormItem
                ref={competitionTeamNameRef}
                defaultValue={competitionTeamDetail.competitionTeamName}
                saveValue={(newValue, changed) =>
                  changed && setIsChanged(changed)
                }
              />
            </td>
          </tr>
          <tr>
            <th>Hauptteam</th>
            <td>
              <FormItem
                ref={competitionTeamMainTeamRef}
                possibleValues={possibileTeams.map(
                  (possibileTeam) => possibileTeam.teamName
                )}
                defaultValue={
                  competitionTeamDetail.team && [
                    competitionTeamDetail.team.teamName,
                  ]
                }
                saveValue={(newValue, changed) =>
                  changed && setIsChanged(changed)
                }
              />
            </td>
          </tr>
          <tr>
            <th>Hinzugefügt am</th>
            <td>{competitionTeamDetail.creationTime}</td>
          </tr>
        </tbody>
      </Table>
      <div style={{ textAlign: "right" }}>
        <Button
          variant="secondary"
          size="sm"
          style={{ width: "200px" }}
          onClick={onSaveHandler}
        >
          Save Meta Data
        </Button>
      </div>
    </>
  );
};

export default CompetitionTeamDetailTable;
