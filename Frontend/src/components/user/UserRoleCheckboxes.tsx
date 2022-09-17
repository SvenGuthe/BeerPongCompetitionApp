import { useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { tSecurityRole } from "../../types/enums/securityRole";
import tUser from "../../types/user/user";
import FormItem from "../ui/form/FormItem";

const UserRoleCheckboxes: React.FC<{
  user: tUser;
  role: tSecurityRole;
}> = (props) => {
  const user = props.user;
  const role = props.role;

  const initialRoleState = !!user.roles.find(
    (currentRole) => currentRole.role === role
  );
  const dispatch = useDispatch();

  const [roleState, setRoleState] = useState<boolean>(initialRoleState);
  const [sendRequest, setSendRequest] = useState<boolean>(false);

  useEffect(() => {
    if (sendRequest) {
      // Send Change Role Request
      setSendRequest(false);
    }
  }, [sendRequest, roleState, user.id, role, dispatch]);

  const onToggleRoleHandler = (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => {
    setRoleState(newValue as boolean);
    console.log(role, newValue, changed);

    setSendRequest(true);
  };

  return <FormItem defaultValue={roleState} saveValue={onToggleRoleHandler} />;
};

export default UserRoleCheckboxes;
