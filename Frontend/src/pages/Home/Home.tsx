import AuthenticatedUser from "../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";

const Home = () => {

    const { authenticatedUser, roles, privileges } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser,
            roles: state.authentication.roles,
            privileges: state.authentication.privileges
        };
    });

    return <>
        <h2>Authenticated User</h2>
        {authenticatedUser && <AuthenticatedUser authenticatedUser={authenticatedUser} roles={roles!} privileges={privileges!} />}
    </>;
};

export default Home;