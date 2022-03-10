export interface tID {
    id: number
}

export type tPaginationDTO<LIST_TYPE extends tID> = {
    size: number,
    pages: number,
    data: LIST_TYPE[]
}