import { tEnum } from "../../types/defaults/generics";

const EnumRow: React.FC<{
    enum: tEnum
}> = (props) => {

    const enumValue = props.enum;
    const additionalAttributes = enumValue.additionalAttributes ? enumValue.additionalAttributes : []

    return <tr>
        <td>{enumValue.id}</td>
        <td>{enumValue.value}</td>
        {additionalAttributes.map(additionalAttribute => {
            return additionalAttribute.reactElement ? <td key={additionalAttribute.id}>{additionalAttribute.reactElement}</td> : <td key={additionalAttribute.id}>{additionalAttribute.value}</td>;
        })}
    </tr>;

};

export default EnumRow;