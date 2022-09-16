import React from "react";
import { Form } from "react-bootstrap";

/**
 * Props data structure to define
 *  if the Checkbox should be checked as default
 *  if the Checkbox should be disabled
 */
interface Props {
  value: boolean;
  disabled: boolean;
}

/**
 * Component to create a Checkbox Input
 */
const CheckboxInput = React.forwardRef<HTMLInputElement, Props>(
  (props, ref) => {
    return (
      <Form.Check
        ref={ref}
        disabled={props.disabled}
        defaultChecked={props.value}
      />
    );
  }
);

export default CheckboxInput;
