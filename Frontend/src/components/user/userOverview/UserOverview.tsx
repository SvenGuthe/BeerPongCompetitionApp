import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { removeUsers, storeUsers } from "../../../store/user/user-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import { userRoute } from "../../../api-routes/user";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import UserTable from "./UserTable";

/**
 * Component to show the list of users (pagination)
 * @returns The choosen page and amount of users in a list
 */
const UserOverview: React.FC = () => {
  const dispatch = useDispatch();

  // Select all users from redux
  const { users } = useSelector((state: RootState) => {
    return {
      users: state.user.users,
    };
  });

  // Set the values for the pagination
  const pageSizes = [10, 20, 30];
  const [filterValues, setFilterValues] = useState({
    page: 0,
    size: pageSizes[0],
    search: "",
  });

  // Initialy load the first 10 users without a search filter
  useEffect(() => {
    dispatch(
      getRequest(
        `${userRoute}?page=${filterValues.page}&size=${
          filterValues.size
        }&search=${encodeURIComponent(filterValues.search)}`,
        [storeUsers]
      )
    );

    // Remove the feched users from redux
    return () => {
      dispatch(removeUsers());
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
      itemCount={users ? users.size : 0}
      pageSizes={pageSizes}
    >
      {users && <UserTable users={users.data} />}
    </TableWithSearchAndFilter>
  );
};

export default UserOverview;
