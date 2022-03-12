import { Form } from "react-bootstrap";
import { tEnum } from "../../../types/defaults/generics";

const SelectInput: React.FC<{
    value: tEnum,
    disabled: boolean,
    possibleValues: tEnum[],
    onChangeHandler: (event: React.ChangeEvent<HTMLInputElement>) => void;
}> = (props) => {

    const value = props.value;

    let defaultValue;

    const options = props.possibleValues.map(possibleValue => {
        if (possibleValue.id === value.id) {
            defaultValue = value.id;
        }
        return <option key={possibleValue.id}>{possibleValue.value}</option>
    })

    return <Form.Select
        size="sm"
        disabled={props.disabled}
        defaultValue={defaultValue}
        onChange={(event) => props.onChangeHandler(event as any)}
    >
        {options}
    </Form.Select>

}

export default SelectInput;