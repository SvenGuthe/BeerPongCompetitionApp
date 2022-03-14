import { Table } from "react-bootstrap";
import { tCompetition } from "../../../types/competition";
import FormItem from "../../ui/form/FormItem";

const CompetitionDetailTable: React.FC<{
    competition: tCompetition
}> = (props) => {

    const competition = props.competition;

    return <Table striped bordered hover size="sm">
        <tbody>
            <tr>
                <th>ID</th>
                <td>{competition.id}</td>
            </tr>
            <tr>
                <th>Name</th>
                <td><FormItem defaultValue={competition.competitionName} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Start Zeit</th>
                <td>{
                    /* This have to be replaced with an editable field */
                    <FormItem defaultValue={String(competition.competitionStartTimestamp)} saveValue={(newValue, changed) => console.log(newValue, changed)} />
                    }</td>
            </tr>
            <tr>
                <th>Erzeugt am</th>
                <td>{competition.creationTime}</td>
            </tr>
            <tr>
                <th>Gebühr</th>
                <td><FormItem defaultValue={competition.fee} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Min. Teams</th>
                <td><FormItem defaultValue={competition.minTeams} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Max. Teams</th>
                <td><FormItem defaultValue={competition.maxTeams} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Registrationsstart</th>
                <td><FormItem defaultValue={String(competition.registrationStart)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Registrationsende</th>
                <td><FormItem defaultValue={String(competition.registrationEnd)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
            <tr>
                <th>Regelwerk</th>
                <td><FormItem defaultValue={competition.setOfRules} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
            </tr>
        </tbody>
    </Table>

};

export default CompetitionDetailTable;