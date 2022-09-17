import { useRef } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addCompetitionAdmin } from "../../../../store/competition/competition-store-actions";
import tCompetitionAdminAdd from "../../../../types/competition/competitionadmin/competitionAdminAdd";
import tUserIDAndGamerTag from "../../../../types/user/userIDAndGamerTag";

/**
 * Component to add a new competition admin to the current competition
 * @param props users (possible admins) and competition id of the current competition
 * @returns JSX to select and add a new user (possible admin) to the competition
 */
const CompetitionAdminAdd: React.FC<{
  users: tUserIDAndGamerTag[];
  competitionId: number;
}> = (props) => {
  // get the possible users from props
  const users = props.users;

  const selectRef = useRef<HTMLSelectElement>(null);
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    event.preventDefault();

    // Create the DTO with the competition id and the selected user (user id)
    const competitionAdmin: tCompetitionAdminAdd = {
      id: props.competitionId,
      userId: +selectRef.current!.value,
    };
    // Send a POST request to the competition admin route to add a user to the competition admins
    dispatch(addCompetitionAdmin(competitionAdmin));
  };

  // JSX just constists of a container with a Select-Input (users) and an add button
  return (
    <Container style={{ padding: "0px", margin: "0px" }}>
      <Form>
        <Row>
          <Form.Group as={Col} sm={4}>
            <Form.Label htmlFor="user">Nutzer</Form.Label>
            <Form.Select id="user" ref={selectRef}>
              {users.map((user) => (
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

export default CompetitionAdminAdd;
