import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { TeamStatus } from "../../types/enums/teamStatus";
import { tTeamWithUsers } from "../../types/team";

const RealTeamTable: React.FC<{ 
    teams: tTeamWithUsers[],
    stateToButtons: (state: TeamStatus) => JSX.Element | undefined
}> = (props) => {

    const teams = props.teams;
    const stateToButtons = props.stateToButtons;

    return <Table striped bordered hover size="sm" style={{
        marginBottom: '3rem'
    }}>
        <thead>
            <tr>
                <th>ID</th>
                <th>Team Name</th>
                <th>Mitglieder</th>
                <th>Admins</th>
                <th>Status</th>
                <th></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            {teams.map(team => {

                const status = team.teamStatusHistories.filter(status => status.validTo === null)[0].teamStatusDescription;
                let button = stateToButtons(status);
                const linkToDetails = `${team.id}`

                return <tr key={team.id}>
                    <td>{team.id}</td>
                    <td>{team.teamName}</td>
                    <td>{team.members.length}</td>
                    <td>{team.members.filter(member => member.admin).length}</td>
                    <td>{status}</td>
                    <td>
                        <Link to={linkToDetails}>
                            <Button variant="secondary" size="sm">Details</Button>
                        </Link>
                    </td>
                    <td>{button}</td>
                </tr>
            })}
        </tbody>
    </Table>

}

export default RealTeamTable;