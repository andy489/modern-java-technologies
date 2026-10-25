package bg.sofia.uni.fmi.mjt.race.track;

import java.util.List;

import bg.sofia.uni.fmi.mjt.race.track.pit.Pit;

public interface Track {

    /**
     * A car enters the pit when it needs maintenance or when it finishes the race.
     * A car finishes the race when it has no more pit stops remaining.
     *
     * @param car the car entering the pit
     */
    void enterPit(Car car);

    /**
     * Returns the number of cars that have finished the race.
     *
     * @return count of finished cars
     */
    int getNumberOfFinishedCars();

    /**
     * Returns the IDs of cars that have finished the race.
     *
     * @return list of finished car IDs
     */
    List<Integer> getFinishedCarsIds();

    /**
     * Returns the pit.
     *
     * @return the pit
     */
    Pit getPit();
}
