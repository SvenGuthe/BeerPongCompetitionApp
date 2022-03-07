import { useEffect, useState } from "react";
import { Form } from "react-bootstrap";

const Search: React.FC<{
    onChangeFunction: (search: string) => void,
    waitTillChangeHandlerStarts?: number
}> = (props) => {

    const { onChangeFunction } = props;
    const [searchValue, setSearchValue] = useState("");

    let timer: ReturnType<typeof setTimeout>;

    const time = props.waitTillChangeHandlerStarts ? props.waitTillChangeHandlerStarts : 1000;

    const onChangeHandler = (event: React.ChangeEvent<HTMLInputElement>) => {

        clearTimeout(timer);

        timer = setTimeout(() => {
            setSearchValue(event.target.value);
        }, time);

    }

    useEffect(() => {
        onChangeFunction(searchValue);
    }, [searchValue, onChangeFunction]);

    return <Form.Group className="mb-3" controlId="search">
        <Form.Control type="text" placeholder="Search ..." onChange={onChangeHandler} />
    </Form.Group>;

}

export default Search;