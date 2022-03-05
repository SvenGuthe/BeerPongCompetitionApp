import { Link } from "react-router-dom";
import { HierarchyItem } from "../../types/hierarchy";

const Hierarchy: React.FC<{
    hierarchyItems: HierarchyItem[]
}> = (props) => {

    const hierarchyItems = props.hierarchyItems;

    const hierarchyOutput = hierarchyItems.map(hierarchyItem => {
        return <Link to={hierarchyItem.url}>{hierarchyItem.label}</Link>
    }).reduce((a, b) => {
        return <>{a} / {b}</>
    })

    return <div style={{marginBottom: "1rem"}}>
        Navigation: / {hierarchyOutput}
    </div>;
}

export default Hierarchy;