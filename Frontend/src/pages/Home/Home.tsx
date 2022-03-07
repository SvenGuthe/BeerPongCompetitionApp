import AuthenticatedUser from "../../components/user/authenticatedUser/AuthenticatedUser";
import { useSelector } from "react-redux";
import { RootState } from "../../store/combine-store";
import Hierarchy from "../../components/ui/Hierarchy";
import { homeHierarchy } from "../../types/hierarchy";
import TableWithSearchAndFilter from "../../components/ui/TableWithSearchAndFilter";

const Home = () => {

    const { authenticatedUser } = useSelector((state: RootState) => {
        return {
            authenticatedUser: state.authentication.authenticatedUser
        };
    });

    const onChangeFunction = (search: string) => {
        console.log("Search String: " + search);
    };

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy]} />
        <h2>Authenticated User</h2>
        {authenticatedUser && <AuthenticatedUser authenticatedUser={authenticatedUser} />}
        <TableWithSearchAndFilter onChangeFunction={onChangeFunction} pageSize={3} itemCount={100}>
            <thead>
                <tr>
                    <th>Test</th>
                    <th>Test2</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td>Test</td>
                    <td>Test2</td>
                </tr>
            </tbody>
        </TableWithSearchAndFilter>
    </>;
};

export default Home;