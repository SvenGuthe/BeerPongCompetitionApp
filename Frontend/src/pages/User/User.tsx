import Hierarchy from "../../components/ui/Hierarchy";
import UserOverview from "../../components/user/userOverview/UserOverview";
import { homeHierarchy, userHierarchy } from "../../utility/hierarchy";

const User = () => {
  return (
    <>
      <Hierarchy hierarchyItems={[homeHierarchy, userHierarchy]} />
      <h2>Nutzer</h2>
      <UserOverview />
    </>
  );
};

export default User;
