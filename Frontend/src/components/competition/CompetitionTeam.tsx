import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetitionTeam } from "../../types/competition";
import CompetitionBillingTable from "./CompetitionBillingTable";
import CompetitionPlayerTable from "./CompetitionPlayerTable";
import CompetitionRegistrationTable from "./CompetitionRegistrationTable";

import classes from './CompetitionTeam.module.css';

const CompetitionTeam: React.FC<{ competitionTeam: tCompetitionTeam }> = (props) => {
    
    const competitionTeam = props.competitionTeam;
    const linkToDetails = `/team/${competitionTeam.team.id}`

    return <div className={classes.team}>
        <h5>{competitionTeam.competitionTeamName}</h5>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>ID</th>
                    <td>{competitionTeam.id}</td>
                </tr>
                <tr>
                    <th>Turnier Teamname</th>
                    <td>{competitionTeam.competitionTeamName}</td>
                </tr>
                <tr>
                    <th>Zugeordnetes Team</th>
                    <td>{competitionTeam.team.teamName}</td>
                </tr>
            </tbody>
        </Table>
        <Link to={linkToDetails}>
            <Button variant="secondary" size="sm">Details</Button>
        </Link>
        <CompetitionBillingTable competitionBillingStatus={competitionTeam.billingStatus} />
        <CompetitionPlayerTable competitionPlayers={competitionTeam.competitionPlayer} />
        <CompetitionRegistrationTable competitionRegistrationStatus={competitionTeam.registrationStatus} />
    </div>

}

export default CompetitionTeam;