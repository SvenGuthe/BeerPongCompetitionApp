import { Table } from "react-bootstrap";
import { tTeamStatus } from "../../../../types/team";
import TeamStatusAddRow from "./TeamStatusAddRow";
import TeamStatusRow from "./TeamStatusRow";

const TeamStatusTable: React.FC<{
    teamStatus: tTeamStatus[],
    wrapped?: boolean,
    additionalAttributesHeader?: string[]
}> = (props) => {

    const teamStatus = props.teamStatus;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th>ID</th>
                <th>Valide von</th>
                <th>Valide bis</th>
                <th>Status</th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {teamStatus.map(singleTeamStatus => <TeamStatusRow key={singleTeamStatus.id} teamStatus={singleTeamStatus} />)}
            <TeamStatusAddRow />
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

}

export default TeamStatusTable;