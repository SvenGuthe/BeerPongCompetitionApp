import classes from "./TableSection.module.css";

/**
 * Component to show information in the main section of the laylout
 * and also the "level" of the information = count of stroke-lines (border) on the left side
 * @param props just includes children
 * @returns JSX with the children attributes renders inside the Table Section
 */
const TableSection: React.FC = (props) => {
  return <div className={classes.section}>{props.children}</div>;
};

export default TableSection;
