import AuthenticatedUser from "../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";

const Home = () => {

    const { authenticatedUser } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser
        };
    });

    return <>
        <h2>Authenticated User</h2>
        {authenticatedUser && <AuthenticatedUser authenticatedUser={authenticatedUser} />}
    </>;
};

export default Home;