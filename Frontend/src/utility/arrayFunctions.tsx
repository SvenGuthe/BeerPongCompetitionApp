import { tPrivilege } from "../types/user";

export function removePriviligeDuplicates(arr: tPrivilege[] | undefined) {
    if (arr) {
        return arr.filter((value, index, self) =>
        index === self.findIndex((t) => (
          t.privilegeId === value.privilegeId
        ))
      )
    } else {
        return null;
    }
}