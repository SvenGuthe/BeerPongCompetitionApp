import { Table } from "react-bootstrap";
import { tTeam } from "../../../types/team";
import TeamInvitationLinkHistoryTable from "../general/TeamInvitationLinkHistoryTable";
import TeamStatusHistoryTable from "../general/TeamStatusHistoryTable";
import TeamStateButton from "../TeamStateButton";

const RealTeamDetails: React.FC<{ team: tTeam }> = (props) => {
    const team = props.team;


    const buttons = TeamStateButton({ team });

    const status = team.teamStatus.filter(singleTeamStatus =>
        singleTeamStatus.validTo === null).map(singleTeamStatus =>
            singleTeamStatus.teamStatusDescription
        )[0]

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Team ID</th>
                    <td>{team.id}</td>
                </tr>
                <tr>
                    <th>Team Name</th>
                    <td>{team.teamName}</td>
                </tr>
                <tr>
                    <th>Erstellt am</th>
                    <td>{team.creationTime}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td>{status}</td>
                </tr>
            </tbody>
        </Table>
        {buttons}
        <TeamStatusHistoryTable teamStatus={team.teamStatus} />
        <TeamInvitationLinkHistoryTable teamInvitationLinks={team.teamInvitationLinks} />
    </>;
};

export default RealTeamDetails;