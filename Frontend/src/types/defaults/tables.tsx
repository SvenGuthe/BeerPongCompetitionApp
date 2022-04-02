import { ReactElement } from "react"

export type tAdditionalAttribute = {
    id: string,
    value: any,
    reactElement?: ReactElement
}

export type tAdditionalAttributes = {
    additionalAttributes?: tAdditionalAttribute[]
}