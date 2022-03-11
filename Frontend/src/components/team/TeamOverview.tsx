import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import RealTeamTable from "./realteam/RealTeamTable";
import { storeTeams } from "../../store/team/team-store";
import { getRequest } from "../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../ui/TableWithSearchAndFilter";

const Team: React.FC = () => {

    const dispatch = useDispatch();

    const { teams } = useSelector((state: RootState) => {
        return {
            teams: state.team.teams
        }
    });

    const pageSizes = [10, 20, 30];
    const [filterValues, setFilterValues] = useState({
        page: 0,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        dispatch(getRequest(`/team/team?page=${filterValues.page}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, [storeTeams]));
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    return teams ? <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={teams.size} pageSizes={pageSizes}>
        <RealTeamTable teams={teams.data} />
    </TableWithSearchAndFilter> : <></>;
}

export default Team;