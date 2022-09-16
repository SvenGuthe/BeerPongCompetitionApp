import React from "react";
import { Form } from "react-bootstrap";

/**
 * Props data structure to define
 *  values as default
 *  if the text input should be disabled
 *  type to define it as text or number // TODO: Check if we should use a boolean
 */
interface Props {
  value: string | number;
  disabled: boolean;
  type?: string;
}

/**
 * Component which returns the JSX of a text input
 */
const TextInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {
  const type = props.type ? props.type : "text";

  return (
    <Form.Control
      ref={ref}
      type={type}
      size="sm"
      defaultValue={props.value}
      disabled={props.disabled}
    />
  );
});

export default TextInput;
