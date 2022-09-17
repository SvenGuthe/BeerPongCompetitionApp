import { useRef } from "react";
import { Button, Col, Row } from "react-bootstrap";
import { Container, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addCompetitionPlayer } from "../../../../../store/competition/competition-store-actions";
import tCompetitionPlayerAdd from "../../../../../types/competition/competitionplayer/competitionPlayerAdd";
import tTeamAndUser from "../../../../../types/team/teamAndUser";
import tUserIDAndGamerTag from "../../../../../types/user/userIDAndGamerTag";

/**
 * Component to add a new competition player to a team
 * @param props an optional team if the competition team is a real team
 *              all possible users which could be added to a competition team
 *              the competition team id
 * @returns JSX to add new players to a competition team
 */
const CompetitionPlayerAdd: React.FC<{
  team?: tTeamAndUser;
  user: tUserIDAndGamerTag[];
  competitionTeamId: number;
}> = (props) => {
  // get the team from props
  // the team is present, if the competition team is registered with a real team
  // in this case, just other members of this real team are allowed in the competition team
  const team = props.team;

  // get the user from props
  const user = props.user;

  const selectRef = useRef<HTMLSelectElement>(null);
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    const userId = selectRef.current?.value;

    if (userId !== null) {
      // Create the DTO with the competition team id and the selected user (user id)
      const competitionPlayer: tCompetitionPlayerAdd = {
        id: props.competitionTeamId,
        userId: +userId!,
      };

      // Send a POST request to the competition player route to add a user to the competition player
      dispatch(addCompetitionPlayer(competitionPlayer));
    }
  };

  let possibleUsers = user;

  // If a team is present, then filter out all possible users which are not in the team
  if (team) {
    possibleUsers = team.users.filter((singleTeamUser) =>
      user.find((singleUser) => singleUser.id === singleTeamUser.id)
    );
  }

  return (
    <Container style={{ padding: "0px", margin: "0px" }}>
      <Form>
        <Row>
          <Form.Group as={Col} sm={4}>
            <Form.Label htmlFor={`teamuser_${props.competitionTeamId}`}>
              Teamspieler
            </Form.Label>
            <Form.Select
              id={`teamuser_${props.competitionTeamId}`}
              ref={selectRef}
            >
              {possibleUsers.map((user) => (
                <option key={user.id} value={user.id}>
                  {user.gamerTag}
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
};

export default CompetitionPlayerAdd;
