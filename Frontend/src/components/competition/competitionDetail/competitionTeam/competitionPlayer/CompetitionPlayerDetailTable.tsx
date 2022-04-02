import { Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { updateCompetitionPlayerStatus } from "../../../../../store/competition/competition-store-actions";
import { tCompetitionPlayer, tCompetitionPlayerStatusUpdate } from "../../../../../types/competition";
import { tCompetitionPlayerStatusType } from "../../../../../types/enums/competitionPlayerStatusType";
import { CompetitionPlayerStatusTypeInput } from "../../../../ui/form/PredefinedSelectInputs";

const CompetitionPlayerDetailTable: React.FC<{
    competitionPlayerDetail: tCompetitionPlayer
}> = (props) => {

    const dispatch = useDispatch();

    const competitionPlayerDetail = props.competitionPlayerDetail;

    const onSaveHandler = (newValue: tCompetitionPlayerStatusType) => {
        const competitionPlayerStatus: tCompetitionPlayerStatusUpdate = {
            id: competitionPlayerDetail.id,
            competitionPlayerStatusType: newValue
        }
        dispatch(updateCompetitionPlayerStatus(competitionPlayerStatus));
    }

    return <>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <th>Competition Player ID</th>
                    <td>{competitionPlayerDetail.id}</td>
                </tr>
                <tr>
                    <th>User Name</th>
                    <td>{competitionPlayerDetail.user.gamerTag}</td>
                </tr>
                <tr>
                    <th>Hinzugefügt am</th>
                    <td>{competitionPlayerDetail.creationTime}</td>
                </tr>
                <tr>
                    <th>Status</th>
                    <td><CompetitionPlayerStatusTypeInput defaultValue={competitionPlayerDetail.competitionPlayerStatus.competitionPlayerStatusDescription} saveValue={(newValue, changed) => {
                        onSaveHandler((newValue as string[])[0] as tCompetitionPlayerStatusType)
                    }} /></td>
                </tr>
            </tbody>
        </Table>
    </>;

};

export default CompetitionPlayerDetailTable;