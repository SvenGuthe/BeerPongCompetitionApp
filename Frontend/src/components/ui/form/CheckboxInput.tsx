import { Form } from "react-bootstrap";

const CheckboxInput: React.FC<{
    reference: React.RefObject<HTMLInputElement>,
    value: boolean,
    disabled: boolean
}> = (props) => {

    return <Form.Check
        ref={props.reference}
        isValid={props.value}
        disabled={props.disabled}
    />

}

export default CheckboxInput;