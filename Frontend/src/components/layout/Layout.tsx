import { Col, Container, Nav, Row, NavDropdown } from 'react-bootstrap';
import Navbar from 'react-bootstrap/Navbar';
import { Button } from 'react-bootstrap';
import { Outlet } from 'react-router-dom';
import { Link } from 'react-router-dom';
import classes from './Layout.module.css';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../store/combine-store';
import { logout } from '../../store/authentication/authentication-store';
import { Role } from '../../types/enums/role';
import { Privilege } from '../../types/enums/privilege';

const Layout: React.FC = () => {

    const dispatch = useDispatch();

    const { loggedIn, privileges, roles } = useSelector((state: RootState) => {
        return {
            loggedIn: state.authentication.loggedIn,
            privileges: state.authentication.privileges,
            roles: state.authentication.roles
        };
    });

    const logoutHandler = () => {
        dispatch(logout());
    };

    let logButton;
    if (loggedIn) {
        logButton = <Button variant="outline-danger" onClick={logoutHandler}>Logout</Button>
    } else {
        logButton = <Nav.Link as={Link} to="/authentication/login">
            <Button variant="outline-success">Login</Button>
        </Nav.Link>
    }

    return <>
        <Navbar expand="lg" bg="dark" variant="dark" sticky="top">
            <Container fluid>
                <Navbar.Brand>
                    <Nav.Link as={Link} to="/" style={{ color: 'white' }}>
                        BeerPong Competition App
                    </Nav.Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="responsive-navbar-nav" />
                <Navbar.Collapse id="responsive-navbar-nav">
                    <Nav className="me-auto my-2 my-lg-0">
                        {roles?.find(role => role.name === Role.ROLE_ADMINISTRATOR) && <NavDropdown title="Admin Bereich" id="navbarScrollingDropdown" menuVariant="dark">
                            {privileges?.find(privilege => privilege.name === Privilege.ADMIN_TEAM_PRIVILEGE) &&
                                <NavDropdown.Item as={Link} to="/team">
                                    <Nav.Link as="div">Teams</Nav.Link>
                                </NavDropdown.Item>
                            }
                            {privileges?.find(privilege => privilege.name === Privilege.ADMIN_AUTHENTICATION_PRIVILEGE) &&
                                <NavDropdown.Item as={Link} to="/user">
                                    <Nav.Link as="div">Users</Nav.Link>
                                </NavDropdown.Item>
                            }
                        </NavDropdown>}
                    </Nav>
                    {logButton}
                </Navbar.Collapse>
            </Container>
        </Navbar>
        <Container>
            <Row>
                <Col className={classes.main} md={{ span: 12, offset: 0.5 }}>
                    <Outlet />
                </Col>
            </Row>
        </Container>
    </>

};

export default Layout;