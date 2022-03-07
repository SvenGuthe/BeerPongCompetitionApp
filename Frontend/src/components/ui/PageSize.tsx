import { MouseEvent } from "react";
import { Button, ButtonGroup } from "react-bootstrap";

const PageSize: React.FC<{
    pageSizes: number[],
    onClickHandler: (pageSize: number) => (event: MouseEvent<HTMLButtonElement>) => void,
    currentPageSize: number
}> = (props) => {

    const onClickHandler = props.onClickHandler;

    return <ButtonGroup className="mb-2">
        {props.pageSizes.map(pageSize =>
            <Button key={pageSize} variant={pageSize === props.currentPageSize ? "secondary" : "light"} onClick={onClickHandler(pageSize)}>
                {pageSize}
            </Button>
        )}
    </ButtonGroup>;
}

export default PageSize;