import { tAdditionalAttribute } from "./tables";

export interface tID {
  id: number;
}

export interface tEnum extends tID {
  value: string;
  additionalAttributes?: tAdditionalAttribute[];
}

export type tPaginationDTO<LIST_TYPE extends tID> = {
  size: number;
  pages: number;
  data: LIST_TYPE[];
};
