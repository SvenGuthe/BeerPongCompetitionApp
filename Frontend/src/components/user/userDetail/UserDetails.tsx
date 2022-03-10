import { useEffect, useState } from "react";
import { Table } from "react-bootstrap";
import { useDispatch, useSelector } from "react-redux";
import { useParams } from "react-router-dom";
import { RootState } from "../../../store/combine-store";
import { addUser } from "../../../store/user/user-store";
import { tUserDetail } from "../../../types/user";
import { getRequestWithID } from "../../../utility/genericHTTPFunctions";
import RolesTable from "./RolesTable";
import TeamsTable from "./TeamsTable";

const UserDetails: React.FC = () => {

    const [selectedUser, setSelectedUser] = useState<tUserDetail>();
    const dispatch = useDispatch();
    const id = useParams().id;

    const { users } = useSelector((state: RootState) => {
        return {
            users: state.user.users
        };
    });

    useEffect(() => {

        if (id) {
            const user = users?.find(user => user.id === +id);
            if (user) {
                setSelectedUser(user)
            } else {
                dispatch(getRequestWithID(+id, "/authentication/user", addUser));
            }
        }

    }, [id, dispatch, users]);

    return <>
        <h3>{selectedUser?.gamerTag}</h3>
        <Table striped bordered hover size="sm">
            <tbody>
                <tr>
                    <td>User ID</td>
                    <td>{selectedUser?.id}</td>
                </tr>
                <tr>
                    <td>Vorname</td>
                    <td>{selectedUser?.firstName}</td>
                </tr>
                <tr>
                    <td>Nachname</td>
                    <td>{selectedUser?.lastName}</td>
                </tr>
                <tr>
                    <td>Spielername</td>
                    <td>{selectedUser?.gamerTag}</td>
                </tr>
                <tr>
                    <td>E-Mail</td>
                    <td>{selectedUser?.email}</td>
                </tr>
                <tr>
                    <td>Profil angelegt am</td>
                    <td>{selectedUser?.creationTime}</td>
                </tr>
                <tr>
                    <td>Freigeschalten</td>
                    <td>{selectedUser?.enabled ? "TRUE" : "FALSE"}</td>
                </tr>
                <tr>
                    <td>Status</td>
                    <td>{selectedUser?.userStatus.userStatus}</td>
                </tr>
            </tbody>
        </Table>
        {selectedUser && <RolesTable user={selectedUser} />}
        {selectedUser && <TeamsTable user={selectedUser} />}
    </>;
};

export default UserDetails;