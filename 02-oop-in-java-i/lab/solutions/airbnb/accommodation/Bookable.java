package bg.sofia.uni.fmi.mjt.airbnb.accommodation;

import bg.sofia.uni.fmi.mjt.airbnb.accommodation.location.Location;

import java.time.LocalDateTime;

public interface Bookable {

    /**
     * @return the unique ID of the Bookable.
     *         Format: first three uppercase letters of the type + dash + sequential count
     *         (e.g. "HOT-0", "APA-1", "VIL-2"). Counting starts at 0, separately per type.
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
     * @return the total price of the stay (nights × price per night) if booked, or 0.0 if not booked.
     */
    double getTotalPriceOfStay();

    /** @return the price per night for this accommodation. */
    double getPricePerNight();

}
