import { ReactElement } from "react";
import { Table } from "react-bootstrap";
import { tEnum } from "../../types/defaults/generics";
import EnumRow from "./EnumRow";

/**
 * Component which displays the table of all enums
 * @param props all enum data
 *              check if a new table should be created or if it is embedded in an existing table
 *              additional attributes which should be added as columns
 *              optional row to add a new enum
 * @returns  JSX with the table of enums
 */
const EnumTable: React.FC<{
  enumData: tEnum[];
  wrapped?: boolean;
  additionalAttributesHeader?: string[];
  addRow?: ReactElement;
}> = (props) => {
  const enumData = props.enumData;
  const wrapped = props.wrapped ? props.wrapped : false;
  const additionalAttributesHeader = props.additionalAttributesHeader
    ? props.additionalAttributesHeader
    : [];

  const input = (
    <>
      <thead>
        <tr>
          <th>ID</th>
          <th>Value</th>
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
        {enumData.map((singleEnum) => (
          <EnumRow key={singleEnum.id} enum={singleEnum} />
        ))}
        {props.addRow}
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

export default EnumTable;
