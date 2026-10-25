# Object-Oriented Programming with Java (Part I)

## Rental Service :car: :bike:

Shared vehicles are an increasingly popular way to get around modern cities. You will build an application for booking cars, scooters, and bicycles.

### `RentalService`

In the package `bg.sofia.uni.fmi.mjt.rentalservice.service`, create a class `RentalService` with a public constructor `RentalService(Vehicle[] vehicles)` that implements the following interface:

```java
package bg.sofia.uni.fmi.mjt.rentalservice.service;

public interface RentalServiceAPI {

    /**
     * Rents an available vehicle until a certain point in time
     *
     * @param vehicle a valid vehicle registered with the RentalService
     * @param until point in time until which the vehicle will be rented.
     *              The vehicle will be available for next booking after @until
     * @return the price of the trip (starting now).
     *         If the vehicle does not exist or is already booked, return -1.0
     */
    double rentUntil(Vehicle vehicle, LocalDateTime until);

    /**
     * Returns the nearest available vehicle of the specified type within a certain radius
     * of the given location
     *
     * @param type the type of the vehicle
     * @param location current location
     * @param maxDistance non-negative maximum distance to the vehicle
     * @return the nearest available vehicle of the specified @type within @maxDistance, null otherwise.
     *         If there are two or more equidistant nearest vehicles, return any of them
     */
    Vehicle findNearestAvailableVehicleInRadius(String type, Location location, double maxDistance);

}
```

### Vehicles

The application supports three vehicle types: cars, scooters, and bicycles, implemented by the concrete classes `Car`, `Scooter`, and `Bicycle`, all implementing the `Vehicle` interface:

```java
package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import java.time.LocalDateTime;

public interface Vehicle {

    /**
     * @return the price per minute for booking this vehicle.
     *         Prices for the different types are:
     *             "CAR"      -> 0.50
     *             "SCOOTER"  -> 0.30
     *             "BICYCLE"  -> 0.20
     */
    double getPricePerMinute();

    /**
     * @return the type of this vehicle. Valid types are: "CAR", "SCOOTER" or "BICYCLE"
     */
    String getType();

    /**
     * @return the ID of this vehicle. The ID must not be blank or null
     */
    String getId();

    /**
     * @return the location of this vehicle. The location must not be null
     */
    Location getLocation();

    /**
     * @return the end of the reservation period if the vehicle is currently booked,
     *         otherwise returns the current time
     */
    LocalDateTime getEndOfReservationPeriod();

    /**
     * @param until time until the vehicle is booked
     */
    void setEndOfReservationPeriod(LocalDateTime until);

}
```

All three `Vehicle` implementations must have a public constructor `(String id, Location location)`.

### Location

Treat the city as a plane and model positions using the class `Location`, representing a pair of Cartesian coordinates.

```java
package bg.sofia.uni.fmi.mjt.rentalservice.location;

public class Location {

    private final double x;
    private final double y;

    public Location(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

}
```

### Hint

:point_right: For date and time operations, use the [`java.time`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/package-summary.html) API — in particular [`LocalDateTime`](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/time/LocalDateTime.html) and its methods.

### Package Structure

Keep all package names exactly as specified — the solution will be auto-tested.

```
src
│
└─ bg/sofia/uni/fmi/mjt/rentalservice
    ├─── location/
    │       └─ Location.java
    ├─── service/
    │       ├─ RentalService.java
    │       ├─ RentalServiceAPI.java
    │       └─ (...)
    ├─── vehicle/
    │       ├─ Bicycle.java
    │       ├─ Car.java
    │       ├─ Scooter.java
    │       ├─ Vehicle.java
    │       └─ (...)
    └─ (...)
```

### :warning: Notes

- Data structures other than arrays are **not allowed**. Solve the problem using only the Java knowledge covered in the course so far plus the APIs mentioned above.
