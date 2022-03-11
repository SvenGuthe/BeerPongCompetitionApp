import axios from "axios";
import { Dispatch } from "react";

export const getRequestWithID = <T,>(id: number, baseUrl: string, func: ((data: T) => void)[]) => {
    console.log(`Send ${baseUrl} [GET] Request`);
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get(`${baseUrl}/${id}`)
            .then((response) => {
                func.forEach(singleFunc => dispatch(singleFunc(response.data)));
            }).catch((error) => {
                console.log(error);
            });

        return sendRequest();
    };
};

export const getRequest = <T,>(baseUrl: string, func: ((data: T) => void)[]) => {
    console.log(`Send ${baseUrl} [GET] Request`);
    return async (dispatch: Dispatch<any>) => {
        const sendRequest = async () => await axios.get(baseUrl).then((response) => {
            func.forEach(singleFunc => dispatch(singleFunc(response.data)));
        }).catch((error) => {
            console.log(error);
        });

        return sendRequest();
    };
};