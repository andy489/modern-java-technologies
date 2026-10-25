# HTTP, REST & JSON

## Topics

### HTTP
- How the internet works
- HTTP characteristics — stateless, request/response, methods, headers
- HTTP request structure — start line, headers, body
- Core HTTP methods — GET, POST, PUT, PATCH, DELETE, HEAD, OPTIONS
- HTTP response structure — status line, headers, body
- HTTP status codes — 1xx, 2xx, 3xx, 4xx, 5xx
- Common HTTP headers — general, request, response, entity, User-Agent
- HTTP sessions and authentication mechanisms
- HTTPS
- HTTP/2 — multiplexing, response time improvement, current adoption

### REST
- REST architecture principles — client-server, stateless, cacheable, uniform interface, layered
- Resources and URIs
- Resource representations and content negotiation
- REST API design examples

### JSON
- JSON characteristics — concise, human-readable, language-independent
- JSON data types — string, number, boolean, null, object, array
- Java libraries for JSON — Google Gson
- `gson.toJson()` / `gson.fromJson()` — serialisation and deserialisation

### Java HTTP Client (Java 11+)
- `HttpClient` — builder, configuration
- `HttpRequest` — builder, `BodyPublisher`
- `HttpResponse<T>` — `BodyHandler`, `BodySubscriber`
- Synchronous (`send`) and asynchronous (`sendAsync`) requests
- HTTP/2 support

## Lecture

[slides.md](lecture/slides.md)

## Lab

[lab/](lab/)
