import classes from "./TableSection.module.css";

const TableSection: React.FC = (props) => {

    return <div className={classes.section}>
        {props.children}
    </div>

}

export default TableSection;