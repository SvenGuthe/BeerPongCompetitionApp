import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { roleRoute } from "../../api-routes/user";
import { RootState } from "../../store/combine-store";
import { storeRoles } from "../../store/user/user-store";
import { homeHierarchy, roleHierarchy } from "../../utility/hierarchy";

const Role = () => {

    const roles = useSelector((state: RootState) => {
        return state.user.roles;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, roleHierarchy]} />
        <h2>Rollen</h2>
        <EnumOverview url={roleRoute} storeFunction={storeRoles} paginationData={roles} />
    </>

};

export default Role;