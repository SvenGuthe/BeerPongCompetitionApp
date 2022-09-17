import { Table } from "react-bootstrap";
import tTeamInvitationLink from "../../../../types/team/teaminvitationlink/teamInvitationLink";
import TeamInvitationLinkAddRow from "./TeamInvitationLinkAddRow";
import TeamInvitationLinkRow from "./TeamInvitationLinkRow";

/**
 * Component to show all team invitation links and add new ones for the team
 * @param props current available teamInvitationLinks from the database
 *              the id of the team
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 * @returns JSX to display all available team invitation links and add new ones
 */
const TeamInvitationLinkTable: React.FC<{
  teamInvitationLinks: tTeamInvitationLink[];
  id: number;
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
}> = (props) => {
  // get the team invitation links and sort them by id (so timestamp)
  const teamInvitationLinks = [...props.teamInvitationLinks].sort(
    (a: tTeamInvitationLink, b: tTeamInvitationLink) => a.id - b.id
  );

  const wrapped = props.wrapped ? props.wrapped : false;
  const additionalAttributesHeader = props.additionalAttributesHeader
    ? props.additionalAttributesHeader
    : [];

  const input = (
    <>
      <thead>
        <tr>
          <th>ID</th>
          <th>Valide von</th>
          <th>Valide bis</th>
          <th>Link</th>
          <th></th>
          {additionalAttributesHeader.map(
            (singleAdditionalAttributesHeader) => {
              return (
                <th key={singleAdditionalAttributesHeader}>
                  {singleAdditionalAttributesHeader}
                </th>
              );
            }
          )}
        </tr>
      </thead>
      <tbody>
        {teamInvitationLinks.map((teamInvitationLink) => (
          <TeamInvitationLinkRow
            key={teamInvitationLink.id}
            teamInvitationLink={teamInvitationLink}
          />
        ))}
        <TeamInvitationLinkAddRow id={props.id} />
      </tbody>
    </>
  );

  const wrappedInput = wrapped ? (
    <Table striped bordered hover size="sm">
      {input}
    </Table>
  ) : (
    input
  );

  return wrappedInput;
};

export default TeamInvitationLinkTable;
