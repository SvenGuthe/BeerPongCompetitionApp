export type tEnum = {
    id: number,
    value: string
}

export type tPaginationDTO = {
    size: number,
    pages: number,
    data: tEnum[]
}