# Flight Scanner

We will build a simple flight search engine, similar to [Skyscanner](https://www.skyscanner.net), that finds how to travel between two locations with the minimum number of transfers.

### Flight Scanner

In the package `bg.sofia.uni.fmi.mjt.flightscanner` create a class `FlightScanner` with a public default constructor that implements the `FlightScannerAPI` interface:

```java
package bg.sofia.uni.fmi.mjt.flightscanner;

import bg.sofia.uni.fmi.mjt.flightscanner.airport.Airport;
import bg.sofia.uni.fmi.mjt.flightscanner.flight.Flight;

import java.util.Collection;
import java.util.List;

public interface FlightScannerAPI {

    /**
     * Adds a flight. If the same flight has already been added, does nothing.
     *
     * @param flight the flight to add.
     * @throws IllegalArgumentException if flight is null.
     */
    void add(Flight flight);

    /**
     * Adds all flights. Already-added flights are ignored.
     *
     * @param flights the flights to add.
     * @throws IllegalArgumentException if flights is null.
     */
    void addAll(Collection<Flight> flights);

    /**
     * Returns the flight path from start to destination with the minimum number of transfers.
     * If multiple such paths exist, returns any one of them.
     * Returns an empty list if the destination is unreachable.
     *
     * @param from the start airport.
     * @param to   the destination airport.
     * @throws IllegalArgumentException if from or to is null, or they are the same airport.
     */
    List<Flight> searchFlights(Airport from, Airport to);

    /**
     * Returns an unmodifiable copy of all outbound flights from the airport,
     * sorted by free seats descending.
     *
     * @param from the airport.
     * @throws IllegalArgumentException if from is null.
     */
    List<Flight> getFlightsSortedByFreeSeats(Airport from);

    /**
     * Returns an unmodifiable copy of all outbound flights from the airport,
     * sorted by destination airport ID lexicographically.
     *
     * @param from the airport.
     * @throws IllegalArgumentException if from is null.
     */
    List<Flight> getFlightsSortedByDestination(Airport from);
}
```

### Airports

Every airport has a unique ID (`String`) modelled by the `Airport` record in package `bg.sofia.uni.fmi.mjt.flightscanner.airport`. The compact constructor must validate that the ID is not null, empty, or blank; on failure throw `IllegalArgumentException`.

### Flights

A flight is modelled by the class `RegularFlight` in `bg.sofia.uni.fmi.mjt.flightscanner.flight`, implementing the `Flight` interface:

```java
public interface Flight {
    Airport getFrom();
    Airport getTo();
    void addPassenger(Passenger passenger) throws FlightCapacityExceededException;
    void addPassengers(Collection<Passenger> passengers) throws FlightCapacityExceededException;
    Collection<Passenger> getAllPassengers();
    int getFreeSeatsCount();
}
```

`RegularFlight` instances are created via the static factory `of(String flightId, Airport from, Airport to, int totalCapacity)`. Validation rules:
- `flightId` must not be null, empty, or blank → `IllegalArgumentException`
- `from` and `to` must not be null → `IllegalArgumentException`
- `totalCapacity` must not be negative → `IllegalArgumentException`
- `from` and `to` must not be the same airport → `InvalidFlightException` (runtime)

### Passengers

A passenger is modelled by the `Passenger` record in `bg.sofia.uni.fmi.mjt.flightscanner.passenger` with components `(String id, String name, Gender gender)`, and `Gender` is:

```java
public enum Gender {
    MALE, FEMALE, OTHER
}
```

## Hints

> If we model airports as graph vertices and flights as directed edges, the problem reduces to the classic shortest-path search in an unweighted graph, solved with [Breadth-First Search (BFS)](https://en.wikipedia.org/wiki/Breadth-first_search).

> Use an *adjacency list* representation: for each airport, store the set of outbound flights. This makes BFS neighbourhood lookups efficient.

### Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.flightscanner
    ├── airport
    │      └── Airport.java
    ├── exception
    │      ├── FlightCapacityExceededException.java
    │      └── InvalidFlightException.java
    ├── flight
    │      ├── Flight.java
    │      └── RegularFlight.java
    ├── passenger
    │      ├── Gender.java
    │      └── Passenger.java
    ├── FlightScanner.java
    └── FlightScannerAPI.java
```

### Notes

- Do not modify the interfaces provided in the problem statement.
