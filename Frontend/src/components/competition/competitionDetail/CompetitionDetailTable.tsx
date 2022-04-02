import { useRef, useState } from "react";
import { Button, Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateCompetition } from "../../../store/competition/competition-store-actions";
import { tCompetition, tCompetitionUpdate } from "../../../types/competition";
import FormItem from "../../ui/form/FormItem";

const CompetitionDetailTable: React.FC<{
    competition: tCompetition
}> = (props) => {

    const competition = props.competition;

    const dispatch = useDispatch();

    const [isChanged, setIsChanged] = useState<boolean>(false);

    const competitionNameRef = useRef<HTMLInputElement>(null);
    const competitionStartRef = useRef<HTMLInputElement>(null);
    const competitionFeeRef = useRef<HTMLInputElement>(null);
    const competitionMinTeamRef = useRef<HTMLInputElement>(null);
    const competitionMaxTeamRef = useRef<HTMLInputElement>(null);
    const registrationStartRef = useRef<HTMLInputElement>(null);
    const registrationEndRef = useRef<HTMLInputElement>(null);
    const setOfRulesRef = useRef<HTMLInputElement>(null);

    const onSaveHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        
        event.preventDefault();

        if (isChanged) {
            const competitionUpdate: tCompetitionUpdate = {
                id: competition.id,
                competitionName: competitionNameRef.current!.value,
                competitionStartTimestamp: competitionStartRef.current!.value,
                fee: +competitionFeeRef.current!.value,
                minTeams: +competitionMinTeamRef.current!.value,
                maxTeams: +competitionMaxTeamRef.current!.value,
                registrationStart: registrationStartRef.current!.value,
                registrationEnd: registrationEndRef.current!.value,
                setOfRules: setOfRulesRef.current!.value
            }

            dispatch(updateCompetition(competitionUpdate));
            setIsChanged(false);
        }

    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>{competition.id}</td>
                </tr>
                <tr>
                    <th>Name</th>
                    <td><FormItem ref={competitionNameRef} defaultValue={competition.competitionName} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Start Zeit</th>
                    <td><FormItem ref={competitionStartRef} defaultValue={String(competition.competitionStartTimestamp)} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Erzeugt am</th>
                    <td>{competition.creationTime}</td>
                </tr>
                <tr>
                    <th>Gebühr</th>
                    <td><FormItem ref={competitionFeeRef} defaultValue={competition.fee} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Min. Teams</th>
                    <td><FormItem ref={competitionMinTeamRef} defaultValue={competition.minTeams} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Max. Teams</th>
                    <td><FormItem ref={competitionMaxTeamRef} defaultValue={competition.maxTeams} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Registrationsstart</th>
                    <td><FormItem ref={registrationStartRef} defaultValue={String(competition.registrationStart)} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Registrationsende</th>
                    <td><FormItem ref={registrationEndRef} defaultValue={String(competition.registrationEnd)} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
                <tr>
                    <th>Regelwerk</th>
                    <td><FormItem ref={setOfRulesRef} defaultValue={competition.setOfRules} saveValue={(newValue, changed) => changed && setIsChanged(changed)} /></td>
                </tr>
            </tbody>
        </Table>
        <div style={{ textAlign: 'right' }}>
            <Button variant="secondary" size="sm" style={{ width: '200px' }} onClick={onSaveHandler}>Save Meta Data</Button>
        </div>
    </>

};

export default CompetitionDetailTable;