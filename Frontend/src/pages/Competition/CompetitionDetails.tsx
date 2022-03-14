import CompetitionDetail from "../../components/competition/competitionDetail/CompetitionDetail";
import Hierarchy from "../../components/ui/Hierarchy";
import { competitionHierarchy, homeHierarchy } from "../../utility/hierarchy";

const CompetitionDetails = () => {

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionHierarchy]} />
        <h2>Turnier</h2>
        <CompetitionDetail />
    </>;

};

export default CompetitionDetails;