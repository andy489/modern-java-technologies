# Object-Oriented Programming with Java (Part I)

## AirBnB :hotel:

You will implement a platform for searching and booking accommodation, similar to [AirBnB](https://www.airbnb.com/).

### `Airbnb`

In the package `bg.sofia.uni.fmi.mjt.airbnb`, create a class `Airbnb` with a public constructor `Airbnb(Bookable[] accommodations)` that implements the following interface:

```java
package bg.sofia.uni.fmi.mjt.airbnb;

public interface AirbnbAPI {

    /**
     * Finds an accommodation by ID.
     *
     * @param id the unique ID of the bookable
     * @return the bookable with the specified id (IDs are case-insensitive).
     *         Returns null if id is null, blank, or no match is found.
     */
    Bookable findAccommodationById(String id);

    /**
     * Estimates the total revenue of all booked accommodations.
     *
     * @return the sum of total prices of stay for all booked accommodations.
     */
    double estimateTotalRevenue();

    /**
     * @return the number of booked accommodations.
     */
    long countBookings();

    /**
     * Filters the accommodations by the given criteria.
     *
     * @param criteria the criteria to apply
     * @return an array of accommodations matching all specified criteria
     */
    Bookable[] filterAccommodations(Criterion... criteria);

}
```

### Accommodations

The platform supports three accommodation types: hotels, apartments, and villas, implemented by the concrete classes `Hotel`, `Apartment`, and `Villa`, all implementing the `Bookable` interface:

```java
package bg.sofia.uni.fmi.mjt.airbnb.accommodation;

public interface Bookable {

    /**
     * @return the unique ID of the Bookable.
     * The ID is a String with a prefix of the first three uppercase letters of the
     * accommodation type, a dash, and a sequential number counting how many instances
     * of that type have been created (e.g. "HOT-45", "APA-12", "VIL-7").
     * Counting starts from 0 and is separate per type.
     */
    String getId();

    /** @return the location of this bookable. */
    Location getLocation();

    /** @return true if the accommodation is currently booked. */
    boolean isBooked();

    /**
     * Books the accommodation for the given date interval.
     *
     * @param checkIn  check-in date/time
     * @param checkOut check-out date/time
     * @return true if booked successfully.
     *         Returns false if already booked, if checkIn or checkOut is null,
     *         if checkIn is in the past, or if checkIn is not strictly before checkOut.
     */
    boolean book(LocalDateTime checkIn, LocalDateTime checkOut);

    /**
     * @return the total price of the stay if booked (nights × price per night), or 0.0 if not booked.
     */
    double getTotalPriceOfStay();

    /** @return the price per night for this accommodation. */
    double getPricePerNight();

}
```

All three `Bookable` implementations must have a public constructor `(Location location, double pricePerNight)`.

### Location

Model positions with a class `Location` with a public constructor `(double x, double y)` representing a pair of Cartesian coordinates.

### Filter Criteria

Accommodations can be filtered by two criteria — location and price — implemented by `LocationCriterion` and `PriceCriterion`, both implementing:

```java
package bg.sofia.uni.fmi.mjt.airbnb.filter;

public interface Criterion {

    /**
     * @return true if the bookable matches the criterion. Returns false if bookable is null.
     */
    boolean check(Bookable bookable);

}
```

`LocationCriterion` must have a public constructor `(Location currentLocation, double maxDistance)`.
`PriceCriterion` must have a public constructor `(double minPrice, double maxPrice)`.

### Hint

:point_right: For date and time operations, use the [`java.time`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/package-summary.html) API — in particular [`LocalDateTime`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/LocalDateTime.html) and its methods.

### Package Structure

Keep all package names exactly as specified — the solution will be auto-tested.

```
src
└─ bg/sofia/uni/fmi/mjt/airbnb
    ├─── accommodation/
    │       ├─── location/
    │       │       └─ Location.java
    │       ├─ Apartment.java
    │       ├─ Bookable.java
    │       ├─ Hotel.java
    │       ├─ Villa.java
    │       └─ (...)
    ├─── filter/
    │       ├─ Criterion.java
    │       ├─ LocationCriterion.java
    │       ├─ PriceCriterion.java
    │       └─ (...)
    ├─ Airbnb.java
    ├─ AirbnbAPI.java
    └─ (...)
```

### :warning: Notes

- Data structures other than arrays are **not allowed**. Solve the problem using only the Java knowledge covered in the course so far plus the APIs mentioned above.
