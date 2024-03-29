import {
  Col,
  Container,
  Nav,
  Row,
  NavDropdown,
  Spinner,
} from "react-bootstrap";
import Navbar from "react-bootstrap/Navbar";
import { Button } from "react-bootstrap";
import { Outlet } from "react-router-dom";
import { Link } from "react-router-dom";
import classes from "./Layout.module.css";
import { useSelector, useDispatch } from "react-redux";
import { RootState } from "../../store/combine-store";
import { logout } from "../../store/authentication/authentication-store";
import { removeDuplicates } from "../../utility/arrayFunctions";
import {
  aclClassHierarchy,
  billingStatusHierarchy,
  competitionAdminStatusHierarchy,
  competitionHierarchy,
  competitionPlayerStatusHierarchy,
  competitionStatusHierarchy,
  privilegeHierarchy,
  registrationStatusHierarchy,
  roleHierarchy,
  teamCompositionStatusHierarchy,
  teamHierarchy,
  teamStatusHierarchy,
  userHierarchy,
  userStatusHierarchy,
} from "../../utility/hierarchy";
import { tSecurityRole } from "../../types/enums/securityRole";
import { tSecurityPrivilege } from "../../types/enums/securityPrivilege";

/**
 * Component which holds the default layout of the website
 * @returns JSX of the layout
 */
const Layout: React.FC = () => {
  const dispatch = useDispatch();

  // getting the currently authenticated user and the flag, if data is loaded (then just display a spinner)
  const { authenticatedUser, loading } = useSelector((state: RootState) => {
    return {
      authenticatedUser: state.authentication.authenticatedUser,
      loading: state.authentication.loadAuthentication,
    };
  });

  // logout handler to log out the current user
  const logoutHandler = () => {
    dispatch(logout());
  };

  // check if login or logout button should be displayed
  let logButton;
  if (authenticatedUser) {
    logButton = (
      <Button variant="outline-danger" onClick={logoutHandler}>
        Logout
      </Button>
    );
  } else {
    logButton = (
      <Nav.Link as={Link} to="/authentication/login">
        <Button variant="outline-success">Login</Button>
      </Nav.Link>
    );
  }

  // getting roles and privileges to display different navigation options
  const roles = authenticatedUser?.roles;
  const privileges = removeDuplicates(
    authenticatedUser?.roles.flatMap((role) => role.privileges)
  );

  return (
    <>
      <Navbar expand="lg" bg="dark" variant="dark" sticky="top">
        <Container fluid>
          <Navbar.Brand>
            <Nav.Link as={Link} to="/" style={{ color: "white" }}>
              BeerPong Competition App
            </Nav.Link>
          </Navbar.Brand>
          <Navbar.Toggle aria-controls="responsive-navbar-nav" />
          <Navbar.Collapse id="responsive-navbar-nav">
            <Nav className="me-auto my-2 my-lg-0">
              {roles?.find(
                (role) => role.role === tSecurityRole.ROLE_ADMINISTRATOR
              ) && (
                <NavDropdown
                  title="Admin Bereich"
                  id="navbarScrollingDropdown"
                  menuVariant="dark"
                >
                  {privileges?.find(
                    (privilege) =>
                      privilege.privilege ===
                      tSecurityPrivilege.ADMIN_TEAM_PRIVILEGE
                  ) && (
                    <>
                      <NavDropdown.Item as={Link} to={teamHierarchy.relative}>
                        <Nav.Link as="div">{teamHierarchy.label}</Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={teamStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {teamStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={teamCompositionStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {teamCompositionStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                    </>
                  )}
                  {privileges?.find(
                    (privilege) =>
                      privilege.privilege ===
                      tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE
                  ) && (
                    <>
                      <NavDropdown.Item as={Link} to={userHierarchy.relative}>
                        <Nav.Link as="div">{userHierarchy.label}</Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={userStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {userStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={roleHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">{roleHierarchy.label}</Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={privilegeHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">{privilegeHierarchy.label}</Nav.Link>
                      </NavDropdown.Item>
                    </>
                  )}
                  {privileges?.find(
                    (privilege) =>
                      privilege.privilege ===
                      tSecurityPrivilege.ADMIN_ACL_PRIVILEGE
                  ) && (
                    <NavDropdown.Item as={Link} to={aclClassHierarchy.relative}>
                      <Nav.Link as="div">{aclClassHierarchy.label}</Nav.Link>
                    </NavDropdown.Item>
                  )}
                  {privileges?.find(
                    (privilege) =>
                      privilege.privilege ===
                      tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE
                  ) && (
                    <>
                      <NavDropdown.Item
                        as={Link}
                        to={competitionHierarchy.relative}
                      >
                        <Nav.Link as="div">
                          {competitionHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={competitionStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {competitionStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={competitionAdminStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {competitionAdminStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={competitionPlayerStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {competitionPlayerStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={registrationStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {registrationStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                      <NavDropdown.Item
                        as={Link}
                        to={billingStatusHierarchy.relative}
                        className={classes.sub}
                      >
                        <Nav.Link as="div">
                          {billingStatusHierarchy.label}
                        </Nav.Link>
                      </NavDropdown.Item>
                    </>
                  )}
                </NavDropdown>
              )}
            </Nav>
            {logButton}
          </Navbar.Collapse>
        </Container>
      </Navbar>
      <Container>
        <Row>
          <Col className={classes.main} md={{ span: 12, offset: 0.5 }}>
            {loading ? (
              <div style={{ textAlign: "center" }}>
                <Spinner animation="border" />
              </div>
            ) : (
              <Outlet />
            )}
          </Col>
        </Row>
      </Container>
    </>
  );
};

export default Layout;
