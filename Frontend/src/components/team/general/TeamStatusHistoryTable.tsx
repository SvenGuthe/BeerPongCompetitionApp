import { Table } from "react-bootstrap";
import { tTeamStatus } from "../../../types/team";

const TeamStatusHistoryTable: React.FC<{ teamStatus: tTeamStatus[] }> = (props) => {
    const teamStatus = props.teamStatus;

    const tableRows = teamStatus.map(singleTeamStatus => {
        return <tr key={singleTeamStatus.id}>
            <td>{singleTeamStatus.id}</td>
            <td>{singleTeamStatus.teamStatusDescription}</td>
            <td>{singleTeamStatus.validFrom}</td>
            <td>{singleTeamStatus.validTo}</td>
        </tr>
    })

    return <>
        <h4 style={{ marginTop: "1rem" }}>Teamstatus Historie</h4>
        <Table striped bordered hover size="sm">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Status</th>
                    <th>Valid Von</th>
                    <th>Valid Bis</th>
                </tr>
            </thead>
            <tbody>
                {tableRows}
            </tbody>
        </Table>
    </>;
};

export default TeamStatusHistoryTable;