import { ActionCreatorWithPayload } from "@reduxjs/toolkit";
import { useCallback, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { tEnum } from "../../types/enum";
import { getRequest } from "../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../ui/TableWithSearchAndFilter";

const EnumOverview: React.FC<{
    url: string,
    storeFunction: ActionCreatorWithPayload<tEnum[], string>,
    data: tEnum[] | null
}> = (props) => {

    const dispatch = useDispatch();

    const { url, storeFunction, data } = props;

    const pageSizes = [10, 20, 30];
    const [filterValues, setFilterValues] = useState({
        page: 1,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        dispatch(getRequest(`${url}?page=${filterValues.page - 1}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, storeFunction));
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch, url, storeFunction]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    let table;

    if (data) {
        table = <>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Wert</th>
                </tr>
            </thead>
            <tbody>
                {data.map(singleData => {
                    return <tr key={singleData.id}>
                        <td>{singleData.id}</td>
                        <td>{singleData.value}</td>
                    </tr>;
                })}
            </tbody>
        </>;
    }

    // Refactore Datastructur, The size should be beside the items - also in the backend
    return data ? <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={data.length >= 1 ? data[0].size : 0} pageSizes={pageSizes}>
        {table}
    </TableWithSearchAndFilter> : <></>

};

export default EnumOverview;