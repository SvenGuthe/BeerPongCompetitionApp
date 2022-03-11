import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { userStatusRoute } from "../../api-routes/authentication";
import { RootState } from "../../store/combine-store";
import { storeUserStatus } from "../../store/user/user-store";
import { homeHierarchy, userStatusHierarchy } from "../../utility/hierarchy";

const UserStatus = () => {

    const userStatus = useSelector((state: RootState) => {
        return state.user.userStatus;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, userStatusHierarchy]} />
        <h2>Nutzer Status</h2>
        <EnumOverview url={userStatusRoute} storeFunction={storeUserStatus} paginationData={userStatus} />
    </>

};

export default UserStatus;