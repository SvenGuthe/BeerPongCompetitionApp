import { Form } from "react-bootstrap";

const SelectInput: React.FC<{
    value: string,
    disabled: boolean,
    possibleValues: string[],
    onChangeHandler: (event: React.ChangeEvent<HTMLInputElement>) => void;
}> = (props) => {

    const value = props.value;

    let defaultValue;    

    const options = props.possibleValues.map(possibleValue => {
        if (possibleValue === value) {
            defaultValue = value;
        }
        return <option key={possibleValue}>{possibleValue}</option>
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