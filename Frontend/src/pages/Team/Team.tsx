import TeamOverview from "../../components/team/teamOverview/TeamOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy, teamHierarchy } from "../../utility/hierarchy";

const Team = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, teamHierarchy]} />
        <h2>Teams</h2>
        <TeamOverview />
    </>;
};

export default Team;