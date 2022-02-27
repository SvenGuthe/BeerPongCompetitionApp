import UserDetails from "../../components/userdetails/UserDetails";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";

const Home = () => {

    const authenticatedUser = useSelector((state: RootState) => {
        return state.user.authenticatedUser;
    });

    return <>
        <h1>Authenticated User</h1>
        {authenticatedUser && <UserDetails authenticatedUser={authenticatedUser} />}
    </>;
};

export default Home;