import { tID } from "../types/defaults/generics";

/**
 * Function to remove duplicates in an array
 * @param arr the array
 * @returns a set of data
 */
export const removeDuplicates = <T extends tID>(
  arr: T[] | undefined
): T[] | null => {
  if (arr) {
    return arr.filter(
      (value, index, self) => index === self.findIndex((t) => t.id === value.id)
    );
  } else {
    return null;
  }
};
