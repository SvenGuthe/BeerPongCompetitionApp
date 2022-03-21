import { Table } from "react-bootstrap";
import { tConfirmationToken } from "../../../../types/authentication";
import ConfirmationTokenAddRow from "./ConfirmationTokenAddRow";
import ConfirmationTokenRow from "./ConfirmationTokenRow";

const ConfirmationTokenTable: React.FC<{
    confirmationToken: tConfirmationToken[],
    wrapped?: boolean,
    additionalAttributesHeader?: string[]
}> = (props) => {

    const confirmationToken = props.confirmationToken;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th>ID</th>
                <th>Erstellt am</th>
                <th>Token</th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {confirmationToken.map(confirmationToken => <ConfirmationTokenRow key={confirmationToken.id} confirmationToken={confirmationToken} />)}
            <ConfirmationTokenAddRow />
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

};

export default ConfirmationTokenTable;