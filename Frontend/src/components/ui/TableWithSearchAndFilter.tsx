import { MouseEvent, useState } from "react";
import { Col, Container, Row, Table } from "react-bootstrap";
import PageNavigator from "./PageNavigator";
import PageSize from "./PageSize";
import Search from "./Search";

const TableWithSearchAndFilter: React.FC<{
    onChangeFunction: (search: string) => void,
    // ToDo: Implement the onChangeFunction for the PageNavigator
    waitTillChangeHandlerStarts?: number,
    pageSize: number,
    itemCount: number,
    pageSizes?: number[]
}> = (props) => {

    const pageSizes = props.pageSizes;
    let usedPageSizes: number[] = [];

    if(pageSizes && pageSizes.length < 0) {
        usedPageSizes = pageSizes;
    } else {
        usedPageSizes = [5, 10, 20];
    }

    const [pageSize, setPageSize] = useState(usedPageSizes[0]);

    const onClickHandler = (pageSize: number) => (event: MouseEvent<HTMLButtonElement>) => {
        setPageSize(pageSize);
    }

    return <>
        <Search
            onChangeFunction={props.onChangeFunction}
            waitTillChangeHandlerStarts={props.waitTillChangeHandlerStarts}
        />
        <Table striped bordered hover size="sm">
            {props.children}
        </Table>
        <Container style={{ paddingLeft: '0px', paddingRight: '0px' }}>
            <Row>
                <Col style={{ textAlign: 'left' }}>
                    <PageNavigator pageSize={pageSize} itemCount={props.itemCount} />
                </Col>
                <Col style={{ textAlign: 'right' }}>
                    <PageSize currentPageSize={pageSize} onClickHandler={onClickHandler} pageSizes={usedPageSizes} />
                </Col>
            </Row>
        </Container>
    </>;

};

export default TableWithSearchAndFilter;