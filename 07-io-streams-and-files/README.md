# I/O Streams & Files

## Topics

### I/O Streams
- What is an I/O stream? Data sources and destinations
- Abstract root classes — `InputStream`, `OutputStream`, `Reader`, `Writer`
- Byte streams vs. character streams
- Wrapping streams into higher-level ones
- `FileInputStream` / `FileOutputStream` — reading and writing bytes
- `DataInputStream` / `DataOutputStream` — primitive type I/O
- `ObjectOutputStream` / `ObjectInputStream` — serialisation
- `java.io.Serializable`
- `BufferedReader` / `BufferedWriter` — efficient character I/O
- Try-with-resources
- System streams — `System.in`, `System.out`, `System.err`
- `java.util.Scanner` and `java.util.Formatter`

### File System (NIO.2)
- `java.nio.file.Path` — creation, separators, conversions, comparison
- Legacy `java.io.File`
- `java.nio.file.Files` — metadata, checks, delete, copy, move, create directories
- Iterating directories — glob patterns, wildcards
- Reading / writing entire files
- Walking a directory tree — `Files.walk`, `Files.walkFileTree`
- File reading performance comparison
- Temporary files, ZIP streams, encrypted streams
- Unit-testing file-based code
- Clean code principles for I/O code

## Lecture

[slides.md](lecture/slides.md)

## Lab

[lab/](lab/)
