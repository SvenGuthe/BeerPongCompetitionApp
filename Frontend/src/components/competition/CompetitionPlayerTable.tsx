import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
import { tCompetitionPlayer } from "../../types/competition";

const CompetitionPlayerTable: React.FC<{ competitionPlayers: tCompetitionPlayer[] }> = (props) => {

    const competitionPlayers = props.competitionPlayers;

    const rows = competitionPlayers.map(competitionPlayer => {
        const linkToDetails = `/user/${competitionPlayer.user.id}`
        return <tr key={competitionPlayer.id}>
            <td style={{ textAlign: 'center', width: '100px' }}>
                <Link to={linkToDetails}>
                    <Button variant="secondary" size="sm">Details</Button>
                </Link>
            </td>
            <td>{competitionPlayer.user.id}</td>
            <td>{competitionPlayer.user.gamerTag}</td>
            <td>{competitionPlayer.competitionPlayerStatus.competitionPlayerStatusDescription}</td>
        </tr>
    })

    return <>
        <h6 style={{marginTop: "1rem"}}>Spieler</h6>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th></th>
                    <th>User ID</th>
                    <th>Spielername</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                {rows}
            </tbody>
        </Table>
    </>;

};

export default CompetitionPlayerTable;