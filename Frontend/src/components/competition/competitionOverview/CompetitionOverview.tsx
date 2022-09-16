import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import {
  removeCompetitions,
  storeCompetitions,
} from "../../../store/competition/competition-store";
import { tCompetition } from "../../../types/competition";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import CompetitionTable from "./CompetitionTable";

/**
 * Component to show the list of competitions (pagination)
 * @returns The choosen page and amount of competitions in a list
 */
const CompetitionOverview: React.FC = () => {
  const dispatch = useDispatch();

  // Select all competitions from redux
  const { competitions } = useSelector((state: RootState) => {
    return {
      competitions: state.competition.competitions,
    };
  });

  // Set the values for the pagination
  const pageSizes = [10, 20, 30];
  const [filterValues, setFilterValues] = useState({
    page: 0,
    size: pageSizes[0],
    search: "",
  });

  // Initialy load the first 10 competitions without a search filter
  useEffect(() => {
    dispatch(
      getRequest(
        `/competition/competition?page=${filterValues.page}&size=${
          filterValues.size
        }&search=${encodeURIComponent(filterValues.search)}`,
        [storeCompetitions]
      )
    );

    // Remove the feched competitions from redux
    return () => {
      dispatch(removeCompetitions());
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

  return (
    <TableWithSearchAndFilter
      changeFunction={changeFunction}
      itemCount={competitions ? competitions.size : 0}
      pageSizes={pageSizes}
    >
      {competitions && (
        <CompetitionTable
          competitions={[...competitions.data].sort(
            (a: tCompetition, b: tCompetition) => a.id - b.id
          )}
        />
      )}
    </TableWithSearchAndFilter>
  );
};

export default CompetitionOverview;
