import React from "react";
import { Form } from "react-bootstrap";

interface Props {
    value: string | number,
    disabled: boolean,
    type?: string
}

const TextInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {

    const type = props.type ? props.type : "text"

    return <Form.Control
        ref={ref}
        type={type}
        size="sm"
        defaultValue={props.value}
        disabled={props.disabled}
    />

});

export default TextInput;