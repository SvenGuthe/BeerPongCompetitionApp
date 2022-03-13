import { tEnum } from "../../types/defaults/generics";

const EnumRow: React.FC<{
    enum: tEnum
}> = (props) => {

    const enumValue = props.enum;

    return <tr>
        <td>{enumValue.id}</td>
        <td>{enumValue.value}</td>
    </tr>;

};

export default EnumRow;