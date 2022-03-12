import { useRef, useState } from "react";
import { Button } from "react-bootstrap";
import CheckboxInput from "./CheckboxInput";

import classes from './FormItem.module.css';
import MultiSelectInput from "./MultiSelectInput";
import SelectInput from "./SelectInput";
import TextInput from "./TextInput";

interface Props {
    defaultValue: string | number | boolean | string[] | number[],
    possibleValues?: string[] | number[],
    multiSelect?: boolean,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void
}

const FormItem = (props: Props) => {

    const [editMode, setEditMode] = useState(false);
    const [value, setValue] = useState(props.defaultValue);

    const multiSelect = props.multiSelect ? props.multiSelect : false;
    const possibleValues = props.possibleValues ? props.possibleValues : [];

    const inputRef = useRef<HTMLInputElement>(null);

    const onClickEditHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        setEditMode((currentEditMode) => !currentEditMode);
    }

    const onClickSaveTextHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        if (inputRef.current) {
            props.saveValue(inputRef.current.value, inputRef.current.value !== value);
            setValue(inputRef.current.value);
        }
        setEditMode((currentEditMode) => !currentEditMode);
    }

    const onClickSaveCheckboxHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        if (inputRef.current) {
            props.saveValue(inputRef.current.checked, inputRef.current.checked !== value);
            setValue(inputRef.current.checked);
        }
        setEditMode((currentEditMode) => !currentEditMode);
    }

    const onChangeSelectHandler = (event: React.ChangeEvent<HTMLInputElement>) => {
        setValue((possibleValues! as string[]).filter(value => value === event.target.value))
    }

    const onClickSelectHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        props.saveValue(value, true);
        setEditMode(false);
    }

    const onChangeMultiSelectHandler = (event: React.ChangeEvent<HTMLSelectElement>) => {
        const selectedOptions = event.currentTarget.selectedOptions;
        const newState = [];
        for (let i = 0; i < selectedOptions.length; i++) {
            const foundString = (possibleValues as string[]).find(possibleValue => possibleValue === selectedOptions[i].value);
            if (foundString) {
                newState.push(foundString);
            }
        }
        setValue(newState);
    }

    const getDiv = (
        disabled: boolean
    ) => {

        let rightInputType;
        let handler;

        if (typeof props.defaultValue === "number" || typeof props.defaultValue === "string" || typeof props.defaultValue === "object") {
            if (props.possibleValues) {
                if (multiSelect) {
                    rightInputType = <MultiSelectInput value={value as string[]} disabled={disabled} possibleValues={props.possibleValues as string[]} onChangeHandler={onChangeMultiSelectHandler} />
                    handler = onClickSelectHandler;
                } else {
                    rightInputType = <SelectInput value={value as string} disabled={disabled} possibleValues={props.possibleValues as string[]} onChangeHandler={onChangeSelectHandler} />
                    handler = onClickSelectHandler;
                }
            } else {                
                rightInputType = <TextInput value={value as string} disabled={disabled} reference={inputRef} />
                handler = onClickSaveTextHandler;
            }
        } else {
            rightInputType = <CheckboxInput value={value as boolean} disabled={disabled} reference={inputRef} />
            handler = onClickSaveCheckboxHandler;
        }

        return <section className={classes.container}>
            <div className={classes.value}>
                {rightInputType}
            </div>
            <div className={classes.button}><Button variant="secondary" size="sm" onClick={disabled ? onClickEditHandler : handler} style={{ width: '100px' }}>{disabled ? "Edit" : "Save"}</Button></div>
        </section>
    }

    const viewerModeInput = getDiv(true);
    const editModeInput = getDiv(false);

    return editMode ? editModeInput : viewerModeInput;

}

export default FormItem;