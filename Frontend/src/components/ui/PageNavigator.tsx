import { MouseEvent, useEffect, useMemo, useState } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

const PageNavigator: React.FC<{
    pageSize: number,
    itemCount: number,
    startingPage?: number,
    countOfPages?: number
}> = (props) => {

    const { pageSize, itemCount, startingPage, countOfPages } = props;
    const usedCountOfPages = countOfPages ? countOfPages : 2;

    const [currentPage, setCurrentPage] = useState(startingPage ? startingPage : 1);

    const pageCount = useMemo(() => Math.ceil(1.0 * (itemCount / pageSize)), [itemCount, pageSize]);

    useEffect(() => {
        if(currentPage > pageCount) {
            setCurrentPage(pageCount);
        }
    }, [currentPage, pageCount])

    const lowestPageNumber = (currentPage - usedCountOfPages > 0) ? currentPage - usedCountOfPages : 1;

    const pages: {
        label: string,
        page: number
    }[] = [];

    const onClickHandler = (page: number) => (event: MouseEvent<HTMLButtonElement>) => {
        setCurrentPage(page);
    }

    if (lowestPageNumber >= 2) {
        pages.push({
            label: "<<",
            page: (lowestPageNumber - 1)
        });
    }

    for (let page = lowestPageNumber; page > 0 && page < currentPage; page++) {
        pages.push({
            label: `${page}`,
            page: page
        });
    }

    pages.push({
        label: `${currentPage}`,
        page: currentPage
    });

    for (let page = currentPage + 1; (page - currentPage) <= usedCountOfPages && page <= pageCount; page++) {
        pages.push({
            label: `${page}`,
            page: page
        });
    }

    if (currentPage + usedCountOfPages < pageCount) {
        pages.push({
            label: ">>",
            page: (currentPage + usedCountOfPages + 1)
        });
    }

    return <ButtonGroup className="mb-2">
        {pages.map(page =>
            <Button key={page.page} variant={page.page === currentPage ? 'secondary' : 'light'} onClick={onClickHandler(page.page)}>
                {page.label}
            </Button>
        )}
    </ButtonGroup>;

};

export default PageNavigator;