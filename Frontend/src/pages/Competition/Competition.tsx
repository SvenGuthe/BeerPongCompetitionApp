import CompetitionOverview from "../../components/competition/competitionOverview/CompetitionOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { competitionHierarchy, homeHierarchy } from "../../utility/hierarchy";

const Competition = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionHierarchy]} />
        <h2>Turniere</h2>
        <CompetitionOverview/>
    </>;
};

export default Competition;