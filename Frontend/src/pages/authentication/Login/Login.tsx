import { default as LoginComponent } from "../../../components/login/Login";
import Hierarchy from "../../../components/ui/Hierarchy";
import { homeHierarchy, loginHierarchy } from "../../../utility/hierarchy";

const Login: React.FC = () => {

    return <>
        <Hierarchy hierarchyItems={[homeHierarchy, loginHierarchy]} />
        <h2>Login</h2>
        <LoginComponent />
    </>;
};

export default Login;