package bg.sofia.uni.fmi.mjt.airbnb;

import bg.sofia.uni.fmi.mjt.airbnb.accommodation.Bookable;
import bg.sofia.uni.fmi.mjt.airbnb.filter.Criterion;

public interface AirbnbAPI {

    /**
     * Finds an accommodation by ID (case-insensitive).
     *
     * @param id the unique ID of the bookable
     * @return the matching bookable, or null if id is null, blank, or not found
     */
    Bookable findAccommodationById(String id);

    /**
     * @return the total revenue across all booked accommodations.
     */
    double estimateTotalRevenue();

    /**
     * @return the number of booked accommodations.
     */
    long countBookings();

    /**
     * Filters the accommodations by all given criteria.
     *
     * @param criteria the criteria to apply
     * @return an array of accommodations matching all specified criteria
     */
    Bookable[] filterAccommodations(Criterion... criteria);

}
