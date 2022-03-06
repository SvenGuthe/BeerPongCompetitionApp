import { Route, Routes } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Home from "./pages/Home/Home";
import Login from "./pages/authentication/Login/Login";
import Register from "./pages/authentication/Register/Register";
import ConfirmWait from "./pages/authentication/Confirm/Confirm-Wait";
import ConfirmResult from "./pages/authentication/Confirm/Confirm-Result";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { instantiation, setLoading } from "./store/authentication/authentication-store";
import { checkToken, sendAuthenticationRequest } from "./store/authentication/authentication-store-actions";
import { RootState } from "./store/combine-store";
import NotFound from "./pages/authentication/404/NotFound";
import axios from "axios";
import TeamDetails from "./pages/Team/TeamDetails";
import { Privilege as PrivilegeEnum } from "./types/enums/privilege";
import Team from "./pages/Team/Team";
import User from "./pages/User/User";
import UserDetails from "./pages/User/UserDetails";
import { removePriviligeDuplicates } from "./utility/arrayFunctions";
import Competition from "./pages/Competition/Competition";
import CompetitionDetails from "./pages/Competition/CompetitionDetails";
import UserStatus from "./pages/User/UserStatus";
import Role from "./pages/User/Role";
import Privilege from "./pages/User/Privilege";
import ACLClass from "./pages/Authorization/ACLClass";
import TeamStatus from "./pages/Team/TeamStatus";
import CompetitionStatus from "./pages/Competition/CompetitionStatus";
import CompetitionAdminStatus from "./pages/Competition/CompetitionAdminStatus";
import CompetitionPlayerStatus from "./pages/Competition/CompetitionPlayerStatus";
import RegistrationStatus from "./pages/Competition/RegistrationStatus";
import BillingStatus from "./pages/Competition/RegistrationStatus copy";

const App: React.FC = () => {

    axios.defaults.baseURL = 'http://localhost:9999';

    const dispatch = useDispatch();
    const { loggedIn, registeredUser, authenticatedUser, token } = useSelector((state: RootState) => {
        return {
            loggedIn: state.authentication.loggedIn,
            registeredUser: state.authentication.registeredUser,
            authenticatedUser: state.authentication.authenticatedUser,
            token: state.authentication.token
        };
    });

    // Instantiation -> Check if token exists in local storage
    useEffect(() => {
        dispatch(instantiation());
    }, [dispatch]);

    // If there is a token -> Try to validate, if the token will be read from api
    useEffect(() => {
        if (token) {
            dispatch(checkToken());
        }
    }, [dispatch, token]);

    // If the loggedIn state is true and the token is set (which should be always the case) -> get the user information from the database
    useEffect(() => {
        if (loggedIn && token) {
            dispatch(setLoading(true));
            dispatch(sendAuthenticationRequest());
        }
    }, [dispatch, loggedIn, token]);

    const privileges = removePriviligeDuplicates(authenticatedUser?.roles.flatMap(role => role.privileges));
    
    return <Routes>
        <Route element={<Layout />}>
            <Route path="/" element={<Home />} />
            <Route path="authentication">
                <Route path="login" element={<Login />} />
                <Route path="register" element={<Register />} />
            </Route>
            <Route path="confirm">
                {registeredUser && <Route path="wait" element={<ConfirmWait />} />}
                <Route path="result" element={<ConfirmResult />} />
            </Route>
            <Route path="team">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_TEAM_PRIVILEGE) ?
                    <>
                        <Route index element={<Team />} />
                        <Route path=":id" element={<TeamDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="teamstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_TEAM_PRIVILEGE) ?
                    <>
                        <Route index element={<TeamStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="user">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<User />} />
                        <Route path=":id" element={<UserDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="userstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<UserStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="role">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<Role />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="privilege">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<Privilege />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="aclclass">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_ACL_PRIVILEGE) ?
                    <>
                        <Route index element={<ACLClass />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="competition">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<Competition />} />
                        <Route path=":id" element={<CompetitionDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="competitionstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="competitionadminstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionAdminStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="competitionplayerstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionPlayerStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="registrationstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<RegistrationStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="billingstatus">
                {privileges?.find(privilege => privilege.name === PrivilegeEnum.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<BillingStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="notfound" element={<NotFound />} />
            <Route path="*" element={<NotFound />} />
        </Route>
    </Routes>
}

export default App;