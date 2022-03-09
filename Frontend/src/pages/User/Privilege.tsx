import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { privilegeRoute } from "../../api-routes/authentication";
import { RootState } from "../../store/combine-store";
import { homeHierarchy, privilegeHierarchy } from "../../types/hierarchy";
import { storePrivileges } from "../../store/user/user-store";

const Privilege = () => {

    const privileges = useSelector((state: RootState) => {
        return state.user.privileges;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, privilegeHierarchy]} />
        <h2>Privilegien</h2>
        <EnumOverview url={privilegeRoute} storeFunction={storePrivileges} paginationData={privileges} />
    </>

};

export default Privilege;