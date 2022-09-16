import { ActionCreatorWithPayload } from "@reduxjs/toolkit";
import { useCallback, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";
import { getRequest } from "../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../ui/TableWithSearchAndFilter";
import EnumTable from "./EnumTable";

/**
 * Component to show the list of different Enum Types (Pagination)
 * @param props url which have to be used to get the right enum type from the backend
 *              storeFunction to forward it to the dispatch function to store the fetched data to redux
 *              paginationData which should be displayed in the table
 * @returns JSX with the table of all enum datatypes and the possiblity to change the filter and the pagination
 */
const EnumOverview: React.FC<{
  url: string;
  storeFunction: ActionCreatorWithPayload<tPaginationDTO<tEnum>, string>;
  paginationData: tPaginationDTO<tEnum> | null;
}> = (props) => {
  const dispatch = useDispatch();

  const { url, storeFunction, paginationData } = props;

  const pageSizes = [10, 20, 30];

  // Set the filter / search / pagination values to get the specific page in the specific size with the specific filter
  const [filterValues, setFilterValues] = useState({
    page: 0,
    size: pageSizes[0],
    search: "",
  });

  // if the filter values changed, than data should be load again
  useEffect(() => {
    dispatch(
      getRequest(
        `${url}?page=${filterValues.page}&size=${
          filterValues.size
        }&search=${encodeURIComponent(filterValues.search)}`,
        [storeFunction]
      )
    );
  }, [
    filterValues.page,
    filterValues.size,
    filterValues.search,
    dispatch,
    url,
    storeFunction,
  ]);

  // function to change the local state -> This function will be passed to the TableWithSearchAndFilter Component
  const changeFunction = useCallback(
    (page: number, size: number, search: string) => {
      setFilterValues({ page, size, search });
    },
    []
  );

  return paginationData ? (
    <TableWithSearchAndFilter
      changeFunction={changeFunction}
      itemCount={paginationData.size}
      pageSizes={pageSizes}
    >
      <EnumTable enumData={paginationData.data} />
    </TableWithSearchAndFilter>
  ) : (
    <></>
  );
};

export default EnumOverview;
