import { tBillingStatusType } from "../../../types/enums/billingStatusType";
import { tCompetitionAdminStatusType } from "../../../types/enums/competitionAdminStatusType";
import { tCompetitionPlayerStatusType } from "../../../types/enums/competitionPlayerStatusType";
import { tCompetitionStatusType } from "../../../types/enums/competitionStatusType";
import { tRegistrationStatusType } from "../../../types/enums/registrationStatusType";
import { tSecurityPrivilege } from "../../../types/enums/securityPrivilege";
import { tSecurityRole } from "../../../types/enums/securityRole";
import { tTeamStatusType } from "../../../types/enums/teamStatusType";
import { tUserStatusType } from "../../../types/enums/userStatusType";
import FormItem from "./FormItem";

export const BillingStatusTypeSelectInput: React.FC<{
    defaultValue?: tBillingStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tBillingStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const CompetitionAdminStatusTypeInput: React.FC<{
    defaultValue?: tCompetitionAdminStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tCompetitionAdminStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const CompetitionPlayerStatusTypeInput: React.FC<{
    defaultValue?: tCompetitionPlayerStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tCompetitionPlayerStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const CompetitionStatusTypeInput: React.FC<{
    defaultValue?: tCompetitionStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tCompetitionStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const RegistrationStatusTypeInput: React.FC<{
    defaultValue?: tRegistrationStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tRegistrationStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const SecurityPrivilegeInput: React.FC<{
    defaultValue?: tSecurityPrivilege,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tSecurityPrivilege);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const SecurityRoleInput: React.FC<{
    defaultValue?: tSecurityRole,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tSecurityRole);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const MultiSecurityRoleInput: React.FC<{
    defaultValue?: tSecurityRole[],
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tSecurityRole);
    const defaultValue = props.defaultValue ? props.defaultValue : [];

    return <FormItem
        defaultValue={defaultValue}
        possibleValues={allPossibleValues}
        multiSelect
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const TeamStatusTypeInput: React.FC<{
    defaultValue?: tTeamStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tTeamStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}

export const UserStatusTypeInput: React.FC<{
    defaultValue?: tUserStatusType,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}> = (props) => {

    const allPossibleValues = Object.keys(tUserStatusType);
    const defaultValue = props.defaultValue ? props.defaultValue : allPossibleValues[0];

    return <FormItem
        defaultValue={[defaultValue]}
        possibleValues={allPossibleValues}
        saveValue={props.saveValue}
        add={props.add}
    />;

}