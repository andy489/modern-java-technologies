## Data Center

### Task

We have a data center map represented as an `m × n` matrix of integers containing only zeros and ones, where `1` means a server is present and `0` means it is not. Two servers _communicate_ if they are in the same row or the same column.

Create a public class `DataCenter` with the method:

```java
public static int getCommunicatingServersCount(int[][] map)
```

that returns the number of servers that communicate with at least one other server.

### Examples

<img src="https://github.com/fmi/java-course/blob/master/01-intro-to-java/lecture/images/communicating-servers.png" alt="Communicating servers">

| Call                                                                                                    | Result |
| :------------------------------------------------------------------------------------------------------ | :----- |
| `getCommunicatingServersCount(new int[][]{ {1, 0}, {0, 1} })`                                          | `0`    |
| `getCommunicatingServersCount(new int[][]{ {1, 0}, {1, 1} })`                                          | `3`    |
| `getCommunicatingServersCount(new int[][]{ {1, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 1, 0}, {0, 0, 0, 1} })` | `4`    |

### Notes

- Using data structures other than arrays is **not allowed**. The task must be solved using only the knowledge from the first lecture of the course.
