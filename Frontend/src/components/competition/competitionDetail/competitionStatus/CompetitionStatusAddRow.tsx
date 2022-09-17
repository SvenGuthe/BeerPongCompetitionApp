import { useDispatch } from "react-redux";
import { changeCompetitionStatus } from "../../../../store/competition/competition-store-actions";
import tCompetitionStatusUpdate from "../../../../types/competition/competitionstatus/competitionStatusUpdate";
import { tCompetitionStatusType } from "../../../../types/enums/competitionStatusType";
import { CompetitionStatusTypeInput } from "../../../ui/form/PredefinedSelectInputs";

/**
 * Component to add a row to an existing table with a select field to choose a new competition status
 * @param props the id of the competition
 * @returns JSX with a single table row (4 columns) and the possibility (select input) to choose a new status for the competition
 */
const CompetitionStatusAddRow: React.FC<{
  id: number;
}> = (props) => {
  // get the competition id from the props
  const id = props.id;
  const dispatch = useDispatch();

  // Handler when clicked the add button
  const onSaveNewCompetitionStatusType = (newValue: string[]) => {
    // Create the DTO with the competition id and the selected competition status
    const competitionStatus: tCompetitionStatusUpdate = {
      id: id,
      competitionStatusType: newValue[0] as tCompetitionStatusType,
    };

    // Send a PUT request to the competition status route to set the new competition status
    dispatch(changeCompetitionStatus(competitionStatus));
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={2} style={{ textAlign: "right" }}>
        Neuen Team Status setzen:
      </td>
      <td colSpan={2}>
        <CompetitionStatusTypeInput
          saveValue={(newValue, changed) => {
            onSaveNewCompetitionStatusType(newValue as string[]);
          }}
          add
        />
      </td>
    </tr>
  );
};

export default CompetitionStatusAddRow;
