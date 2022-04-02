import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { removeCompetitions, storeCompetitions } from "../../../store/competition/competition-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../../ui/TableWithSearchAndFilter";
import CompetitionTable from "./CompetitionTable";

const CompetitionOverview: React.FC = (props) => {

    const dispatch = useDispatch();

    const { competitions } = useSelector((state: RootState) => {
        return {
            competitions: state.competition.competitions
        }
    });

    const pageSizes = [10, 20, 30];
    const [filterValues, setFilterValues] = useState({
        page: 0,
        size: pageSizes[0],
        search: ""
    })

    useEffect(() => {
        dispatch(getRequest(`/competition/competition?page=${filterValues.page}&size=${filterValues.size}&search=${encodeURIComponent(filterValues.search)}`, [storeCompetitions]));
    
        return () => {
            dispatch(removeCompetitions());
        }
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    return <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={competitions ? competitions.size : 0} pageSizes={pageSizes}>
        {competitions && <CompetitionTable competitions={competitions.data} />}
    </TableWithSearchAndFilter>;

};

export default CompetitionOverview;