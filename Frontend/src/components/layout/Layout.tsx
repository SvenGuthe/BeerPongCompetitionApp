import { Col, Container, Nav, NavItem, Row } from 'react-bootstrap';
import Navbar from 'react-bootstrap/Navbar';
import { Button } from 'react-bootstrap';
import { Outlet } from 'react-router-dom';
import { Link } from 'react-router-dom';
import classes from './Layout.module.css';
import { useSelector, useDispatch } from 'react-redux';
import { RootState } from '../../store/combine-store';
import { logout } from '../../store/user-store';

const Layout: React.FC = () => {

    const dispatch = useDispatch();

    const isLoggedIn = useSelector((state: RootState) => {
        return state.user.loggedIn;
    });

    const logoutHandler = () => {
        dispatch(logout());
    };

    let logButton;
    if (isLoggedIn) {
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
                    <Nav className="me-auto my-2 my-lg-0" style={{ maxHeight: '100px' }} navbarScroll>
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