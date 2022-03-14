import { ReactElement } from "react";
import { Table } from "react-bootstrap";
import { tEnum } from "../../types/defaults/generics";
import EnumRow from "./EnumRow";

const EnumTable: React.FC<{
    enumData: tEnum[],
    wrapped?: boolean,
    additionalAttributesHeader?: string[],
    addRow?: ReactElement
}> = (props) => {

    const enumData = props.enumData;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th>ID</th>
                <th>Value</th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {enumData.map(singleEnum => <EnumRow key={singleEnum.id} enum={singleEnum} />)}
            {props.addRow}
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

};

export default EnumTable;