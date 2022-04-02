import React, { RefObject } from "react";
import { Form } from "react-bootstrap";

interface Props {
    value?: string[],
    disabled: boolean,
    possibleValues: string[],
    onChangeHandler: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}

const SelectInput = React.forwardRef<HTMLInputElement, Props>((props, ref) => {

    const value = props.value ? props.value[0] : "";

    let possibleValues = props.possibleValues;

    if (value === "") {
        possibleValues = [""].concat(possibleValues);
    }

    let defaultValue;

    const options = possibleValues.map(possibleValue => {
        if (possibleValue === value) {
            defaultValue = value;
        }
        return <option key={possibleValue}>{possibleValue}</option>
    })

    return <Form.Select
        ref={ref as RefObject<HTMLSelectElement>}
        size="sm"
        disabled={props.disabled}
        defaultValue={defaultValue}
        onChange={(event) => props.onChangeHandler(event as any)}
    >
        {options}
    </Form.Select>

});

export default SelectInput;