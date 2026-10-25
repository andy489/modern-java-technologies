package bg.sofia.uni.fmi.mjt.rentalservice.vehicle;

import java.time.LocalDateTime;

import bg.sofia.uni.fmi.mjt.rentalservice.location.Location;

public class AbstractVehicle implements Vehicle {

    private final String type;
    private final String id;
    private final double pricePerMinute;
    private final Location location;

    private LocalDateTime endOfReservationPeriod;

    public AbstractVehicle(Location location, String type, String id, double pricePerMinute) {
        this.location = location;
        this.type = type;
        this.id = id;
        this.pricePerMinute = pricePerMinute;
        this.endOfReservationPeriod = LocalDateTime.MIN;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public double getPricePerMinute() {
        return pricePerMinute;
    }

    @Override
    public LocalDateTime getEndOfReservationPeriod() {
        return endOfReservationPeriod;
    }

    @Override
    public void setEndOfReservationPeriod(LocalDateTime endOfReservationPeriod) {
        this.endOfReservationPeriod = endOfReservationPeriod;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractVehicle other = (AbstractVehicle) o;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
