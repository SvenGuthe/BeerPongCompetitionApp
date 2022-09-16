import TeamDetail from "../../components/team/teamDetail/TeamDetail";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy, teamHierarchy } from "../../utility/hierarchy";

const TeamDetails = () => {
  return (
    <>
      <Hierarchy hierarchyItems={[homeHierarchy, teamHierarchy]} />
      <h2>Team</h2>
      <TeamDetail />
    </>
  );
};

export default TeamDetails;
