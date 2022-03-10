import { tID } from "../types/defaults/generics";

export const removeDuplicates = <T extends tID>(arr: T[] | undefined): T[] | null => {
    if (arr) {
        return arr.filter((value, index, self) =>
        index === self.findIndex((t) => (
          t.id === value.id
        ))
      )
    } else {
        return null;
    }
}