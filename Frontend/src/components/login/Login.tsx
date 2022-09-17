import { SyntheticEvent, useEffect, useState } from "react";
import { Form, Button } from "react-bootstrap";
import { Link, useNavigate } from "react-router-dom";
import classes from "./Login.module.css";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { sendLoginRequest } from "../../store/authentication/authentication-store-actions";
import { afterLoginCleanup } from "../../store/authentication/authentication-store";
import tJwtResponse from "../../types/authentication/jwtResponse";

/**
 * Component to display the login page
 * @returns JSX with the form to login and the possibility to navigate to the registration page
 */
const Login: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const [loginData, setLoginData] = useState<tJwtResponse | null>();

  // get the information if the application should redirect to home
  // this is the case, if the login was successful
  const redirection = useSelector((state: RootState) => {
    return state.authentication.redirectToHome;
  });

  // if redirection is true, than navigate to the home route
  // reset the login data and cleanup the redirectToHome state
  useEffect(() => {
    if (redirection) {
      navigate("/");
      setLoginData(null);
      dispatch(afterLoginCleanup());
    }
  }, [redirection, navigate, dispatch]);

  // if the login data changed, than send a POST request to the login route
  // after that the fetched jwt token should be stored and for every
  // other request the bearer token should be set in the header
  useEffect(() => {
    if (loginData) {
      dispatch(sendLoginRequest(loginData.email, loginData.password));
    }
  }, [loginData, dispatch]);

  // handler if the login-button was clicked
  const submitHandler = (event: SyntheticEvent): void => {
    event.preventDefault();

    const target = event.target as typeof event.target & {
      email: { value: string };
      password: { value: string };
    };

    const email = target.email.value;
    const password = target.password.value;

    // set the login data from the targets
    setLoginData({
      email,
      password,
    });
  };

  const registerClass = `mb-3 ${classes.register}`;
  const formClass = `mb-3 ${classes.form}`;

  return (
    <Form className={formClass} onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formBasicEmail">
        <Form.Label>E-Mail</Form.Label>
        <Form.Control
          required
          type="email"
          placeholder="Enter email"
          name="email"
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Passwort</Form.Label>
        <Form.Control
          required
          type="password"
          placeholder="Enter password"
          name="password"
        />
      </Form.Group>
      <Button variant="primary" type="submit">
        Login
      </Button>
      <Form.Group className={registerClass}>
        <Link to="/authentication/register">Register</Link>
      </Form.Group>
    </Form>
  );
};

export default Login;
