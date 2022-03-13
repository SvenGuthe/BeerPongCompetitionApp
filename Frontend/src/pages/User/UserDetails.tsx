import Hierarchy from "../../components/ui/Hierarchy";
import { default as UserDetailsComponent } from "../../components/user/userDetail/UserDetail";
import { homeHierarchy, userHierarchy } from "../../utility/hierarchy";


const UserDetails = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, userHierarchy]} />
        <h2>Nutzer</h2>
        <UserDetailsComponent />
    </>;
};

export default UserDetails;