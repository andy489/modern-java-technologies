package bg.sofia.uni.fmi.mjt.airbnb;

import bg.sofia.uni.fmi.mjt.airbnb.accommodation.Apartment;
import bg.sofia.uni.fmi.mjt.airbnb.accommodation.Bookable;
import bg.sofia.uni.fmi.mjt.airbnb.accommodation.BookableAccommodation;
import bg.sofia.uni.fmi.mjt.airbnb.accommodation.location.Location;
import bg.sofia.uni.fmi.mjt.airbnb.filter.Criterion;
import bg.sofia.uni.fmi.mjt.airbnb.filter.LocationCriterion;
import bg.sofia.uni.fmi.mjt.airbnb.filter.PriceCriterion;

import java.time.LocalDateTime;

public class Airbnb implements AirbnbAPI {

    private final Bookable[] accommodations;

    public Airbnb(Bookable[] accommodations) {
        this.accommodations = accommodations;
    }

    @Override
    public Bookable findAccommodationById(String id) {
        if (id == null || id.isBlank()) {
            return null;
        }
        for (Bookable accommodation : accommodations) {
            if (accommodation.getId().equalsIgnoreCase(id)) {
                return accommodation;
            }
        }
        return null;
    }

    @Override
    public double estimateTotalRevenue() {
        double total = 0.0;
        for (Bookable accommodation : accommodations) {
            total += accommodation.getTotalPriceOfStay();
        }
        return total;
    }

    @Override
    public long countBookings() {
        long count = 0;
        for (Bookable accommodation : accommodations) {
            if (accommodation.isBooked()) {
                ++count;
            }
        }
        return count;
    }

    @Override
    public Bookable[] filterAccommodations(Criterion... criteria) {
        int matchCount = 0;
        for (Bookable accommodation : accommodations) {
            if (matchesAll(accommodation, criteria)) {
                matchCount++;
            }
        }

        Bookable[] result = new Bookable[matchCount];
        int index = 0;
        for (Bookable accommodation : accommodations) {
            if (matchesAll(accommodation, criteria)) {
                result[index++] = accommodation;
            }
        }
        return result;
    }

    private boolean matchesAll(Bookable accommodation, Criterion[] criteria) {
        for (Criterion criterion : criteria) {
            if (criterion != null && !criterion.check(accommodation)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String... args) {
        BookableAccommodation apt1 = new Apartment(new Location(1, 1), 90);
        BookableAccommodation hot1 = new Apartment(new Location(2, 2), 60);
        BookableAccommodation vil1 = new Apartment(new Location(3, 3), 220);

        apt1.book(LocalDateTime.of(2022, 12, 22, 14, 0), LocalDateTime.of(2022, 12, 25, 14, 0));
        hot1.book(LocalDateTime.of(2022, 12, 25, 12, 0), LocalDateTime.of(2022, 12, 26, 11, 0));

        Bookable[] bookables = {apt1, hot1, vil1};
        var airbnb = new Airbnb(bookables);

        Criterion locationCriterion = new LocationCriterion(new Location(0, 0), 3.9);
        Criterion priceCriterion = new PriceCriterion(70, 300);

        System.out.println("Bookings: " + airbnb.countBookings());
        System.out.println("Revenue: " + airbnb.estimateTotalRevenue());

        for (Bookable b : airbnb.filterAccommodations(locationCriterion, priceCriterion)) {
            System.out.println(b);
        }
    }

}
