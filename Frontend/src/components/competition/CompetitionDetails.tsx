import { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../store/combine-store";
import { addCompetition } from "../../store/competition/competition-store";
import { tCompetition } from "../../types/competition";
import { getRequestWithID } from "../../utility/genericHTTPFunctions";
import CompetitionAdminTable from "./CompetitionAdminTable";
import CompetitionStatusTable from "./CompetitionStatusTable";
import CompetitionTeam from "./CompetitionTeam";

const CompetitionDetails: React.FC = () => {

    const [selectedCompetition, setSelectedCompetition] = useState<tCompetition>();
    const dispatch = useDispatch();
    const id = useParams().id;

    const { competitions } = useSelector((state: RootState) => {
        return {
            competitions: state.competition.competitions
        };
    });

    useEffect(() => {

        if (id) {
            const competition = competitions?.find(competition => competition.id === +id);
            if (competition) {
                setSelectedCompetition(competition)
            } else {
                dispatch(getRequestWithID(+id, "/competition/competition", addCompetition));
            }
        }

    }, [id, dispatch, competitions]);

    return <>
        <h3>{selectedCompetition?.competitionName}</h3>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>{selectedCompetition?.id}</td>
                </tr>
                <tr>
                    <th>Turnier Name</th>
                    <td>{selectedCompetition?.competitionName}</td>
                </tr>
                <tr>
                    <th>Erstellt am</th>
                    <td>{selectedCompetition?.creationTime}</td>
                </tr>
                <tr>
                    <th>Beginnt am</th>
                    <td>{selectedCompetition?.competitionStartTimestamp}</td>
                </tr>
                <tr>
                    <th>Gebühr</th>
                    <td>{selectedCompetition?.fee.toFixed(2)} €</td>
                </tr>
                <tr>
                    <th>Max. Teams</th>
                    <td>{selectedCompetition?.maxTeams}</td>
                </tr>
                <tr>
                    <th>Min. Teams</th>
                    <td>{selectedCompetition?.minTeams}</td>
                </tr>
                <tr>
                    <th>Registration Start</th>
                    <td>{selectedCompetition?.registrationStart}</td>
                </tr>
                <tr>
                    <th>Registration Ende</th>
                    <td>{selectedCompetition?.registrationEnd}</td>
                </tr>
                <tr>
                    <th>Regelwerk</th>
                    <td>{selectedCompetition?.setOfRules}</td>
                </tr>
            </tbody>
        </Table>
        {selectedCompetition && <CompetitionAdminTable competitionAdmins={selectedCompetition.competitionAdmins} />}
        {selectedCompetition && <CompetitionStatusTable competitionStatus={selectedCompetition.competitionStatus} />}
        {selectedCompetition && <>
            <h4>Teams</h4>
            {selectedCompetition.competitionTeams.map(competitionTeam => <CompetitionTeam key={competitionTeam.id} competitionTeam={competitionTeam} />)}
        </>}
    </>;

};

export default CompetitionDetails;