import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { removeTeams, storeTeams } from "../../../store/team/team-store";
import { tTeam } from "../../../types/team";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import TeamTable from "./TeamTable";

/**
 * Component to show the list of teams (pagination)
 * @returns The choosen page and amount of teams in a list
 */
const TeamOverview: React.FC = () => {
  const dispatch = useDispatch();

  // Select all teams from redux
  const { teams } = useSelector((state: RootState) => {
    return {
      teams: state.team.teams,
    };
  });

  // Set the values for the pagination
  const pageSizes = [10, 20, 30];
  const [filterValues, setFilterValues] = useState({
    page: 0,
    size: pageSizes[0],
    search: "",
  });

  // Initialy load the first 10 teams without a search filter
  useEffect(() => {
    dispatch(
      getRequest(
        `/team/team?page=${filterValues.page}&size=${
          filterValues.size
        }&search=${encodeURIComponent(filterValues.search)}`,
        [storeTeams]
      )
    );

    // Remove the feched teams from redux
    return () => {
      dispatch(removeTeams());
    };

    // Reload competitions if the values for the pagination changed
  }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

  // change function to pass it to the generic search and filter table
  const changeFunction = useCallback(
    (page: number, size: number, search: string) => {
      setFilterValues({ page, size, search });
    },
    []
  );

  return teams ? (
    <TableWithSearchAndFilter
      changeFunction={changeFunction}
      itemCount={teams.size}
      pageSizes={pageSizes}
    >
      <TeamTable
        teams={[...teams.data].sort((a: tTeam, b: tTeam) => a.id - b.id)}
      />
    </TableWithSearchAndFilter>
  ) : (
    <></>
  );
};

export default TeamOverview;
