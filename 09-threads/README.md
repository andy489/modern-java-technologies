# Threads

## Topics

### Concurrency Fundamentals
- Processes vs. threads — units of concurrency
- A brief history of concurrency
- Hyper-threading and multi-core processors
- Concurrent vs. parallel execution
- Benefits and drawbacks of multithreading

### Thread Management
- Creating threads — `Thread`, `Runnable`, lambdas
- Starting, stopping, and joining threads
- Thread naming, groups, and priorities
- Daemon threads vs. standard threads
- Thread state — `Thread.State`

### Thread Safety
- Race conditions — read-modify-write and check-then-act
- Atomic data types — `AtomicInteger`, `AtomicReference`, CAS
- Synchronised sections and methods — the monitor model
- Reentrant locks, dedicated monitor objects, class-level monitors
- Thread-safe objects by design
- Compound operations
- Deadlock — causes and prevention

### Inter-Thread Communication
- Busy wait and why to avoid it
- `wait()`, `notify()`, `notifyAll()` — the guarded wait pattern
- Interrupting a thread
- Virtual threads (Java 21 preview)

## Lecture

[slides.md](lecture/slides.md)

## Lab

[lab/](lab/)
