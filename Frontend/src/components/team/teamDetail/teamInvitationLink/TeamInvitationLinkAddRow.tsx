import { useRef } from "react";
import { useDispatch } from "react-redux";
import { addTeamInvitationLink } from "../../../../store/team/team-store-actions";
import { tTeamInvitationLinkAdd } from "../../../../types/team";
import FormItem from "../../../ui/form/FormItem";

/**
 * Component to add a row to an existing table with an input field to add a new invitation link
 * @param props the id of the team
 * @returns JSX with a single table row (5 columns) and the possibility (input) to add a new custom invitation link
 */
const TeamInvitationLinkAddRow: React.FC<{
  id: number;
}> = (props) => {
  // get the id of the team from props
  const id = props.id;
  const dispatch = useDispatch();

  const teamInvitationLinkRef = useRef<HTMLInputElement>(null);

  // Handler when clicked the save button
  const onSaveNewInvitationLink = (newValue: string) => {
    // Create the DTO with the team id and the team invitation link
    const teamInvitationLink: tTeamInvitationLinkAdd = {
      id: id,
      teamInvitationLink: newValue,
    };

    // Send a POST request to the team invitation link route to add a new one
    dispatch(addTeamInvitationLink(teamInvitationLink));
    teamInvitationLinkRef.current!.value = "";
  };

  return (
    <tr style={{ borderTop: "2px dashed black" }}>
      <td colSpan={3} style={{ textAlign: "right" }}>
        Neuen Einladungslink Erstellen:
      </td>
      <td colSpan={2}>
        <FormItem
          ref={teamInvitationLinkRef}
          defaultValue=""
          saveValue={(newValue, changed) =>
            onSaveNewInvitationLink(newValue as string)
          }
        />
      </td>
    </tr>
  );
};

export default TeamInvitationLinkAddRow;
