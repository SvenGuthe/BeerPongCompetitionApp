import Hierarchy from "../../components/ui/Hierarchy";
import {default as CompetitionComponent} from "../../components/competition/CompetitionOverview";
import { competitionHierarchy, homeHierarchy } from "../../types/hierarchy";

const Competition = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionHierarchy]} />
        <h2>Turniere</h2>
        <CompetitionComponent/>
    </>;
};

export default Competition;