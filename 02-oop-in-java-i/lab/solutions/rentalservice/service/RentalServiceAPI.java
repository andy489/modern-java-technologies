package bg.sofia.uni.fmi.mjt.rentalservice.service;

import java.time.LocalDateTime;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;
import bg.sofia.uni.fmi.mjt.rentalservice.vehicle.Vehicle;

public interface RentalServiceAPI {

    /**
     * Rents an available vehicle until a certain point in time.
     *
     * @param vehicle a valid vehicle registered with the RentalService
     * @param until point in time until which the vehicle will be rented;
     *              the vehicle will be available for next booking after this time
     * @return the price of the trip (starting now),
     *         or -1.0 if the vehicle does not exist or is already booked
     */
    double rentUntil(Vehicle vehicle, LocalDateTime until);

    /**
     * Returns the nearest available vehicle of the specified type within a certain radius.
     *
     * @param type the type of the vehicle
     * @param location current location
     * @param maxDistance non-negative maximum distance to the vehicle
     * @return the nearest available vehicle of the specified type within maxDistance,
     *         or null if none found; if two vehicles are equidistant, returns either
     */
    Vehicle findNearestAvailableVehicleInRadius(String type, Location location, double maxDistance);

}
