import { useCallback, useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { storeCompetitions } from "../../store/competition/competition-store";
import { getRequest } from "../../utility/genericHTTPFunctions";
import TableWithSearchAndFilter from "../ui/TableWithSearchAndFilter";
import CompetitionRow from "./CompetitionRow";

const CompetitionOverview: React.FC = () => {

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
    }, [filterValues.page, filterValues.size, filterValues.search, dispatch]);

    const changeFunction = useCallback((page: number, size: number, search: string) => {
        setFilterValues({ page, size, search });
    }, []);

    let table;

    if (competitions) {
        table = <TableWithSearchAndFilter changeFunction={changeFunction} itemCount={competitions.size} pageSizes={pageSizes}>
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Turniername</th>
                    <th>Turnierstart</th>
                    <th>Gebühr</th>
                    <th>Anzahl Teams</th>
                    <th>Min Teams</th>
                    <th>Max Teams</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {competitions.data.map(competition => <CompetitionRow key={competition.id} competition={competition} />)}
            </tbody>
        </TableWithSearchAndFilter>
    }

    return <>
        {table}
    </>;

};

export default CompetitionOverview;