import { Form } from "react-bootstrap";

const TextInput: React.FC<{
    reference: React.RefObject<HTMLInputElement>,
    value: string | number,
    disabled: boolean
}> = (props) => {

    return <Form.Control
        ref={props.reference}
        type="text"
        size="sm"
        defaultValue={props.value}
        disabled={props.disabled}
    />

}

export default TextInput;