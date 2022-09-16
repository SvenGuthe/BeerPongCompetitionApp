import { Button } from "react-bootstrap";
import { tTeamInvitationLink } from "../../../../types/team";

/**
 * Component which defines a single row (a single team invitation link)
 * @param props the team invitation link DTO with all required information
 * @returns JSX with is a single row of the team invitation link table
 */
const TeamInvitationLinkRow: React.FC<{
  teamInvitationLink: tTeamInvitationLink;
}> = (props) => {
  // get the link from props
  const teamInvitationLink = props.teamInvitationLink;
  const additionalAttributes = teamInvitationLink.additionalAttributes
    ? teamInvitationLink.additionalAttributes
    : [];

  // TODO: Add the possiblity to change a team invitation link
  return (
    <tr>
      <td>{teamInvitationLink.id}</td>
      <td>{teamInvitationLink.validFrom}</td>
      <td>{teamInvitationLink.validTo}</td>
      <td>{teamInvitationLink.teamInvitationLink}</td>
      <td style={{ textAlign: "right" }}>
        <Button variant="secondary" size="sm" style={{ width: "100px" }}>
          {teamInvitationLink.validTo ? "Revalidate" : "Invalidate"}
        </Button>
      </td>
      {additionalAttributes.map((additionalAttribute) => {
        return additionalAttribute.reactElement ? (
          <td key={additionalAttribute.id}>
            {additionalAttribute.reactElement}
          </td>
        ) : (
          <td key={additionalAttribute.id}>{additionalAttribute.value}</td>
        );
      })}
    </tr>
  );
};

export default TeamInvitationLinkRow;
