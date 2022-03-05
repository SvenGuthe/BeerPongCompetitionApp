import { SyntheticEvent, useEffect, useState } from "react";
import { Form, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import classes from './Login.module.css';
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { sendLoginRequest } from "../../store/authentication/authentication-store-actions";
import { tLogin } from "../../types/authenticate";
import { afterLoginCleanup } from "../../store/authentication/authentication-store";

const Login: React.FC = () => {
    const dispatch = useDispatch();
    const navigate = useNavigate();
    const [loginData, setLoginData] = useState<tLogin | null>();

    const redirection = useSelector((state: RootState) => {
        return state.authentication.redirectToHome;
    });

    useEffect(() => {
        if (redirection) {
            navigate('/');
            setLoginData(null);
            dispatch(afterLoginCleanup());
        }
    }, [redirection, navigate, dispatch])

    useEffect(() => {
        if (loginData) {            
            dispatch(sendLoginRequest(
                loginData.email,
                loginData.password
            ));
        }
    }, [loginData, dispatch])

    const submitHandler = (event: SyntheticEvent): void => {
        event.preventDefault();

        const target = event.target as typeof event.target & {
            email: { value: string };
            password: { value: string };
        };

        const email = target.email.value;
        const password = target.password.value;

        setLoginData({
            email,
            password
        })
    }

    const registerClass = `mb-3 ${classes.register}`
    const formClass = `mb-3 ${classes.form}`

    return <Form className={formClass} onSubmit={submitHandler}>
        <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>E-Mail</Form.Label>
            <Form.Control required type="email" placeholder="Enter email" name="email" />
        </Form.Group>
        <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Passwort</Form.Label>
            <Form.Control required type="password" placeholder="Enter password" name="password" />
        </Form.Group>
        <Button variant="primary" type="submit">
            Login
        </Button>
        <Form.Group className={registerClass}>
            <Link to='/authentication/register'>
                Register
            </Link>
        </Form.Group>
    </Form>;
};

export default Login;