# Unit Testing and Mocking

In professional software development, it is very common to not only write code from scratch, but also to maintain, fix, or write tests for existing code.

The goal of this exercise is to create and run JUnit tests against an existing implementation.

## Generic In-Memory Cache :package:

In-memory caching is a widely used technique for improving the performance of algorithms that frequently access data stored on slow devices.
We will build our own implementation of a generic cache.

Access to cached data is orders of magnitude faster than going to the backing store, but a cache has a fixed capacity that cannot be exceeded. For this reason, every cache has an *eviction policy* — a rule that determines which elements are removed from the cache when a new element needs to be added and the cache is full.

### Current Implementation

In [./resources](./resources) you will find two implementations of the `Cache` interface, differing in their eviction policy:

- [Least-Recently Used (LRU)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least_recently_used_(LRU))
- [Least-Frequently Used (LFU)](https://en.wikipedia.org/wiki/Cache_replacement_policies#Least-frequently_used_(LFU))

A concrete implementation of the `Cache` interface using a chosen eviction policy is created via the following static methods of the `CacheFactory` interface:

```java
static <K, V> Cache<K, V> getInstance(Storage<K, V> storage, int capacity, EvictionPolicy policy);
static <K, V> Cache<K, V> getInstance(Storage<K, V> storage, EvictionPolicy policy);
```

where `EvictionPolicy` is an enum, and `Storage<K, V>` is an interface for the backing store (e.g. a database, file system, etc.) behind our cache. If a value is not found for a given key, we attempt to retrieve it from the storage; if successful, we add it to the cache and return it.

![cache-storage interaction](./cache.svg)

#### Implementation notes

:point_right: For the Least-Frequently Used implementation:
- "usage" means any invocation of `get` or `set` for a given element.
- when `set` is called for an already-existing key (with a new or the same value), the usage count for that element is incremented by 1, not reset to zero.
- if two or more elements share the same (minimum) usage count when a new element must be added to a full cache, one of them is evicted at random.

#### Important:

:warning: The implementation contains some bugs that you need to find and fix in the process of writing tests. To make the process effective, first write a test for a scenario, then fix the bug you discovered with it.
:warning: No implementation of the `Storage` interface is provided — instead, use *Mockito* in your unit tests to mock the storage used by the cache. Alternatively, you may write a simple stub for the storage.

Your project must have the following structure:

```
src
└─ bg/sofia/uni/fmi/mjt/cache/
    ├─ factory/
    |    ├─ CacheFactory.java
    |    └─ EvictionPolicy.java
    ├─ storage/
    |    └─ Storage.java
    ├─ exception/
    |    └─ ItemNotFound.java
    |
    ├─ CacheBase.java
    ├─ Cache.java
    ├─ LeastFrequentlyUsedCache.java
    └─ LeastRecentlyUsedCache.java
test
└─ bg/sofia/uni/fmi/mjt/cache/
    └─ (...)
```

Good luck, and don't hesitate to ask questions! :star:
