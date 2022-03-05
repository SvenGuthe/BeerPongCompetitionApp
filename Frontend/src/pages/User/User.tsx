import Hierarchy from "../../components/ui/Hierarchy";
import {default as UserComponent} from "../../components/user/userDetail/UserOverview";
import { homeHierarchy, userHierarchy } from "../../types/hierarchy";

const User = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, userHierarchy]} />
        <h2>Users</h2>
        <UserComponent/>
    </>;
};

export default User;