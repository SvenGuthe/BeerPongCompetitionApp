import { MouseEvent, useMemo } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

const PageNavigator: React.FC<{
    pageSize: number,
    itemCount: number,
    currentPage: number,
    leftRightItems?: number,
    onChangePageHandler: (page: number) => (event: MouseEvent<HTMLButtonElement>) => void
}> = (props) => {

    const { pageSize, itemCount, currentPage, leftRightItems, onChangePageHandler } = props;

    const usedLeftRightItems = useMemo(() => leftRightItems ? leftRightItems : 2, [leftRightItems]);

    const pageCount = useMemo(() => Math.ceil(1.0 * (itemCount / pageSize)) - 1, [itemCount, pageSize]);

    const lowestPageNumber = (currentPage - usedLeftRightItems >= 0) ? currentPage - usedLeftRightItems : 0;

    const pages: {
        label: string,
        page: number
    }[] = [];    

    if (lowestPageNumber >= 1) {
        pages.push({
            label: "<<",
            page: (lowestPageNumber - 1)
        });
    }

    for (let page = lowestPageNumber; page >= 0 && page < currentPage; page++) {
        pages.push({
            label: `${page + 1}`,
            page: page
        });
    }

    pages.push({
        label: `${currentPage + 1}`,
        page: currentPage
    });

    for (let page = currentPage + 1; (page - currentPage) <= usedLeftRightItems && page <= pageCount; page++) {
        pages.push({
            label: `${page + 1}`,
            page: page
        });
    }

    if (currentPage + usedLeftRightItems < pageCount) {
        pages.push({
            label: ">>",
            page: (currentPage + usedLeftRightItems + 2)
        });
    }

    return <ButtonGroup size="sm" className="mb-2">
        {pages.map(page =>
            <Button key={page.page} variant={page.page === currentPage ? 'secondary' : 'light'} onClick={onChangePageHandler(page.page)}>
                {page.label}
            </Button>
        )}
    </ButtonGroup>;

};

export default PageNavigator;