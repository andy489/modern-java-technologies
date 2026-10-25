package bg.sofia.uni.fmi.mjt.rentalservice.service;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;
import bg.sofia.uni.fmi.mjt.rentalservice.vehicle.Vehicle;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class RentalService implements RentalServiceAPI {

    private static final double SECONDS_PER_MINUTE = 60.0;

    private final Vehicle[] vehicles;

    public RentalService(Vehicle[] vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public double rentUntil(Vehicle vehicle, LocalDateTime until) {
        if (vehicle == null || until.isBefore(LocalDateTime.now())) {
            return -1.0;
        }

        for (Vehicle current : vehicles) {
            if (current.getId().equals(vehicle.getId())) {
                if (current.getEndOfReservationPeriod().isBefore(LocalDateTime.now())) {
                    current.setEndOfReservationPeriod(until);
                    double seconds = ChronoUnit.SECONDS.between(LocalDateTime.now(), until);
                    int minutes = (int) Math.ceil(seconds / SECONDS_PER_MINUTE);
                    return minutes * current.getPricePerMinute();
                }
                return -1.0;
            }
        }
        return -1.0;
    }

    @Override
    public Vehicle findNearestAvailableVehicleInRadius(String type, Location location, double maxDistance) {
        Vehicle nearest = null;

        for (Vehicle current : vehicles) {
            double dist = current.getLocation().getDistance(location);
            boolean typeMatch = type.equalsIgnoreCase(current.getType());
            boolean withinRange = dist <= maxDistance;
            boolean available = current.getEndOfReservationPeriod().isBefore(LocalDateTime.now());

            if (typeMatch && withinRange && available) {
                maxDistance = dist;
                nearest = current;
            }
        }

        return nearest;
    }

}
