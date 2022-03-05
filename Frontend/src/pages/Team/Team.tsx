import {default as TeamComponent} from "../../components/team/TeamOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy, teamHierarchy } from "../../types/hierarchy";

const Team = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, teamHierarchy]} />
        <h2>Teams</h2>
        <TeamComponent />
    </>;
};

export default Team;