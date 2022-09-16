import { MouseEvent, useEffect, useMemo, useState } from "react";
import { Col, Container, Row, Table } from "react-bootstrap";
import PageNavigator from "./PageNavigator";
import PageSize from "./PageSize";
import Search from "./Search";

/**
 * Component to display a table with entries, an search filter and buttons to navigate through pages and to change the page size
 * @param props the function what happend if the page, the size or the search input field was changed
 *              seconds how long we should wait after typing something in the search field to search for the entry
 *              the count of all available items
 *              the array with possible page sizes
 * @returns
 */
const TableWithSearchAndFilter: React.FC<{
  changeFunction: (page: number, size: number, search: string) => void;
  waitTillChangeHandlerStarts?: number;
  itemCount: number;
  pageSizes: number[];
}> = (props) => {
  const pageSizes = props.pageSizes;
  const changeFunction = props.changeFunction;

  const [pageSize, setPageSize] = useState(pageSizes[0]);
  const [currentPage, setCurrentPage] = useState(0);
  const [searchValue, setSearchValue] = useState("");
  const [initialized, setInitialized] = useState(true);

  useEffect(() => {
    if (!initialized) {
      changeFunction(currentPage, pageSize, searchValue);
    }
  }, [initialized, pageSize, currentPage, searchValue, changeFunction]);

  let timer: ReturnType<typeof setTimeout>;
  const time = useMemo(
    () =>
      props.waitTillChangeHandlerStarts
        ? props.waitTillChangeHandlerStarts
        : 1000,
    [props.waitTillChangeHandlerStarts]
  );

  const onChangeSearchHandler = (
    event: React.ChangeEvent<HTMLInputElement>
  ) => {
    clearTimeout(timer);
    timer = setTimeout(() => {
      setSearchValue(event.target.value);
      setCurrentPage(0);
      setInitialized(false);
    }, time);
  };

  const onChangePageHandler =
    (page: number) => (event: MouseEvent<HTMLButtonElement>) => {
      setInitialized(false);
      setCurrentPage(page);
    };

  const onChangePageSizeHandler =
    (pageSize: number) => (event: MouseEvent<HTMLButtonElement>) => {
      setInitialized(false);
      const pageCount = Math.ceil(1.0 * (props.itemCount / pageSize)) - 1;

      if (currentPage > pageCount) {
        setCurrentPage(pageCount);
      }
      setPageSize(pageSize);
    };

  return (
    <>
      <Container style={{ paddingLeft: "0px", paddingRight: "0px" }}>
        <Row>
          <Col md={{ span: 3, offset: 9 }}>
            <Search onChangeSearchHandler={onChangeSearchHandler} />
          </Col>
        </Row>
      </Container>
      <Table striped bordered hover size="sm">
        {props.children}
      </Table>
      {props.itemCount > 0 && (
        <Container style={{ paddingLeft: "0px", paddingRight: "0px" }}>
          <Row>
            <Col style={{ textAlign: "left" }}>
              <PageNavigator
                pageSize={pageSize}
                itemCount={props.itemCount}
                currentPage={currentPage}
                onChangePageHandler={onChangePageHandler}
              />
            </Col>
            <Col style={{ textAlign: "right" }}>
              <PageSize
                currentPageSize={pageSize}
                onChangePageSizeHandler={onChangePageSizeHandler}
                pageSizes={pageSizes}
              />
            </Col>
          </Row>
        </Container>
      )}
    </>
  );
};

export default TableWithSearchAndFilter;
