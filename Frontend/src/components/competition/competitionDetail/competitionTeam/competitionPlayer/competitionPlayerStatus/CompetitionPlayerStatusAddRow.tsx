import { useDispatch } from "react-redux";
import { updateCompetitionPlayerStatus } from "../../../../../../store/competition/competition-store-actions";
import tCompetitionPlayerStatusUpdate from "../../../../../../types/competition/competitionplayer/competitionPlayerStatusUpdate";
import { tCompetitionPlayerStatusType } from "../../../../../../types/enums/competitionPlayerStatusType";
import { CompetitionPlayerStatusTypeInput } from "../../../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new competition player status
 * @param props the id of the competition player
 * @returns JSX with a sngle table row (4 columns) and the possibility (select input) to choose a new competition player status for the competition player
 */
const CompetitionPlayerStatusAddRow: React.FC<{
  competitionPlayerId: number;
}> = (props) => {
  // get the competition player id from the props
  const competitionPlayerId = props.competitionPlayerId;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewCompetitionPlayerStatusType = (newValue: string[]) => {
    // Create the DTO with the competition player id and the selected competition player status
    const competitionPlayerStatus: tCompetitionPlayerStatusUpdate = {
      id: competitionPlayerId,
      competitionPlayerStatusType: newValue[0] as tCompetitionPlayerStatusType,
    };

    // Send a PUT request to the competition player status route to set the new competition player status
    dispatch(updateCompetitionPlayerStatus(competitionPlayerStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Turnier Spieler Status setzen:
      </td>
      <td colSpan={2}>
        <CompetitionPlayerStatusTypeInput
          saveValue={(newValue, changed) => {
            onSaveNewCompetitionPlayerStatusType(newValue as string[]);
          }}
          add
        />
      </td>
    </tr>
  );
};

export default CompetitionPlayerStatusAddRow;
