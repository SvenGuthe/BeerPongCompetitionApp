import React, { RefObject } from "react";
import { Form } from "react-bootstrap";

/**
 * Props data structure to define
 *  selected values as default // TODO: Should be changed to a single value
 *  if the select input should be disabled
 *  possible values for the select input
 *  handler, when the choosen entry was changed
 */
interface Props {
  value?: string[];
  disabled: boolean;
  possibleValues: string[];
  onChangeHandler: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

/**
 * Component which returns the JSX of a select input
 */
const SelectInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {
  // If the value is defined, use the first entry as default value
  // If not then use a empty string
  const value = props.value ? props.value[0] : "";

  let possibleValues = props.possibleValues;

  // if there was no default value than add the empty string to the possible values
  if (value === "") {
    possibleValues = [""].concat(possibleValues);
  }

  let defaultValue;

  // transform all possible values to select options
  // TODO: Check key
  const options = possibleValues.map((possibleValue) => {
    if (possibleValue === value) {
      defaultValue = value;
    }
    return <option key={possibleValue}>{possibleValue}</option>;
  });

  return (
    <Form.Select
      ref={ref as RefObject<HTMLSelectElement>}
      size="sm"
      disabled={props.disabled}
      defaultValue={defaultValue}
      onChange={(event) => props.onChangeHandler(event as any)}
    >
      {options}
    </Form.Select>
  );
});

export default SelectInput;
