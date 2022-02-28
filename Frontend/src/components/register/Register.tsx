import { Form, Button } from "react-bootstrap";
import { SyntheticEvent, useEffect, useState } from "react";
import classes from './Register.module.css';
import { useDispatch, useSelector } from "react-redux";
import { sendRegisterRequest } from "../../store/user-store-actions";
import { tRegister } from "../../types/authenticate";
import { RootState } from "../../store/combine-store";
import { useNavigate } from "react-router-dom";
import { afterRegisterCleanup } from "../../store/user-store";

const Register: React.FC = () => {

    const [registerData, setRegisterData] = useState<tRegister>();
    const dispatch = useDispatch();
    const navigate = useNavigate();

    const redirection = useSelector((state: RootState) => {
        return state.user.redirectToConfirmWait;
    });

    useEffect(() => {
        if (redirection) {
            navigate('/confirm/wait');
            dispatch(afterRegisterCleanup());
        }
    }, [redirection, navigate, dispatch])

    useEffect(() => {
        if (registerData) {
            dispatch(sendRegisterRequest(registerData));
        }
    }, [registerData, dispatch])

    const submitHandler = (event: SyntheticEvent): void => {
        event.preventDefault();

        const target = event.target as typeof event.target & {
            firstName: { value: string };
            lastName: { value: string };
            gamerTag: { value: string };
            email: { value: string };
            password: { value: string };
        };

        const registerData: tRegister = {
            firstName: target.firstName.value,
            lastName: target.lastName.value,
            gamerTag: target.gamerTag.value,
            email: target.email.value,
            password: target.password.value
        }

        setRegisterData(registerData);
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