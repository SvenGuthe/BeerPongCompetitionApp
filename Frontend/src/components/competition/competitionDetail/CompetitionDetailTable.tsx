import { useRef } from "react";
import { Button, Table } from "react-bootstrap";
import { tCompetition } from "../../../types/competition";
import FormItem from "../../ui/form/FormItem";

const CompetitionDetailTable: React.FC<{
    competition: tCompetition
}> = (props) => {

    const competition = props.competition;

    const competitionTeamRef = useRef<HTMLInputElement>(null);
    const competitionStartRef = useRef<HTMLInputElement>(null);
    const competitionFeeRef = useRef<HTMLInputElement>(null);
    const competitionMinTeamRef = useRef<HTMLInputElement>(null);
    const competitionMaxTeamRef = useRef<HTMLInputElement>(null);
    const registrationStartRef = useRef<HTMLInputElement>(null);
    const registrationEndRef = useRef<HTMLInputElement>(null);
    const setOfRulesRef = useRef<HTMLInputElement>(null);

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>{competition.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><FormItem ref={competitionTeamRef} defaultValue={competition.competitionName} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Start Zeit</th>
                    <td><FormItem ref={competitionStartRef} defaultValue={String(competition.competitionStartTimestamp)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Erzeugt am</th>
                    <td>{competition.creationTime}</td>
                </tr>
                <tr>
                    <th>Gebühr</th>
                    <td><FormItem ref={competitionFeeRef} defaultValue={competition.fee} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Min. Teams</th>
                    <td><FormItem ref={competitionMinTeamRef} defaultValue={competition.minTeams} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Max. Teams</th>
                    <td><FormItem ref={competitionMaxTeamRef} defaultValue={competition.maxTeams} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Registrationsstart</th>
                    <td><FormItem ref={registrationStartRef} defaultValue={String(competition.registrationStart)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Registrationsende</th>
                    <td><FormItem ref={registrationEndRef} defaultValue={String(competition.registrationEnd)} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
                <tr>
                    <th>Regelwerk</th>
                    <td><FormItem ref={setOfRulesRef} defaultValue={competition.setOfRules} saveValue={(newValue, changed) => console.log(newValue, changed)} /></td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }}>Save Meta Data</Button>
        </div>
    </>

};

export default CompetitionDetailTable;