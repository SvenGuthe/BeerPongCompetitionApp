import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { storeUsers } from "../../../store/user/user-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import UserRow from "./UserRow";

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
        dispatch(getRequest(`/authentication/user?page=${filterValues.page}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, storeUsers));
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    let table;

    if (users) {
        table = <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={users.size} pageSizes={pageSizes}>
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Spielername</th>
                    <th>Freigeschalten</th>
                    <th>User Status</th>
                    <th style={{ textAlign: 'center' }}>Administrator</th>
                    <th style={{ textAlign: 'center' }}>Moderator</th>
                    <th style={{ textAlign: 'center' }}>Player</th>
                </tr>
            </thead>
            <tbody>
                {users.data.map(user => <UserRow key={user.id} user={user} />)}
            </tbody>
        </TableWithSearchAndFilter>
    }

    return <>
        {table}
    </>;

};

export default UserOverview;