import React from "react";
import { Form } from "react-bootstrap";

interface Props {
    value: boolean,
    disabled: boolean
}

const CheckboxInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {

    return <Form.Check
        ref={ref}
        disabled={props.disabled}
        defaultChecked={props.value}
    />

});

export default CheckboxInput;