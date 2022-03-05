import { Alert } from "react-bootstrap";
import Hierarchy from "../../../components/ui/Hierarchy";
import { homeHierarchy, notFoundHierarchy } from "../../../types/hierarchy";

const NotFound = () => {
    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, notFoundHierarchy]} />
        <h2>404</h2>
        <Alert variant='warning'>Page not found</Alert>
    </>;
};

export default NotFound;