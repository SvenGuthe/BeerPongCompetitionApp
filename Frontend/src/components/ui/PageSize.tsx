import { MouseEvent } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

/**
 * Component for the Buttongroup to select the page size
 * @param props the array with possible page sizes
 *              the handler how to handle a click on a single button
 *              the currently selected page size
 * @returns the Buttongroupe with the page sizes as JSX
 */
const PageSize: React.FC<{
  pageSizes: number[];
  onChangePageSizeHandler: (
    pageSize: number
  ) => (event: MouseEvent<HTMLButtonElement>) => void;
  currentPageSize: number;
}> = (props) => {
  const onChangePageSizeHandler = props.onChangePageSizeHandler;

  return (
    <ButtonGroup size="sm" className="mb-2">
      {props.pageSizes.map((pageSize) => (
        <Button
          key={pageSize}
          variant={pageSize === props.currentPageSize ? "secondary" : "light"}
          onClick={onChangePageSizeHandler(pageSize)}
        >
          {pageSize}
        </Button>
      ))}
    </ButtonGroup>
  );
};

export default PageSize;
