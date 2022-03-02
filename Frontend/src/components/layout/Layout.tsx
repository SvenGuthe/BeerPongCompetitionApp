import { Col, Container, Nav, NavItem, Row, NavDropdown } from 'react-bootstrap';
import Navbar from 'react-bootstrap/Navbar';
import { Button } from 'react-bootstrap';
import { Outlet } from 'react-router-dom';
import { Link } from 'react-router-dom';
import classes from './Layout.module.css';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../store/combine-store';
import { logout } from '../../store/user-store';
import { Role } from '../../types/enums/role';
import { Privilege } from '../../types/enums/privilege';

const Layout: React.FC = () => {

    const dispatch = useDispatch();

    const { loggedIn, privileges, roles } = useSelector((state: RootState) => {
        return {
            loggedIn: state.user.loggedIn,
            privileges: state.user.privileges,
            roles: state.user.roles
        };
    });

    const logoutHandler = () => {
        dispatch(logout());
    };

    let logButton;
    if (loggedIn === null) {

    } else if (loggedIn) {
        logButton = <Button variant="outline-danger" onClick={logoutHandler}>Logout</Button>
    } else {
        logButton = <Nav.Link as={Link} to="/authentication/login">
            <Button variant="outline-success">Login</Button>
        </Nav.Link>
    }

    return <>
        <Navbar expand="lg" bg="dark" variant="dark">
            <Container fluid>
                <Navbar.Brand>
                    <Nav.Link as={Link} to="/" style={{ color: 'white' }}>
                        BeerPong Competition App
                    </Nav.Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="navbarScroll" />
                <Navbar.Collapse id="navbarScroll">
                    <Nav className="me-auto">
                        {roles?.find(role => role.name === Role.ROLE_ADMINISTRATOR) && <NavDropdown title="Admin Bereich" id="collasible-nav-dropdown">
                            {privileges?.find(privilege => privilege.name === Privilege.ADMIN_TEAM_PRIVILEGE) &&
                                <NavDropdown.Item>
                                    <Nav.Link as={Link} to="/team">Teams</Nav.Link>
                                </NavDropdown.Item>
                            }
                        </NavDropdown>}
                    </Nav>
                    <Nav>
                        <NavItem>
                            {logButton}
                        </NavItem>
                    </Nav>
                </Navbar.Collapse>
            </Container>
        </Navbar>
        <Container>
            <Row>
                <Col className={classes.main} md={{ span: 9, offset: 1 }}>
                    <Outlet />
                </Col>
            </Row>
        </Container>
    </>

};

export default Layout;