import { FormControl, InputGroup } from "react-bootstrap";

/**
 * Component of the search-input for table
 * @param props the handler what happend if the search-input was changed
 * @returns The Input Group with the search filter as JSX
 */
const Search: React.FC<{
  onChangeSearchHandler: (event: React.ChangeEvent<HTMLInputElement>) => void;
}> = (props) => {
  const { onChangeSearchHandler } = props;

  return (
    <InputGroup size="sm" className="mb-3">
      <FormControl
        aria-label="Filter"
        aria-describedby="inputGroup-sizing-sm "
        onChange={onChangeSearchHandler}
      />
      <InputGroup.Text id="inputGroup-sizing-sm">Filter</InputGroup.Text>
    </InputGroup>
  );
};

export default Search;
