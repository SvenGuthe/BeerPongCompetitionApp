import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import {
  homeHierarchy,
  teamCompositionStatusHierarchy,
} from "../../utility/hierarchy";
import { teamCompositionStatusRoute } from "../../api-routes/team";
import { storeTeamCompositionStatus } from "../../store/team/team-store";

const TeamCompositionStatus = () => {
  const teamCompositionStatus = useSelector((state: RootState) => {
    return state.team.teamCompositionStatus;
  });

  return (
    <>
      <Hierarchy
        hierarchyItems={[homeHierarchy, teamCompositionStatusHierarchy]}
      />
      <h2>Team Status</h2>
      <EnumOverview
        url={teamCompositionStatusRoute}
        storeFunction={storeTeamCompositionStatus}
        paginationData={teamCompositionStatus}
      />
    </>
  );
};

export default TeamCompositionStatus;
