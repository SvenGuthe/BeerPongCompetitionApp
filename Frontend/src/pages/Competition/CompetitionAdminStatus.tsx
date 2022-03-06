import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { competitionAdminStatusHierarchy, homeHierarchy } from "../../types/hierarchy";
import { competitionAdminStatusRoute } from "../../api-routes/competition";
import { storeCompetitionAdminStatus } from "../../store/competition/competition-store";

const CompetitionAdminStatus = () => {

    const competitionAdminStatus = useSelector((state: RootState) => {
        return state.competition.competitionAdminStatus;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, competitionAdminStatusHierarchy]} />
        <h2>Turnier Administrator Status</h2>
        <EnumOverview url={competitionAdminStatusRoute} storeFunction={storeCompetitionAdminStatus} data={competitionAdminStatus} />
    </>

};

export default CompetitionAdminStatus;