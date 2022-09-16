import React, { RefObject } from "react";
import { Form } from "react-bootstrap";

/**
 * Props data structure to define
 *  selected values as default
 *  if the multi select input should be disabled
 *  possible values for the multi-select-input
 *  handler, when the choosen entry was changed
 */
interface Props {
  value: string[];
  disabled: boolean;
  possibleValues: string[];
  onChangeHandler: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

/**
 * Component which returns the JSX of a multi-select-input
 */
const MultiSelectInput = React.forwardRef<HTMLInputElement, Props>(
  (props, ref) => {
    const value = props.value;

    let defaultValues: string[] = [];

    // transform the possible values to options with keys
    // TODO: Check if we should change the key (Will be a problem, if the value is not unique)
    const options = props.possibleValues.map((possibleValue) => {
      const defaultValue = value.find((val) => val === possibleValue);
      if (defaultValue) {
        defaultValues.push(defaultValue);
      }
      return <option key={possibleValue}>{possibleValue}</option>;
    });

    return (
      <Form.Select
        ref={ref as RefObject<HTMLSelectElement>}
        size="sm"
        disabled={props.disabled}
        multiple
        defaultValue={defaultValues}
        onChange={(event) => props.onChangeHandler(event as any)}
      >
        {options}
      </Form.Select>
    );
  }
);

export default MultiSelectInput;
