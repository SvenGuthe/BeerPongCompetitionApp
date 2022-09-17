import React, { useRef, useState } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addCompetitionTeam } from "../../../../store/competition/competition-store-actions";
import { removeDuplicates } from "../../../../utility/arrayFunctions";
import tCompetitionTeamAdd from "../../../../types/competition/competitionteam/competitionTeamAdd";
import tTeamAndUser from "../../../../types/team/teamAndUser";
import tUserIDAndGamerTag from "../../../../types/user/userIDAndGamerTag";

/**
 * Component to add a new competition team to a competition
 * @param props all possible teams to add to the competition
 *              all possible users to add to the competition (if single player)
 *              the competition id
 * @returns JSX to add a new competition team to a competition
 */
const CompetitionTeamAdd: React.FC<{
  teams: tTeamAndUser[];
  users: tUserIDAndGamerTag[];
  competitionId: number;
}> = (props) => {
  const [selectedTeam, setSelectedTeam] = useState<tTeamAndUser | null>(null);
  const [selectedUsers, setSelectedUsers] = useState<tUserIDAndGamerTag[]>([]);

  const competitionTeamNameRef = useRef<HTMLInputElement>(null);
  const competitionTeamPasswordRef = useRef<HTMLInputElement>(null);

  const dispatch = useDispatch();

  // get the teams from the props
  const teams = props.teams;

  // Handler when the "real" team was set (changed)
  const onSelectChangeHandler = (event: React.FormEvent<HTMLSelectElement>) => {
    // Get the choosen "real" team
    const value = event.currentTarget.value;

    // Find the choosen team by id
    const chosenTeam = teams.find((team) => team.team.id === +value);

    // If the team was found, then set the selected team
    // (This affected the possible users)
    if (chosenTeam) {
      setSelectedTeam(chosenTeam);
    } else {
      setSelectedTeam(null);
    }
  };

  // Handler when the choosen competition team users was set (changed)
  const onMultiSelectChangeHandler = (
    event: React.FormEvent<HTMLSelectElement>
  ) => {
    // The ids of the choosen users
    const value = event.currentTarget.selectedOptions;

    // All users
    const possibleUsersOption = removeDuplicates(
      teams.flatMap((team) => team.users)
    );

    // Find the users with the given id and add them to the selected values
    let selectedValues: tUserIDAndGamerTag[] = [];
    for (let i = 0; i < value.length; i++) {
      possibleUsersOption
        ?.filter((user) => {
          const item = value.item(i);

          if (item !== null) {
            return user.id === +item!.value;
          } else {
            return false;
          }
        })
        .forEach((user) => {
          selectedValues.push(user);
        });
    }
    setSelectedUsers(selectedValues);
  };

  // Handler when clicked the add button
  const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    // Create the DTO with the competition id and the selected users (optional team)
    const competitionTeam: tCompetitionTeamAdd = {
      id: props.competitionId,
      teamname: competitionTeamNameRef.current!.value,
      password: competitionTeamPasswordRef.current!.value,
      teamId: selectedTeam?.team.id,
      playerIds: selectedUsers.map((user) => user.id),
    };

    // Send a POST request to the competition team route to add a competition team to the competition
    dispatch(addCompetitionTeam(competitionTeam));

    // Reset the fields
    setSelectedTeam(null);
    setSelectedUsers([]);
  };

  let possibleTeams: tUserIDAndGamerTag[] = [];

  // If the "real" team was selected, the users have to be filtered
  // Then just users will be displayed, which are in the same team
  if (selectedTeam) {
    possibleTeams = selectedTeam.users.filter((user) =>
      props.users.find((singleUser) => singleUser.id === user.id)
    );
  } else {
    possibleTeams = props.users;
  }

  // The default value will be a competition team without a "real" team
  const defaultValue = {
    id: -1,
    teamName: "Ohne Hauptteam",
  };

  // Add all other teams to the possible team values
  const selectTeamDefaults = [defaultValue].concat(
    teams.map((team) => team.team)
  );

  const output = (
    <Container style={{ padding: "0px", margin: "0px" }}>
      <Form>
        <Row>
          <Form.Group as={Col} sm={2}>
            <Form.Label htmlFor="competitionTeamName">
              Turnier Team Name
            </Form.Label>
            <Form.Control
              id="competitionTeamName"
              type="text"
              placeholder="Enter Team Name"
              ref={competitionTeamNameRef}
            />
          </Form.Group>
          <Form.Group as={Col} sm={2}>
            <Form.Label htmlFor="competitionTeamPassword">Passwort</Form.Label>
            <Form.Control
              id="competitionTeamPassword"
              type="password"
              placeholder="Enter Password"
              ref={competitionTeamPasswordRef}
            />
          </Form.Group>
          <Form.Group as={Col} sm={4}>
            <Form.Label htmlFor="competitionMainTeam">
              Turnier Team Name
            </Form.Label>
            <Form.Select
              id="competitionMainTeam"
              onChange={onSelectChangeHandler}
            >
              {selectTeamDefaults.map((selectTeamDefault) => (
                <option key={selectTeamDefault.id} value={selectTeamDefault.id}>
                  {selectTeamDefault.teamName}
                </option>
              ))}
            </Form.Select>
          </Form.Group>
          <Form.Group as={Col} sm={2}>
            <Form.Label htmlFor="competitionTeamUser">Nutzer</Form.Label>
            <Form.Select
              id="competitionTeamUser"
              onChange={onMultiSelectChangeHandler}
              multiple
            >
              {possibleTeams.map((possibleTeam) => (
                <option key={possibleTeam.id} value={possibleTeam.id}>
                  {possibleTeam.gamerTag}
                </option>
              ))}
            </Form.Select>
          </Form.Group>
          <Col sm={2} style={{ textAlign: "right" }}>
            <Button
              variant="secondary"
              type="submit"
              size="sm"
              onClick={onAddHandler}
              style={{ width: "100px" }}
            >
              Add
            </Button>
          </Col>
        </Row>
      </Form>
    </Container>
  );

  return output;
};

export default CompetitionTeamAdd;
