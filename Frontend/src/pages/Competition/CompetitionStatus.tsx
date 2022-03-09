import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { competitionStatusHierarchy, homeHierarchy } from "../../types/hierarchy";
import { competitionStatusRoute } from "../../api-routes/competition";
import { storeCompetitionStatus } from "../../store/competition/competition-store";

const CompetitionStatus = () => {

    const competitionStatus = useSelector((state: RootState) => {
        return state.competition.competitionStatus;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionStatusHierarchy]} />
        <h2>Turnier Status</h2>
        <EnumOverview url={competitionStatusRoute} storeFunction={storeCompetitionStatus} paginationData={competitionStatus} />
    </>

};

export default CompetitionStatus;