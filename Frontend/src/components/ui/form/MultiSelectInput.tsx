import React, { RefObject } from "react";
import { Form } from "react-bootstrap";

interface Props {
    value: string[],
    disabled: boolean,
    possibleValues: string[],
    onChangeHandler: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

const MultiSelectInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {

    const value = props.value;

    let defaultValues: string[] = [];

    const options = props.possibleValues.map(possibleValue => {
        const defaultValue = value.find(val => val === possibleValue);
        if (defaultValue) {
            defaultValues.push(defaultValue);
        }
        return <option key={possibleValue}>{possibleValue}</option>
    });

    return <Form.Select
        ref={ref as RefObject<HTMLSelectElement>}
        size="sm"
        disabled={props.disabled}
        multiple
        defaultValue={defaultValues}
        onChange={(event) => props.onChangeHandler(event as any)}
    >
        {options}
    </Form.Select>

});

export default MultiSelectInput;