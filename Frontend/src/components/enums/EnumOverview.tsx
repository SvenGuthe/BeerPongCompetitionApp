import { ActionCreatorWithPayload } from "@reduxjs/toolkit";
import { useCallback, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { tEnum, tPaginationDTO } from "../../types/defaults/generics";
import { getRequest } from "../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../ui/TableWithSearchAndFilter";
import EnumTable from "./EnumTable";

const EnumOverview: React.FC<{
    url: string,
    storeFunction: ActionCreatorWithPayload<tPaginationDTO<tEnum>, string>,
    paginationData: tPaginationDTO<tEnum> | null
}> = (props) => {

    const dispatch = useDispatch();

    const { url, storeFunction, paginationData } = props;

    const pageSizes = [10, 20, 30];
    const [filterValues, setFilterValues] = useState({
        page: 0,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        dispatch(getRequest(`${url}?page=${filterValues.page}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, [storeFunction]));
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch, url, storeFunction]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    return paginationData ? <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={paginationData.size} pageSizes={pageSizes}>
        <EnumTable enumData={paginationData.data} />
    </TableWithSearchAndFilter> : <></>

};

export default EnumOverview;