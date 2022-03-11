import Hierarchy from "../../components/ui/Hierarchy";
import { default as UserDetailsComponent } from "../../components/user/userDetail/UserDetails";
import { homeHierarchy, userHierarchy } from "../../utility/hierarchy";


const UserDetails = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, userHierarchy]} />
        <h2>User</h2>
        <UserDetailsComponent />
    </>;
};

export default UserDetails;