import { useRef } from "react";
import { Button, Col, Row } from "react-bootstrap";
import { Container, Form } from "react-bootstrap";
import { tUserIDAndGamerTag } from "../../../../../types/authentication";
import { tTeamAndUser } from "../../../../../types/team";

const CompetitionPlayerAdd: React.FC<{
    team: tTeamAndUser | null,
    user: tUserIDAndGamerTag[],
    id: number
}> = (props) => {

    const team = props.team;
    const user = props.user;

    const selectRef = useRef<HTMLSelectElement>(null);

    const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        console.log(selectRef.current?.value);
    }

    let possibleUsers = user;

    if (team) {
        possibleUsers = team.users.filter(singleTeamUser => user.find(singleUser => singleUser.id === singleTeamUser.id))
    }

    return <Container style={{ padding: "0px", margin: "0px" }}>
        <Form>
            <Row>
                <Form.Group as={Col} sm={4}>
                    <Form.Label htmlFor={`teamuser_${props.id}`}>Teamspieler</Form.Label>
                    <Form.Select id={`teamuser_${props.id}`} ref={selectRef}>
                        {possibleUsers.map(user => <option key={user.id}>{user.gamerTag}</option>)}
                    </Form.Select>
                </Form.Group>
                <Col sm={2} style={{ textAlign: "right" }}>
                    <Button variant="secondary" type="submit" size="sm" onClick={onAddHandler} style={{ width: '100px' }}>
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    </Container>

};

export default CompetitionPlayerAdd;