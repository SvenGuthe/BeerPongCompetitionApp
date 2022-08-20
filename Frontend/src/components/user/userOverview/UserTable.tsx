import { Table } from "react-bootstrap";
import { tUser } from "../../../types/user";
import UserRow from "./UserRow";

const UserTable: React.FC<{
    users: tUser[],
    wrapped?: boolean
    additionalAttributesHeader?: string[]
}> = (props) => {

    const users = props.users;
    const wrapped = props.wrapped ? props.wrapped : false;
    const additionalAttributesHeader = props.additionalAttributesHeader ? props.additionalAttributesHeader : [];

    const input = <>
        <thead>
            <tr>
                <th></th>
                <th>ID</th>
                <th>Spielername</th>
                <th>Freigeschalten</th>
                <th>User Status</th>
                {additionalAttributesHeader.map(singleAdditionalAttributesHeader => {
                    return <th key={singleAdditionalAttributesHeader}>{singleAdditionalAttributesHeader}</th>
                })}
            </tr>
        </thead>
        <tbody>
            {users.map(user => <UserRow key={user.id} user={user} />)}
        </tbody>
    </>;

    const wrappedInput = wrapped ? <Table striped bordered hover size="sm">{input}</Table> : input;

    return wrappedInput;

};

export default UserTable;