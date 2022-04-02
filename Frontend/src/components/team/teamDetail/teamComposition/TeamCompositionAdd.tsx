import { useRef } from "react";
import { Button, Col, Row } from "react-bootstrap";
import { Container, Form } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addTeamComposition } from "../../../../store/team/team-store-actions";
import { tUser } from "../../../../types/authentication";
import { tTeamCompositionAdd } from "../../../../types/team";

const TeamCompositionAdd: React.FC<{
    id: number,
    users: tUser[]
}> = (props) => {

    let possibleUsers = props.users;

    const userRef = useRef<HTMLSelectElement>(null);
    const activatedRef = useRef<HTMLInputElement>(null);

    const dispatch = useDispatch();

    const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        const teamComposition: tTeamCompositionAdd = {
            id: props.id,
            userId: +userRef.current!.value,
            isAdmin: activatedRef.current!.checked
        };
        dispatch(addTeamComposition(teamComposition));
        activatedRef.current!.checked = false;
    }

    return <Container style={{ padding: "0px", margin: "0px" }}>
        <Form>
            <Row>
                <Form.Group as={Col} sm={3}>
                    <Form.Label htmlFor={`user_${props.id}`}>Spieler</Form.Label>
                    <Form.Select id={`user_${props.id}`} ref={userRef}>
                        {possibleUsers.map(user => <option key={user.id} value={user.id}>{user.gamerTag}</option>)}
                    </Form.Select>
                </Form.Group>
                <Form.Group as={Col} sm={2}>
                    <Form.Label htmlFor={`admin_${props.id}`}>Admin</Form.Label>
                    <Form.Check id={`admin_${props.id}`} ref={activatedRef} />
                </Form.Group>
                <Col sm={2} style={{ textAlign: "right" }}>
                    <Button variant="secondary" type="submit" size="sm" onClick={onAddHandler} style={{ width: '100px' }}>
                        Add
                    </Button>
                </Col>
            </Row>
        </Form>
    </Container>;

}

export default TeamCompositionAdd;