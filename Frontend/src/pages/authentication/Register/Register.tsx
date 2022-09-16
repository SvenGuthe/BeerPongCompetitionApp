import { default as RegisterComponent } from "../../../components/register/Register";
import Hierarchy from "../../../components/ui/Hierarchy";
import { homeHierarchy, registerHierarchy } from "../../../utility/hierarchy";

const Register = () => {
  return (
    <>
      <Hierarchy hierarchyItems={[homeHierarchy, registerHierarchy]} />
      <h2>Register</h2>
      <RegisterComponent />
    </>
  );
};

export default Register;
