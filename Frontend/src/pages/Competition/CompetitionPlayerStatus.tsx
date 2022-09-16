import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import {
  competitionPlayerStatusHierarchy,
  homeHierarchy,
} from "../../utility/hierarchy";
import { competitionPlayerStatusRoute } from "../../api-routes/competition";
import { storeCompetitionPlayerStatus } from "../../store/competition/competition-store";

const CompetitionPlayerStatus = () => {
  const competitionPlayerStatus = useSelector((state: RootState) => {
    return state.competition.competitionPlayerStatus;
  });

  return (
    <>
      <Hierarchy
        hierarchyItems={[homeHierarchy, competitionPlayerStatusHierarchy]}
      />
      <h2>Turnier Spieler Status</h2>
      <EnumOverview
        url={competitionPlayerStatusRoute}
        storeFunction={storeCompetitionPlayerStatus}
        paginationData={competitionPlayerStatus}
      />
    </>
  );
};

export default CompetitionPlayerStatus;
