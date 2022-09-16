import React from "react";
import { tBillingStatusType } from "../../../types/enums/billingStatusType";
import { tCompetitionAdminStatusType } from "../../../types/enums/competitionAdminStatusType";
import { tCompetitionPlayerStatusType } from "../../../types/enums/competitionPlayerStatusType";
import { tCompetitionStatusType } from "../../../types/enums/competitionStatusType";
import { tRegistrationStatusType } from "../../../types/enums/registrationStatusType";
import { tSecurityPrivilege } from "../../../types/enums/securityPrivilege";
import { tSecurityRole } from "../../../types/enums/securityRole";
import { tTeamCompositionStatusType } from "../../../types/enums/teamCompositionStatusType";
import { tTeamStatusType } from "../../../types/enums/teamStatusType";
import { tUserStatusType } from "../../../types/enums/userStatusType";
import FormItem from "./FormItem";

// File to hold all predefined select inputs for our enum types

/**
 * Component which returns the JSX of a Select Input field with all BillingStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const BillingStatusTypeSelectInput: React.FC<{
  defaultValue?: tBillingStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tBillingStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all CompetitionAdminStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const CompetitionAdminStatusTypeInput: React.FC<{
  defaultValue?: tCompetitionAdminStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tCompetitionAdminStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all CompetitionPlayerStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const CompetitionPlayerStatusTypeInput: React.FC<{
  defaultValue?: tCompetitionPlayerStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tCompetitionPlayerStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all CompetitionStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const CompetitionStatusTypeInput: React.FC<{
  defaultValue?: tCompetitionStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tCompetitionStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all RegistrationStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const RegistrationStatusTypeInput: React.FC<{
  defaultValue?: tRegistrationStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tRegistrationStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all SecurityPrivileges
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const SecurityPrivilegeInput: React.FC<{
  defaultValue?: tSecurityPrivilege;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tSecurityPrivilege);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all SecurityRoles
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const SecurityRoleInput: React.FC<{
  defaultValue?: tSecurityRole;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tSecurityRole);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Multi-Select Input field with all SecurityRoles
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Multi-Select Input field
 */
export const MultiSecurityRoleInput: React.FC<{
  defaultValue?: tSecurityRole[];
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tSecurityRole);
  const defaultValue = props.defaultValue ? props.defaultValue : [];

  return (
    <FormItem
      defaultValue={defaultValue}
      possibleValues={allPossibleValues}
      multiSelect
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all TeamStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const TeamStatusTypeInput: React.FC<{
  defaultValue?: tTeamStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tTeamStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

/**
 * Component which returns the JSX of a Select Input field with all TeamCompositionStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const TeamCompositionStatusTypeInput: React.FC<{
  defaultValue?: tTeamCompositionStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}> = (props) => {
  const allPossibleValues = Object.keys(tTeamCompositionStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
};

interface UserStatusTypeInputProps {
  defaultValue?: tUserStatusType;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}

/**
 * Component which returns the JSX of a Select Input field with all UserStatusTypes
 * @param props a possible default value
 *              a function how to save the new choosen status
 *              a flag if a entry should be added (true) or changed (false)
 * @returns the JSX of the Select Input field
 */
export const UserStatusTypeInput = React.forwardRef<
  HTMLElement,
  UserStatusTypeInputProps
>((props, ref) => {
  const allPossibleValues = Object.keys(tUserStatusType);
  const defaultValue = props.defaultValue
    ? props.defaultValue
    : allPossibleValues[0];

  return (
    <FormItem
      ref={ref}
      defaultValue={[defaultValue]}
      possibleValues={allPossibleValues}
      saveValue={props.saveValue}
      add={props.add}
    />
  );
});
