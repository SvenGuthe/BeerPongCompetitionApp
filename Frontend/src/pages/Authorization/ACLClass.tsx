import { useSelector } from "react-redux";
import EnumOverview from "../../components/enums/EnumOverview";
import Hierarchy from "../../components/ui/Hierarchy";
import { RootState } from "../../store/combine-store";
import { aclClassHierarchy, homeHierarchy } from "../../types/hierarchy";
import { aclClassRoute } from "../../api-routes/authorization";
import { storeACLClasses } from "../../store/authorization/authorization-store";

const ACLClass = () => {

    const aclClasses = useSelector((state: RootState) => {
        return state.authorization.aclClasses;
    })

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, aclClassHierarchy]} />
        <h2>ACL Klassen</h2>
        <EnumOverview url={aclClassRoute} storeFunction={storeACLClasses} paginationData={aclClasses} />
    </>

};

export default ACLClass;