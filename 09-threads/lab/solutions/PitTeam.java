package bg.sofia.uni.fmi.mjt.race.track.pit;

import bg.sofia.uni.fmi.mjt.race.track.Car;

import java.util.concurrent.ThreadLocalRandom;

public class PitTeam extends Thread {
    private static final int SERVICE_UPPER_BOUND = 10;

    private int pitStoppedCars;
    private final int id;
    private final Pit pitStop;

    public PitTeam(int id, Pit pitStop) {
        this.id = id;
        this.pitStop = pitStop;
    }

    @Override
    public void run() {
        Car car;
        while ((car = pitStop.getCar()) != null) {
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(SERVICE_UPPER_BOUND));
                pitStoppedCars++;

                var refreshedCar = new Car(car.getCarId(), car.getNPitStops() - 1, car.getTrack());
                var serviceThread = new Thread(refreshedCar);
                serviceThread.setName("car-" + car.getCarId() + "-team-" + id);
                serviceThread.start();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public int getPitStoppedCars() {
        return pitStoppedCars;
    }
}
