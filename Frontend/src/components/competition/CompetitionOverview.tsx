import { useEffect } from "react";
import { Table } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import { storeCompetitions } from "../../store/competition/competition-store";
import { getRequest } from "../../utility/genericHTTPFunctions";
import CompetitionRow from "./CompetitionRow";

const CompetitionOverview: React.FC = () => {

    const dispatch = useDispatch();

    const { authenticatedUser, competitions } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser,
            competitions: state.competition.competitions
        }
    });

    useEffect(() => {
        if (authenticatedUser) {
            dispatch(getRequest("/competition/competition", storeCompetitions));
        }
    }, [dispatch, authenticatedUser])

    let table;

    if (competitions) {
        table = <Table striped bordered hover size="sm" style={{ marginBottom: '3rem' }}>
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
                {competitions.map(competition => <CompetitionRow key={competition.id} competition={competition} />)}
            </tbody>
        </Table>
    }

    return <>
        {table}
    </>;

};

export default CompetitionOverview;