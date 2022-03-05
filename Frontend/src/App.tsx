import { Route, Routes } from "react-router-dom";
import Layout from "./components/layout/Layout";
import Home from "./pages/Home/Home";
import Authentication from "./pages/authentication/Authentication";
import Login from "./pages/authentication/Login/Login";
import Register from "./pages/authentication/Register/Register";
import ConfirmWait from "./pages/authentication/Confirm/Confirm-Wait";
import ConfirmResult from "./pages/authentication/Confirm/Confirm-Result";
import { useDispatch, useSelector } from "react-redux";
import { useEffect } from "react";
import { instantiation, setAuthenticatedUser } from "./store/authentication/authentication-store";
import { checkToken } from "./store/authentication/authentication-store-actions";
import { RootState } from "./store/combine-store";
import NotFound from "./pages/authentication/404/NotFound";
import axios from "axios";
import { getRequest } from "./utility/genericHTTPFunctions";
import TeamDetails from "./pages/Team/TeamDetails";
import { Privilege } from "./types/enums/privilege";
import Team from "./pages/Team/Team";
import User from "./pages/User/User";
import UserDetails from "./pages/User/UserDetails";

const App: React.FC = () => {

    axios.defaults.baseURL = 'http://localhost:9999';

    const dispatch = useDispatch();
    const { loggedIn, registeredUser, token, privileges } = useSelector((state: RootState) => {
        return {
            loggedIn: state.authentication.loggedIn,
            registeredUser: state.authentication.registeredUser,
            token: state.authentication.token,
            privileges: state.authentication.privileges
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
    }, [dispatch, token])

    // If the loggedIn state is true and the token is set (which should be always the case) -> get the user information from the database
    useEffect(() => {
        if (loggedIn && token) {
            dispatch(getRequest("/authentication/authenticateduser", setAuthenticatedUser))
        }
    }, [dispatch, loggedIn, token])

    return <Routes>
        <Route element={<Layout />}>
            <Route path="/" element={<Home />} />
            <Route path="authentication">
                <Route index element={<Authentication />} />
                <Route path="login" element={<Login />} />
                <Route path="register" element={<Register />} />
            </Route>
            <Route path="confirm">
                {registeredUser && <Route path="wait" element={<ConfirmWait />} />}
                <Route path="result" element={<ConfirmResult />} />
            </Route>
            <Route path="team">
                {privileges?.find(privilege => privilege.name === Privilege.ADMIN_TEAM_PRIVILEGE) ?
                    <>
                        <Route index element={<Team />} />
                        <Route path=":id" element={<TeamDetails />} />
                    </>
                    :
                    <Route index element={<NotFound />} />}
            </Route>
            <Route path="user">
                {privileges?.find(privilege => privilege.name === Privilege.ADMIN_AUTHENTICATION_PRIVILEGE) ?
                    <>
                        <Route index element={<User />} />
                        <Route path=":id" element={<UserDetails />} />
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