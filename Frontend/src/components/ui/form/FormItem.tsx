import React, { MutableRefObject, useState } from "react";
import { Button } from "react-bootstrap";
import CheckboxInput from "./CheckboxInput";

import classes from './FormItem.module.css';
import MultiSelectInput from "./MultiSelectInput";
import SelectInput from "./SelectInput";
import TextInput from "./TextInput";

interface Props {
    defaultValue?: string | number | boolean | string[] | number[],
    possibleValues?: string[] | number[],
    multiSelect?: boolean,
    saveValue: (newValue: string | number | boolean | string[] | number[], changed: boolean) => void,
    add?: boolean
}

const FormItem = React.forwardRef<HTMLElement, Props>((props, ref) => {

    const add = props.add ? props.add : false;

    const [editMode, setEditMode] = useState(add ? true : false);
    const [value, setValue] = useState(props.defaultValue);

    const multiSelect = props.multiSelect ? props.multiSelect : false;
    const possibleValues = props.possibleValues ? props.possibleValues : [];

    const inputRef = ref as MutableRefObject<HTMLInputElement>;

    const onClickEditHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        setEditMode((currentEditMode) => !currentEditMode);
    }

    const onClickSaveTextHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        if (inputRef.current) {
            props.saveValue(inputRef.current.value, inputRef.current.value !== value);
            setValue(inputRef.current.value);
        }
        !add && setEditMode((currentEditMode) => !currentEditMode);
    }

    const onClickSaveCheckboxHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        if (inputRef.current) {
            props.saveValue(inputRef.current.checked, inputRef.current.checked !== value);
            setValue(inputRef.current.checked);
        }
        !add && setEditMode((currentEditMode) => !currentEditMode);
    }

    const onChangeSelectHandler = (event: React.ChangeEvent<HTMLSelectElement>) => {
        setValue((possibleValues! as string[]).filter(value => value === event.target.value))
    }

    const onClickSelectHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
        props.saveValue(value ? value : "", true);
        !add && setEditMode(false);
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
        disabled: boolean,
        add: boolean
    ) => {

        let rightInputType;
        let handler;

        if (typeof props.defaultValue === "number" || typeof props.defaultValue === "string" || typeof props.defaultValue === "object") {
            if (props.possibleValues) {
                if (multiSelect) {
                    rightInputType = <MultiSelectInput value={value as string[]} disabled={disabled} possibleValues={props.possibleValues as string[]} onChangeHandler={onChangeMultiSelectHandler} ref={inputRef} />
                    handler = onClickSelectHandler;
                } else {
                    rightInputType = <SelectInput value={value as string[]} disabled={disabled} possibleValues={props.possibleValues as string[]} onChangeHandler={onChangeSelectHandler} ref={inputRef} />
                    handler = onClickSelectHandler;
                }
            } else {
                rightInputType = <TextInput value={value as string} disabled={disabled} ref={inputRef} />
                handler = onClickSaveTextHandler;
            }
        } else {
            rightInputType = <CheckboxInput value={value as boolean} disabled={disabled} ref={inputRef} />
            handler = onClickSaveCheckboxHandler;
        }

        let clickAndLabel: {
            click: (event: React.MouseEvent<HTMLButtonElement>) => void,
            label: string
        }

        if (add) {
            clickAndLabel = {
                click: handler,
                label: "Add"
            }
        } else if (disabled) {
            clickAndLabel = {
                click: onClickEditHandler,
                label: "Edit"
            }
        } else {
            clickAndLabel = {
                click: handler,
                label: "Save"
            }
        }

        return <section className={classes.container}>
            <div className={classes.value}>
                {rightInputType}
            </div>
            <div className={classes.button}>
                <Button variant="secondary" size="sm" onClick={clickAndLabel.click} style={{ width: '100px' }}>
                    {clickAndLabel.label}
                </Button></div>
        </section>
    }

    const viewerModeInput = getDiv(true, add);
    const editModeInput = getDiv(false, add);

    return editMode ? editModeInput : viewerModeInput;

});

export default FormItem;