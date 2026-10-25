# Race Track

Simulate a car racing pit stop scenario with multithreading.

## Problem Description

A race track consists of a `Pit` (pit stop area) with a fixed number of `PitTeam`s — mechanic crews. Unlike a traditional race, any team can service any car. Cars drive laps for a random amount of time, then enter the pit. If a team is free it services the car immediately; otherwise the car waits.

## Task

Implement the following classes step by step (start simple, then refine):

**1. `RaceTrack` (implements `Track`)**

Constructor: `RaceTrack(int pitTeamsCount)`

Interface `Track`:
```java
package bg.sofia.uni.fmi.mjt.race.track;

import java.util.List;
import bg.sofia.uni.fmi.mjt.race.track.pit.Pit;

public interface Track {
    /** A car enters the pit when it needs maintenance or when it finishes the race. */
    void enterPit(Car car);

    /** Returns the number of cars that have finished the race. */
    int getNumberOfFinishedCars();

    /** Returns the IDs of cars that have finished the race. */
    List<Integer> getFinishedCarsIds();

    /** Returns the pit. */
    Pit getPit();
}
```

**2. `Car` (implements `Runnable`)**

```java
public Car(int id, int nPitStops, Track track)
public void run()       // sleep random 0–50 ms, then call track.enterPit(this)
public int getCarId()
public int getNPitStops()
public Track getTrack()
```

**3. `Pit`**

```java
public Pit(int nPitTeams)       // creates and starts PitTeam threads
public void submitCar(Car car)  // enqueue car; notify waiting PitTeams
public Car getCar()             // blocking dequeue; returns null when race is finished
public int getPitStopsCount()
public List<PitTeam> getPitTeams()
public void finishRace()        // signal all PitTeams to stop
```

Use a thread-safe queue (e.g., `ArrayDeque` with `synchronized`) to hold cars waiting for service.

**4. `PitTeam` (extends `Thread`)**

```java
public PitTeam(int id, Pit pitStop)
public void run()               // loop: get car, sleep 0–200 ms, re-launch car with decremented pitStops
public int getPitStoppedCars()
```

**5. Main driver**

- Create a `RaceTrack` with N pit teams.
- Start M cars concurrently, each with a random number of pit stops.
- Wait for all cars to finish.
- Call `pit.finishRace()` and verify totals.

## Project Structure

```
src/
└── bg/sofia/uni/fmi/mjt/race/track/
    ├── pit/
    │   ├── Pit.java
    │   └── PitTeam.java
    ├── Car.java
    ├── RaceTrack.java
    └── Track.java
test/
└── bg/sofia/uni/fmi/mjt/race/track/
    └── RaceTrackTest.java
```

Submit a zip of `src/` and `test/` to the grader.
