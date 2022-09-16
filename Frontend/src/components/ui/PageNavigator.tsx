import { MouseEvent, useMemo } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

/**
 * Component to display and change the current page in a list (pagination)
 * TODO: Check if we want to change the count to index
 * @param props the size of a single page
 *              the count of all available items
 *              the current page number
 *              the number of pages which should be displayed lower and greater than the current page
 *              handler what happens if the page should be changed
 * @returns the JSX of the page navigator
 */
const PageNavigator: React.FC<{
  pageSize: number;
  itemCount: number;
  currentPage: number;
  leftRightItems?: number;
  onChangePageHandler: (
    page: number
  ) => (event: MouseEvent<HTMLButtonElement>) => void;
}> = (props) => {
  const {
    pageSize,
    itemCount,
    currentPage,
    leftRightItems,
    onChangePageHandler,
  } = props;

  // Check, how many pages should be showed right and left to the current one
  const usedLeftRightItems = useMemo(
    () => (leftRightItems ? leftRightItems : 2),
    [leftRightItems]
  );

  // Calculate the amount of pages (Item Count / Page Size)
  const pageCount = useMemo(
    () => Math.ceil(1.0 * (itemCount / pageSize)) - 1,
    [itemCount, pageSize]
  );

  // Calculate the lowest page number to start with
  const lowestPageNumber =
    currentPage - usedLeftRightItems >= 0
      ? currentPage - usedLeftRightItems
      : 0;

  const pages: {
    label: string;
    page: number;
  }[] = [];

  // If the lowest page number is larger than 1, add "<<" items (with refers to lowestPageNumber - 1)
  if (lowestPageNumber >= 1) {
    pages.push({
      label: "<<",
      page: lowestPageNumber - 1,
    });
  }

  // Add the page numbers between the lowest page number to the current page number
  for (let page = lowestPageNumber; page >= 0 && page < currentPage; page++) {
    pages.push({
      label: `${page + 1}`,
      page: page,
    });
  }

  // Add the current page number
  pages.push({
    label: `${currentPage + 1}`,
    page: currentPage,
  });

  // Add the page numbers between the current page number and the current page number + usedLeftRightItems or the biggest page number
  for (
    let page = currentPage + 1;
    page - currentPage <= usedLeftRightItems && page <= pageCount;
    page++
  ) {
    pages.push({
      label: `${page + 1}`,
      page: page,
    });
  }

  // If the largest page number in the array is not the last page, than add the ">>" entry
  if (currentPage + usedLeftRightItems < pageCount) {
    pages.push({
      label: ">>",
      page: currentPage + usedLeftRightItems + 2,
    });
  }

  return (
    <ButtonGroup size="sm" className="mb-2">
      {pages.map((page) => (
        <Button
          key={page.page}
          variant={page.page === currentPage ? "secondary" : "light"}
          onClick={onChangePageHandler(page.page)}
        >
          {page.label}
        </Button>
      ))}
    </ButtonGroup>
  );
};

export default PageNavigator;
