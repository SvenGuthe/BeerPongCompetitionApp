import { MouseEvent } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

const PageSize: React.FC<{
    pageSizes: number[],
    onChangePageSizeHandler: (pageSize: number) => (event: MouseEvent<HTMLButtonElement>) => void,
    currentPageSize: number
}> = (props) => {

    const onChangePageSizeHandler = props.onChangePageSizeHandler;

    return <ButtonGroup size="sm" className="mb-2">
        {props.pageSizes.map(pageSize =>
            <Button key={pageSize} variant={pageSize === props.currentPageSize ? "secondary" : "light"} onClick={onChangePageSizeHandler(pageSize)}>
                {pageSize}
            </Button>
        )}
    </ButtonGroup>;
}

export default PageSize;