import { Table } from "react-bootstrap";
import { tCompetition } from "../../../types/competition";
import CompetitionRow from "./CompetitionRow";

const CompetitionTable: React.FC<{
    competitions: tCompetition[],
    wrapped?: boolean
    additionalAttributesHeader?: string[]
}> = (props) => {

    const competitions = props.competitions;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Name</th>
                <th>Start</th>
                <th>Min. Teams</th>
                <th>Max. Teams</th>
                <th>Status</th>
                <th>#Teams aktuell</th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {competitions.map(competition => <CompetitionRow key={competition.id} competition={competition} />)}
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

};

export default CompetitionTable;