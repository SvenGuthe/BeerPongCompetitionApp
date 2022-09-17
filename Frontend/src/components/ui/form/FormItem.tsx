import React, { MutableRefObject, useState } from "react";
import { Button } from "react-bootstrap";
import CheckboxInput from "./CheckboxInput";

import classes from "./FormItem.module.css";
import MultiSelectInput from "./MultiSelectInput";
import SelectInput from "./SelectInput";
import TextInput from "./TextInput";

/**
 * Props data structure to define
 *  a default value (for several input types)
 *  possible other values (for select-inputs)
 *  optional flag if a select input should allow multiple selects
 *  a function what should happend if the input was changed
 *  flag if an "add" button should be added
 */
interface Props {
  defaultValue?: string | number | boolean | string[] | number[];
  possibleValues?: string[] | number[];
  multiSelect?: boolean;
  saveValue: (
    newValue: string | number | boolean | string[] | number[],
    changed: boolean
  ) => void;
  add?: boolean;
}

/**
 * Component to create an Input Form
 */
const FormItem = React.forwardRef<HTMLElement, Props>((props, ref) => {
  const add = props.add ? props.add : false;

  // Set edit mode to true if the "add" property is true
  const [editMode, setEditMode] = useState(add ? true : false);

  // Set the value to the default Value (if given through the props)
  const [value, setValue] = useState(props.defaultValue);

  // Set the settings for the select input
  const multiSelect = props.multiSelect ? props.multiSelect : false;

  // Set possible values for select inputs
  const possibleValues = props.possibleValues ? props.possibleValues : [];

  // Create a reference for the created input to pass it through a save function
  const inputRef = ref as MutableRefObject<HTMLInputElement>;

  // Handler to toggle the edit mode via an click on the "Edit" Button
  const onClickEditHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    // Toggle the edit mode (to edit view)
    setEditMode((currentEditMode) => !currentEditMode);
  };

  // Handler to store text input data when clicked on the "Save" Button
  const onClickSaveTextHandler = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    // Save the values via the saveValue function if the inputRef is present and has a value
    if (inputRef.current) {
      props.saveValue(inputRef.current.value, inputRef.current.value !== value);
      setValue(inputRef.current.value);
    }

    // Toggle the edit mode (to just view)
    !add && setEditMode((currentEditMode) => !currentEditMode);
  };

  // Handler to store checkbox input data when clicked on the "Save" Button
  const onClickSaveCheckboxHandler = (
    event: React.MouseEvent<HTMLButtonElement>
  ) => {
    // Save the values via the saveValue function if the inputRef is present and has a value
    if (inputRef.current) {
      props.saveValue(
        inputRef.current.checked,
        inputRef.current.checked !== value
      );
      setValue(inputRef.current.checked);
    }

    // Toggle the edit mode (to just view)
    !add && setEditMode((currentEditMode) => !currentEditMode);
  };

  // Handler if the value in a select-input was selected (changed)
  const onChangeSelectHandler = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    // Set the choosen value
    setValue(
      (possibleValues as string[]).filter(
        (value) => value === event.target.value
      )
    );
  };

  // Handler to store the selected values in the select inputs
  const onClickSelectHandler = (event: React.MouseEvent<HTMLButtonElement>) => {
    // Save the currently selected values
    props.saveValue(value ? value : "", true);

    // Toggle the edit mode (to just view)
    !add && setEditMode(false);
  };

  // Handler if the value in a multi-select-input was selected (changed)
  const onChangeMultiSelectHandler = (
    event: React.ChangeEvent<HTMLSelectElement>
  ) => {
    const selectedOptions = event.currentTarget.selectedOptions;
    const newState = [];
    // Iterate through all selected values and try to find these values in the possible value
    for (let i = 0; i < selectedOptions.length; i++) {
      const foundString = (possibleValues as string[]).find(
        (possibleValue) => possibleValue === selectedOptions[i].value
      );
      if (foundString) {
        newState.push(foundString);
      }
    }
    // Set the value to all the selected entries
    setValue(newState);
  };

  /**
   * Function to generate JSX with the Input Fields
   * @param disabled flag if the Input should be disabled
   * @param add flag to identify if the input is used to add a entry (true) or to change a value (false)
   * @returns a section with the input field and the button (add / edit / save)
   */
  const getDiv = (disabled: boolean, add: boolean) => {
    let rightInputType;
    let handler;

    // if the default value is a number / string / object
    if (
      typeof props.defaultValue === "number" ||
      typeof props.defaultValue === "string" ||
      typeof props.defaultValue === "object"
    ) {
      // if possible values are set -> it have to be a select input field
      if (props.possibleValues) {
        // if multiple values are possible
        if (multiSelect) {
          rightInputType = (
            <MultiSelectInput
              value={value as string[]}
              disabled={disabled}
              possibleValues={props.possibleValues as string[]}
              onChangeHandler={onChangeMultiSelectHandler}
              ref={inputRef}
            />
          );
          handler = onClickSelectHandler;
        }
        // if just single values are possible
        else {
          rightInputType = (
            <SelectInput
              value={value as string[]}
              disabled={disabled}
              possibleValues={props.possibleValues as string[]}
              onChangeHandler={onChangeSelectHandler}
              ref={inputRef}
            />
          );
          handler = onClickSelectHandler;
        }
      }
      // if possible values are not set -> it have to be a normal text input field
      else {
        rightInputType = (
          <TextInput
            value={value as string}
            disabled={disabled}
            ref={inputRef}
          />
        );
        handler = onClickSaveTextHandler;
      }
    }
    // if the default value is a boolean
    else {
      rightInputType = (
        <CheckboxInput
          value={value as boolean}
          disabled={disabled}
          ref={inputRef}
        />
      );
      handler = onClickSaveCheckboxHandler;
    }

    let clickAndLabel: {
      click: (event: React.MouseEvent<HTMLButtonElement>) => void;
      label: string;
    };

    // if there should be an "add" button
    if (add) {
      clickAndLabel = {
        click: handler,
        label: "Add",
      };
    }
    // if the div is disabled (is the case in the viewer mode)
    else if (disabled) {
      clickAndLabel = {
        click: onClickEditHandler,
        label: "Edit",
      };
    }
    // if the div is enabled (is the case in the edit mode)
    else {
      clickAndLabel = {
        click: handler,
        label: "Save",
      };
    }

    // Return the JSX Code with the Input Field and the Button
    return (
      <section className={classes.container}>
        <div className={classes.value}>{rightInputType}</div>
        <div className={classes.button}>
          <Button
            variant="secondary"
            size="sm"
            onClick={clickAndLabel.click}
            style={{ width: "100px" }}
          >
            {clickAndLabel.label}
          </Button>
        </div>
      </section>
    );
  };

  // in viewer mode, the div should be disabled
  const viewerModeInput = getDiv(true, add);

  // in edit mode, the div should be enabled
  const editModeInput = getDiv(false, add);

  return editMode ? editModeInput : viewerModeInput;
});

export default FormItem;
