import { Form } from "react-bootstrap";
import { tEnum } from "../../../types/defaults/generics";

const MultiSelectInput: React.FC<{
    value: tEnum[],
    disabled: boolean,
    possibleValues: tEnum[],
    onChangeHandler: (event: React.ChangeEvent<HTMLSelectElement>) => void;
}> = (props) => {

    const value = props.value;

    let defaultValues: string[] = [];

    const options = props.possibleValues.map(possibleValue => {
        const defaultValue = value.find(val => val.id === possibleValue.id);
        if (defaultValue) {
            defaultValues.push(defaultValue.value);
        }
        return <option key={possibleValue.id}>{possibleValue.value}</option>
    });

    return <Form.Select
        size="sm"
        disabled={props.disabled}
        multiple
        defaultValue={defaultValues}
        onChange={(event) => props.onChangeHandler(event as any)}
    >
        {options}
    </Form.Select>

}

export default MultiSelectInput;