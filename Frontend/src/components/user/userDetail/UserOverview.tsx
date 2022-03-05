import { useEffect } from "react";
import { Table } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "../../../store/combine-store";
import { storeUsers } from "../../../store/user/user-store";
import { getRequest } from "../../../utility/genericHTTPFunctions";
import UserRow from "./UserRow";

const UserOverview: React.FC = () => {

    const dispatch = useDispatch();

    const { authenticatedUser, users } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser,
            users: state.user.users
        }
    });

    useEffect(() => {
        if (authenticatedUser) {
            dispatch(getRequest("/authentication/user", storeUsers));
        }
    }, [dispatch, authenticatedUser]);

    let table;

    if (users) {
        table = <Table striped bordered hover size="sm" style={{
            marginBottom: '3rem'
        }}>
            <thead>
                <tr>
                    <th></th>
                    <th>ID</th>
                    <th>Spielername</th>
                    <th>Anzahl Teams</th>
                    <th>Freigeschalten</th>
                    <th>User Status</th>
                    <th style={{ textAlign: 'center' }}>Administrator</th>
                    <th style={{ textAlign: 'center' }}>Moderator</th>
                    <th style={{ textAlign: 'center' }}>Player</th>
                </tr>
            </thead>
            <tbody>
                {users.map(user => <UserRow key={user.userId} user={user} />)}
            </tbody>
        </Table>
    }

    return <>
        {table}
    </>;

};

export default UserOverview;