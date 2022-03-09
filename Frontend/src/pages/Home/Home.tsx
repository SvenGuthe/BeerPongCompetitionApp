import AuthenticatedUser from "../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy } from "../../types/hierarchy";

const Home = () => {

    const { authenticatedUser } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser
        };
    });

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy]} />
        <h2>Authenticated User</h2>
        {authenticatedUser && <AuthenticatedUser authenticatedUser={authenticatedUser} />}
    </>;
};

export default Home;