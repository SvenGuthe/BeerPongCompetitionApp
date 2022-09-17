import { Form, Button } from "react-bootstrap";
import { SyntheticEvent, useEffect, useState } from "react";
import classes from "./Register.module.css";
import { useDispatch, useSelector } from "react-redux";
import { sendRegisterRequest } from "../../store/authentication/authentication-store-actions";
import { RootState } from "../../store/combine-store";
import { useNavigate } from "react-router-dom";
import { afterRegisterCleanup } from "../../store/authentication/authentication-store";
import tUserRegistration from "../../types/user/userRegistration";

/**
 * Component to display the registration page
 * @returns JSX with the form to register
 */
const Register: React.FC = () => {
  const [registerData, setRegisterData] = useState<tUserRegistration>();
  const dispatch = useDispatch();
  const navigate = useNavigate();

  // get the information if the application should redirect to the "wait for confirmation" page
  // this is the case, if the registration was send successfully
  const redirection = useSelector((state: RootState) => {
    return state.authentication.redirectToConfirmWait;
  });

  // if redirection is true, than navigate to the "wait for confirmation" page
  // reset the registration data and cleanup the redirectToConfirmWait state
  useEffect(() => {
    if (redirection) {
      navigate("/confirm/wait");
      dispatch(afterRegisterCleanup());
    }
  }, [redirection, navigate, dispatch]);

  // if the registration data changed, than send a POST request to the registration route
  // after that navigate to the "wait for confirmation" page if this was successfully
  useEffect(() => {
    if (registerData) {
      dispatch(sendRegisterRequest(registerData));
    }
  }, [registerData, dispatch]);

  // handler if the register-button was clicked
  const submitHandler = (event: SyntheticEvent): void => {
    event.preventDefault();

    const target = event.target as typeof event.target & {
      firstName: { value: string };
      lastName: { value: string };
      gamerTag: { value: string };
      email: { value: string };
      password: { value: string };
    };

    const registerData: tUserRegistration = {
      firstName: target.firstName.value,
      lastName: target.lastName.value,
      gamerTag: target.gamerTag.value,
      email: target.email.value,
      password: target.password.value,
    };

    // send the registeration data from the targets
    setRegisterData(registerData);
  };

  const formClass = `mb-3 ${classes.form}`;

  return (
    <Form className={formClass} onSubmit={submitHandler}>
      <Form.Group className="mb-3" controlId="formFirstName">
        <Form.Label>Vorname</Form.Label>
        <Form.Control
          required
          type="text"
          placeholder="Enter firstname"
          name="firstName"
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formLastName">
        <Form.Label>Nachname</Form.Label>
        <Form.Control
          required
          type="text"
          placeholder="Enter lastname"
          name="lastName"
        />
      </Form.Group>
      <Form.Group className="mb-3" controlId="formGamerTag">
        <Form.Label>Spielername</Form.Label>
        <Form.Control
          required
          type="text"
          placeholder="Enter gamertag"
          name="gamerTag"
        />
      </Form.Group>
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
        Register
      </Button>
    </Form>
  );
};

export default Register;
