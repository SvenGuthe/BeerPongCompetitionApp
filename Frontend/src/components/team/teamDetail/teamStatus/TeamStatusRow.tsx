import { tTeamStatus } from "../../../../types/team";

const TeamStatusRow: React.FC<{
    teamStatus: tTeamStatus
}> = (props) => {

    const teamStatus = props.teamStatus;

    return <tr>
        <td>{teamStatus.id}</td>
        <td>{teamStatus.validFrom}</td>
        <td>{teamStatus.validTo}</td>
        <td>{teamStatus.teamStatusDescription}</td>
    </tr>;

}

export default TeamStatusRow;