import { useRef } from "react";
import { Button, Col, Container, Form, Row } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { addCompetitionAdmin } from "../../../../store/competition/competition-store-actions";
import { tUserIDAndGamerTag } from "../../../../types/authentication";
import { tCompetitionAdminAdd } from "../../../../types/competition";

const CompetitionAdminAdd: React.FC<{
    users: tUserIDAndGamerTag[],
    competitionId: number
}> = (props) => {

    const users = props.users;

    const selectRef = useRef<HTMLSelectElement>(null);
    const dispatch = useDispatch();

    const onAddHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        event.preventDefault();
        const competitionAdmin: tCompetitionAdminAdd = {
            id: props.competitionId,
            userId: +selectRef.current!.value
        }
        dispatch(addCompetitionAdmin(competitionAdmin))
    }

    return <Container style={{ padding: "0px", margin: "0px" }}>
        <Form>
            <Row>
                <Form.Group as={Col} sm={4}>
                    <Form.Label htmlFor="user">Nutzer</Form.Label>
                    <Form.Select id="user" ref={selectRef}>
                        {users.map(user => <option key={user.id} value={user.id}>{user.gamerTag}</option>)}
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

export default CompetitionAdminAdd;