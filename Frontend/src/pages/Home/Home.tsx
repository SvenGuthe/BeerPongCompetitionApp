import AuthenticatedUser from "../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy } from "../../types/hierarchy";
import TableWithSearchAndFilter from "../../components/ui/TableWithSearchAndFilter";
import {useCallback, useEffect, useState } from "react";

const Home = () => {

    const { authenticatedUser } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser
        };
    });

    const pageSizes = [20, 50, 100];
    const [filterValues, setFilterValues] = useState({
        page: 1,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        console.log("Page: " + filterValues.page + " With Size: " + filterValues.size + " Search: " + filterValues.search);
    }, [filterValues.page, filterValues.size, filterValues.search]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({page, size, search});
    }, []);

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy]} />
        <h2>Authenticated User</h2>
        {authenticatedUser && <AuthenticatedUser authenticatedUser={authenticatedUser} />}
        <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={100} pageSizes={pageSizes}>
            <thead>
                <tr>
                    <th>Test</th>
                    <th>Test2</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Test</td>
                    <td>Test2</td>
                </tr>
            </tbody>
        </TableWithSearchAndFilter>
    </>;
};

export default Home;