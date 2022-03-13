import { Table } from "react-bootstrap";
import { tEnum } from "../../types/defaults/generics";
import EnumRow from "./EnumRow";

const EnumTable: React.FC<{
    enumData: tEnum[],
    wrapped?: boolean
}> = (props) => {

    const enumData = props.enumData;
    const wrapped = props.wrapped ? props.wrapped : false;

    const input = <>
        <thead>
            <tr>
                <th>ID</th>
                <th>Value</th>
            </tr>
        </thead>
        <tbody>
            {enumData.map(singleEnum => <EnumRow key={singleEnum.id} enum={singleEnum} />)}
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

};

export default EnumTable;