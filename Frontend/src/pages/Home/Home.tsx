import UserDetails from "../../components/userdetails/UserDetails";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";

const Home = () => {

    const { authenticatedUser, roles, privileges } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.user.authenticatedUser,
            roles: state.user.roles,
            privileges: state.user.privileges
        };
    });

    return <>
        <h2>Authenticated User</h2>
        {authenticatedUser && <UserDetails authenticatedUser={authenticatedUser} roles={roles!} privileges={privileges!} />}
    </>;
};

export default Home;