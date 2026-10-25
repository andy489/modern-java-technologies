## Canonical Directory Path

### Task

In Unix-style operating systems, `.` denotes the current directory, `..` refers to the parent directory, and any sequence of slashes (e.g. `//`) is treated as a single slash (`/`). Any other sequence of dots (e.g. `...`) is treated as a file or directory name.

A _canonical_ path is one that:
- starts with a single slash `/`
- separates each pair of directories with a single slash `/`
- does not end with `/`
- contains only the directories on the path from root to the target — no `.` or `..`

Create a public class `PathUtils` with the method:

```java
public static String getCanonicalPath(String path)
```

that returns the canonical equivalent of the given path.

### Examples

| Call                                  | Result        |
| :------------------------------------ | :------------ |
| `getCanonicalPath("/home/")`          | `"/home"`     |
| `getCanonicalPath("/../")`            | `"/"`         |
| `getCanonicalPath("/home//foo/")`     | `"/home/foo"` |
| `getCanonicalPath("/a/./b/../../c/")` | `"/c"`        |

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course. In particular, filesystem APIs are not permitted.
