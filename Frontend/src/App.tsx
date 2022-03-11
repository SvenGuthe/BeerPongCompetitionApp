import { Route, Routes } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Home from "./pages/Home/Home";
import Login from "./pages/authentication/Login/Login";
import Register from "./pages/authentication/Register/Register";
import ConfirmWait from "./pages/authentication/Confirm/Confirm-Wait";
import ConfirmResult from "./pages/authentication/Confirm/Confirm-Result";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { setLoading } from "./store/authentication/authentication-store";
import { sendAuthenticationRequest } from "./store/authentication/authentication-store-actions";
import { RootState } from "./store/combine-store";
import NotFound from "./pages/authentication/404/NotFound";
import axios from "axios";
import TeamDetails from "./pages/Team/TeamDetails";
import Team from "./pages/Team/Team";
import User from "./pages/User/User";
import UserDetails from "./pages/User/UserDetails";
import { removeDuplicates } from "./utility/arrayFunctions";
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
import BillingStatus from "./pages/Competition/BillingStatus";
import { aclClassHierarchy, billingStatusHierarchy, competitionAdminStatusHierarchy, competitionHierarchy, competitionPlayerStatusHierarchy, competitionStatusHierarchy, confirmResultHierarchy, confirmWaitHierarchy, homeHierarchy, loginHierarchy, notFoundHierarchy, privilegeHierarchy, registerHierarchy, registrationStatusHierarchy, roleHierarchy, teamHierarchy, teamStatusHierarchy, userHierarchy, userStatusHierarchy } from "./utility/hierarchy";
import { tSecurityPrivilege } from "./types/enums/securityPrivilege";

const App: React.FC = () => {

    axios.defaults.baseURL = 'http://localhost:9999';

    const dispatch = useDispatch();
    const { registeredUser, authenticatedUser } = useSelector((state: RootState) => {
        return {
            registeredUser: state.authentication.registeredUser,
            authenticatedUser: state.authentication.authenticatedUser
        };
    });

    useEffect(() => {
        if (!authenticatedUser) {
            dispatch(setLoading(true));
            const token = localStorage.getItem('token');
            if (token) {
                axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
                dispatch(sendAuthenticationRequest())
            } else {
                axios.defaults.headers.common['Authorization'] = false;
                dispatch(setLoading(false));
            }
        }
    }, [authenticatedUser, dispatch]);

    const privileges = removeDuplicates(authenticatedUser?.roles.flatMap(role => role.privileges));

    return <Routes>
        <Route element={<Layout />}>
            <Route path={homeHierarchy.url} element={<Home />} />
            <Route path="authentication">
                <Route path={loginHierarchy.relativeLabel} element={<Login />} />
                <Route path={registerHierarchy.relativeLabel} element={<Register />} />
            </Route>
            <Route path="confirm">
                {registeredUser && <Route path={confirmWaitHierarchy.relativeLabel} element={<ConfirmWait />} />}
                <Route path={confirmResultHierarchy.relativeLabel} element={<ConfirmResult />} />
            </Route>
            <Route path={teamHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_TEAM_PRIVILEGE) ?
                    <>
                        <Route index element={<Team />} />
                        <Route path=":id" element={<TeamDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={teamStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_TEAM_PRIVILEGE) ?
                    <>
                        <Route index element={<TeamStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={userHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<User />} />
                        <Route path=":id" element={<UserDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={userStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<UserStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={roleHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<Role />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={privilegeHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<Privilege />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={aclClassHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_ACL_PRIVILEGE) ?
                    <>
                        <Route index element={<ACLClass />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={competitionHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<Competition />} />
                        <Route path=":id" element={<CompetitionDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={competitionStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={competitionAdminStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionAdminStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={competitionPlayerStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<CompetitionPlayerStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={registrationStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<RegistrationStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={billingStatusHierarchy.relativeLabel}>
                {privileges?.find(privilege => privilege.privilege === tSecurityPrivilege.ADMIN_COMPETITION_PRIVILEGE) ?
                    <>
                        <Route index element={<BillingStatus />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path={notFoundHierarchy.relativeLabel} element={<NotFound />} />
            <Route path="*" element={<NotFound />} />
        </Route>
    </Routes>
}

export default App;