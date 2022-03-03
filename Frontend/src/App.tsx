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
import { instantiation } from "./store/user-store";
import { checkToken, sendAuthenticatedUserRequest } from "./store/user-store-actions";
import { RootState } from "./store/combine-store";
import NotFound from "./pages/authentication/404/NotFound";
import { Privilege } from "./types/enums/privilege";
import Team from "./pages/Team/Team";
import TeamDetails from "./pages/Team/TeamDetails";
import axios from "axios";

const App: React.FC = () => {

    axios.defaults.baseURL = 'http://localhost:9999';

    const dispatch = useDispatch();
    const { loggedIn, registeredUser, token, privileges } = useSelector((state: RootState) => {
        return {
            loggedIn: state.user.loggedIn,
            registeredUser: state.user.registeredUser,
            token: state.user.token,
            privileges: state.user.privileges
        };
    });

    // Instantiation -> Check if token exists in local storage
    useEffect(() => {
        dispatch(instantiation());
    }, [dispatch]);

    // If there is a token -> Try to validate, if the token will be read from api
    useEffect(() => {
        if (token) {
            dispatch(checkToken(token));
        }
    }, [dispatch, token])

    // If the loggedIn state is true and the token is set (which should be always the case) -> get the user information from the database
    useEffect(() => {
        if (loggedIn && token) {
            dispatch(sendAuthenticatedUserRequest(token))
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
            <Route path="notfound" element={<NotFound />} />
            <Route path="*" element={<NotFound />} />
        </Route>
    </Routes>
}

export default App;