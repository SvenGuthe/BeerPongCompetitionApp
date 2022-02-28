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
import { sendAuthenticatedUserRequest } from "./store/user-store-actions";
import { RootState } from "./store/combine-store";
import NotFound from "./pages/authentication/404/NotFound";

const App: React.FC = () => {
    const dispatch = useDispatch();
    const isLoggedIn = useSelector((state: RootState) => {
        return state.user.loggedIn;
    });
    const registeredUser = useSelector((state: RootState) => {
        return state.user.registeredUser;
    });
    const token = useSelector((state: RootState) => {
        return state.user.token;
    });

    useEffect(() => {

        if(isLoggedIn) {
            dispatch(sendAuthenticatedUserRequest(token))
        }

        dispatch(instantiation())
    }, [dispatch, isLoggedIn, token])

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
            <Route path="notfound" element={<NotFound />} />
            <Route path="*" element={<NotFound />} />
        </Route>
    </Routes>
}

export default App;