import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { removeUsers, storeUsers } from "../../../store/user/user-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import UserTable from "./UserTable";

const UserOverview: React.FC = () => {

    const dispatch = useDispatch();

    const { users } = useSelector((state: RootState) => {
        return {
            users: state.user.users
        }
    });

    const pageSizes = [10, 20, 30];
    const [filterValues, setFilterValues] = useState({
        page: 0,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        dispatch(getRequest(`/authentication/user?page=${filterValues.page}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, [storeUsers]));
        
        return () => {
            dispatch(removeUsers())
        };
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    return <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={users ? users.size : 0} pageSizes={pageSizes}>
        {users && <UserTable users={users.data} />}
    </TableWithSearchAndFilter>;

};

export default UserOverview;