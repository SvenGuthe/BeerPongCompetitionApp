import Hierarchy from "../../components/ui/Hierarchy";
import { competitionHierarchy, homeHierarchy } from "../../utility/hierarchy";
import { default as CompetitionDetailsComponent } from "../../components/competition/CompetitionDetails"

const CompetitionDetails = () => {

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionHierarchy]} />
        <h2>Turnier</h2>
        <CompetitionDetailsComponent />
    </>;

};

export default CompetitionDetails;