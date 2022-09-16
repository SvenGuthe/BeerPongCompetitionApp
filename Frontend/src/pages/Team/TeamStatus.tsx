import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { homeHierarchy, teamStatusHierarchy } from "../../utility/hierarchy";
import { teamStatusRoute } from "../../api-routes/team";
import { storeTeamStatus } from "../../store/team/team-store";

const TeamStatus = () => {
  const teamStatus = useSelector((state: RootState) => {
    return state.team.teamStatus;
  });

  return (
    <>
      <Hierarchy hierarchyItems={[homeHierarchy, teamStatusHierarchy]} />
      <h2>Team Status</h2>
      <EnumOverview
        url={teamStatusRoute}
        storeFunction={storeTeamStatus}
        paginationData={teamStatus}
      />
    </>
  );
};

export default TeamStatus;
