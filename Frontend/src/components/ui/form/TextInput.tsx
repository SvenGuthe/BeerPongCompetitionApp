import { Form } from "react-bootstrap";

const TextInput: React.FC<{
    reference: React.RefObject<HTMLInputElement>,
    value: string | number,
    disabled: boolean,
    type?: string
}> = (props) => {

    const type = props.type ? props.type : "text"

    return <Form.Control
        ref={props.reference}
        type={type}
        size="sm"
        defaultValue={props.value}
        disabled={props.disabled}
    />

}

export default TextInput;