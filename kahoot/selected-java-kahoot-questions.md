# Selected Java Kahoot Questions

---

**1.** Which of the following statements about atomic data types is **NOT** true?

- **a) They solve read-modify-write race conditions, but NOT check-then-act race conditions** ✓
- b) They use special CPU instructions ("compare-and-swap", CAS)
- c) They guarantee thread safety for shared mutable state: single variable
- d) They provide functions implementing certain composite operations atomically

---

**2.** `wait()`, `notify()` and `notifyAll()` are methods of which class/interface?

- a) Thread
- **b) Object** ✓
- c) Runnable
- d) Thread.State

---

**3.** What can happen if two threads try to write to the same resource without synchronization?

- a) Accessing the same resource from multiple threads is not allowed in Java
- b) A race condition will never occur
- **c) A race condition may occur** ✓
- d) A deadlock may occur

---

**4.** Which `Thread` method can make the main thread wait for all other threads to finish?

- a) wait()
- **b) join()** ✓
- c) sleep()
- d) stop()

---

**5.** How many tasks can run in parallel on a machine with 4 processors, each with 2 cores supporting hyper-threading?

- a) 16
- **b) 8** ✓
- c) 4
- d) Unlimited

---

**6.** Which of the following principles is **NOT** part of the REST architecture?

- a) REST is a client-server architecture
- b) REST offers caching capabilities
- **c) REST is stateful** ✓
- d) REST is a layered system

---

**7.** Every resource in REST is defined by?

- a) Socket
- **b) URI (Uniform Resource Identifier)** ✓
- c) A unique ID
- d) None of the above

---

**8.** Which of the following characteristics does **NOT** apply to JSON?

- **a) Difficult to parse** ✓
- b) Concise and intuitive
- c) Readable by both humans and machines
- d) Language-independent specification

---

**9.** Which of the following data types exists in JSON?

- **a) Boolean** ✓
- b) Float
- c) List
- **d) Number** ✓

---

**10.** Which of the following is **NOT** true about the `HttpClient` included in the JDK since Java 11?

- **a) It supports only HTTP/1** ✓
- b) It supports both synchronous and asynchronous requests
- c) It consists of several classes and interfaces in the `java.net.http` package
- d) All three answers are correct

---

**11.** An `HttpClient` instance in Java is created with `HttpClient client = new HttpClient()`?

- a) True
- **b) False** ✓

---

**12.** Which of the following statements about an `HttpRequest` instance are true?

- a) It is mutable and can be sent multiple times
- **b) It is created via a builder method** ✓
- c) It is immutable and can only be sent once
- **d) The default HTTP method is GET() and can be omitted** ✓

---

**13.** What does the method `sendAsync(HttpRequest, BodyHandler)` of `HttpClient` return?

- a) Callable
- b) Optional
- **c) CompletableFuture** ✓
- d) None of the above

---

**14.** Which of the following array declarations/initializations is **NOT** valid in Java?

- a) `int[] a;`
- b) `int b[];`
- c) `int[] c[] = {{1}};`
- **d) `int d[7];`** ✓

---

**15.** How many distinct String instances will be created after executing the following code?

```java
String s1 = "FMI";
String s2 = "FMI";
String s3 = new String("MJT");
```

- a) 1
- **b) 2** ✓
- c) 3
- d) 4

---

**16.** Which of the following is **NOT** a valid modifier for an interface method in Java?

- a) private
- b) abstract
- **c) final** ✓
- d) static

---

**17.** Is the following statement true: `null instanceof String`?

- a) Yes
- **b) No** ✓

---

**18.** Would we use a `Collection<? super Number>` when we want to **put** elements but not **get** them?

- **a) Yes** ✓
- b) No

---

**19.** How many abstract methods does the `java.io.Serializable` interface contain?

- **a) 0** ✓
- b) 1
- c) 2
- d) 3

---

**20.** If I/O stream A wraps stream B, does closing A automatically close B?

- **a) Yes** ✓
- b) No

---

**21.** Which of the following statements about `java.util.Optional` are true?

- **a) It is used to avoid null checks or NullPointerException** ✓
- b) It is used to define methods with a variable number of arguments
- c) It represents a container for a value that will be available in the future
- **d) Its instances are returned by some Stream API methods** ✓

---

**22.** How can we make a thread a daemon in Java?

- **a) By calling `setDaemon(true)`** ✓
- b) By calling `setPriority(10)`
- **c) By starting it from another daemon thread** ✓
- d) When the thread that started it finishes execution

---

**23.** Which of the following statements about atomic operations in Java are true?

- **a) They allow avoiding synchronization, making them very fast** ✓
- **b) They are used to prevent race conditions** ✓
- c) They must always be executed in a synchronized section
- d) Two consecutive atomic operations are also an atomic operation

---

**24.** What are the main classes from the `java.net` API for network communication?

- a) SocketChannel
- **b) ServerSocket** ✓
- **c) Socket** ✓
- d) ByteBuffer

---

**25.** Which of the following `java.nio` operations are blocking in non-blocking mode on all channels?

- a) `ServerSocketChannel.accept()`
- b) `SocketChannel.read(ByteBuffer buf)`
- **c) `Selector.select()`** ✓
- d) `Selector.selectNow()`

---

**26.** How is an instance of `java.net.http.HttpResponse` created?

- a) Via a constructor
- b) Via a factory method
- c) Via a builder method
- **d) As a result of calling the `send` method on HttpClient** ✓

---

**26b.** Which of the following are valid data types in JSON?

- **a) Boolean** ✓
- b) Function
- c) Undefined
- **d) String** ✓

---

**27.** Which of the following is true about the S.O.L.I.D. **Interface Segregation** principle?

- a) In Java 15 it is achieved via sealed interfaces
- **b) A client should not implement an interface it does not use** ✓
- **c) Several specific interfaces are preferred over one general one** ✓
- d) In Java it is achieved via access modifiers
