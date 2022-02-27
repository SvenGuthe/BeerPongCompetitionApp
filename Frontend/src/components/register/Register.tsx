import { Form, Button } from "react-bootstrap";
import { SyntheticEvent } from "react";
import classes from './Register.module.css';

const Register = () => {

    const submitHandler = (event: SyntheticEvent): void => {

    }

    const formClass = `mb-3 ${classes.form}`

    return <Form className={formClass} onSubmit={submitHandler}>
            <Form.Group className="mb-3" controlId="formFirstName">
                <Form.Label>Vorname</Form.Label>
                <Form.Control required type="text" placeholder="Enter firstname" name="firstName" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formLastName">
                <Form.Label>Nachname</Form.Label>
                <Form.Control required type="text" placeholder="Enter lastname" name="lastName" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formGamerTag">
                <Form.Label>Spielername</Form.Label>
                <Form.Control required type="text" placeholder="Enter gamertag" name="gamerTag" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
                <Form.Label>E-Mail</Form.Label>
                <Form.Control required type="email" placeholder="Enter email" name="email" />
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicPassword">
                <Form.Label>Passwort</Form.Label>
                <Form.Control required type="password" placeholder="Enter password" name="password" />
            </Form.Group>
            <Button variant="primary" type="submit">
                Register
            </Button>
        </Form>;
};

export default Register;