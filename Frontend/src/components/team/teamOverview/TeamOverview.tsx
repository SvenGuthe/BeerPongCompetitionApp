import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { removeTeams, storeTeams } from "../../../store/team/team-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import TeamTable from "./TeamTable";

const TeamOverview: React.FC = (props) => {

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
    
        return () => {
            dispatch(removeTeams());
        }
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    return teams ? <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={teams.size} pageSizes={pageSizes}>
        <TeamTable teams={teams.data} />
    </TableWithSearchAndFilter> : <></>;

};

export default TeamOverview;