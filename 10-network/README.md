# Network Programming

## Topics

### Concurrency Utilities (continued from Threads)
- `Runnable` vs. `Callable`, `Future<V>`
- Thread pools — `Executor`, `ExecutorService`, `Executors` API
- Thread-safe collections — synchronised wrappers, `CopyOnWriteArrayList`, `ConcurrentHashMap`, `BlockingQueue`

### Networking Fundamentals
- Client-server and peer-to-peer models
- OSI model and network protocols
- IP addresses, ports, `InetAddress`
- UDP vs. TCP — characteristics and trade-offs

### java.net — Blocking I/O
- `ServerSocket` and `Socket`
- TCP communication flow — blocking architecture
- Multithreaded server architecture

### java.nio — Non-Blocking I/O
- Core abstractions — channels, buffers, selectors
- `ByteBuffer` — direct vs. indirect, flip/compact lifecycle
- `ServerSocketChannel`, `SocketChannel`
- `Selector` and `SelectionKey` — registration, interest ops, ready ops
- Non-blocking read/write patterns
- Sending and receiving data step-by-step
- IO vs. NIO performance comparison

## Lecture

[slides.md](lecture/slides.md)

## Lab

[lab/](lab/)
