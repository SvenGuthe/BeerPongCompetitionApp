import { Table } from "react-bootstrap";
import { tTeamStatusHistory } from "../../../types/team";

const TeamStatusHistoryTable: React.FC<{ teamStatusHistories: tTeamStatusHistory[] }> = (props) => {
    const teamStatusHistories = props.teamStatusHistories;

    const tableRows = teamStatusHistories.map(teamStatusHistory => {
        return <tr key={teamStatusHistory.id}>
            <td>{teamStatusHistory.id}</td>
            <td>{teamStatusHistory.teamStatusDescription}</td>
            <td>{teamStatusHistory.validFrom}</td>
            <td>{teamStatusHistory.validTo}</td>
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