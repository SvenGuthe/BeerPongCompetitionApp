import { ActionCreatorWithPayload } from "@reduxjs/toolkit";
import { useEffect } from "react";
import { Table } from "react-bootstrap";
import { useDispatch } from "react-redux";
import { tEnum } from "../../types/enum";
import { getRequest } from "../../utility/genericHTTPFunctions";

const EnumOverview: React.FC<{
    url: string,
    storeFunction: ActionCreatorWithPayload<tEnum[], string>,
    data: tEnum[] | null
}> = (props) => {

    const dispatch = useDispatch();

    const { url, storeFunction, data } = props;

    useEffect(() => {
        dispatch(getRequest(url, storeFunction));
    }, [dispatch, url, storeFunction]);

    let table;

    if (data) {
        table = <Table striped bordered hover size="sm" style={{ marginBottom: '1rem' }}>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Wert</th>
                </tr>
            </thead>
            <tbody>
                {data.map(singleData => {
                    return <tr key={singleData.id}>
                        <td>{singleData.id}</td>
                        <td>{singleData.value}</td>
                    </tr>;
                })}
            </tbody>
        </Table>;
    }

    return <>
        {table}
    </>;

};

export default EnumOverview;