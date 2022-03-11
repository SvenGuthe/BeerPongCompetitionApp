import { default as TeamDetailsComponent } from "../../components/team/TeamDetails";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy, teamHierarchy } from "../../utility/hierarchy";

const TeamDetails = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, teamHierarchy]} />
        <h2>Team</h2>
        <TeamDetailsComponent />
    </>;
};

export default TeamDetails;