import { tEnum } from "../../types/defaults/generics";

/**
 * Component which includes a single row of the enum list
 * @param props the enum which should be displayed in the row
 * @returns JSX of the row with the important information about the enum
 */
const EnumRow: React.FC<{
  enum: tEnum;
}> = (props) => {
  // get the enum from the props
  const enumValue = props.enum;

  // if additional attributes are set, add them here to the table
  // mostly elements to edit values
  const additionalAttributes = enumValue.additionalAttributes
    ? enumValue.additionalAttributes
    : [];

  return (
    <tr>
      <td>{enumValue.id}</td>
      <td>{enumValue.value}</td>
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

export default EnumRow;
