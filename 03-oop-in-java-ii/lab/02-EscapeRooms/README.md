# Escape Rooms

[Escape rooms](https://en.wikipedia.org/wiki/Escape_room) are a popular form of entertainment, especially among programmers. We will build a platform for escape room information — similar to [vsichkistai.bg](http://vsichkistai.bg) — where fans can find the most interesting rooms and the best teams.

### Escape Room Platform

In the package `bg.sofia.uni.fmi.mjt.escaperoom`, create a class `EscapeRoomPlatform` with the constructor

```java
public EscapeRoomPlatform(Team[] teams, int maxCapacity)
```

that implements the interfaces `EscapeRoomAdminAPI` (for platform administrators) and `EscapeRoomPortalAPI` (for end users):

```java
package bg.sofia.uni.fmi.mjt.escaperoom;

import bg.sofia.uni.fmi.mjt.escaperoom.exception.RoomNotFoundException;
import bg.sofia.uni.fmi.mjt.escaperoom.exception.PlatformCapacityExceededException;
import bg.sofia.uni.fmi.mjt.escaperoom.exception.RoomAlreadyExistsException;
import bg.sofia.uni.fmi.mjt.escaperoom.exception.TeamNotFoundException;
import bg.sofia.uni.fmi.mjt.escaperoom.room.EscapeRoom;
import bg.sofia.uni.fmi.mjt.escaperoom.team.Team;

public interface EscapeRoomAdminAPI {

    /**
     * Adds a new escape room to the platform.
     *
     * @throws IllegalArgumentException          if room is null.
     * @throws PlatformCapacityExceededException if the maximum number of escape rooms has been reached.
     * @throws RoomAlreadyExistsException        if the room already exists in the platform.
     */
    void addEscapeRoom(EscapeRoom room) throws RoomAlreadyExistsException;

    /**
     * Removes the escape room with the specified name from the platform.
     *
     * @throws IllegalArgumentException if the room name is null, empty or blank.
     * @throws RoomNotFoundException    if no escape room with the given name exists.
     */
    void removeEscapeRoom(String roomName) throws RoomNotFoundException;

    /** Returns all escape rooms registered on the platform. */
    EscapeRoom[] getAllEscapeRooms();

    /**
     * Registers a team achievement: escaping a room within the given time.
     *
     * @param roomName   the name of the escape room.
     * @param teamName   the name of the team.
     * @param escapeTime the escape time in minutes.
     * @throws IllegalArgumentException if any name is null/empty/blank, or if escapeTime is
     *                                  negative, zero, or exceeds the room's maximum escape time.
     * @throws RoomNotFoundException    if no escape room with the given name exists.
     * @throws TeamNotFoundException    if no team with the given name exists.
     */
    void registerAchievement(String roomName, String teamName, int escapeTime)
        throws RoomNotFoundException, TeamNotFoundException;

}
```

```java
package bg.sofia.uni.fmi.mjt.escaperoom;

import bg.sofia.uni.fmi.mjt.escaperoom.exception.RoomNotFoundException;
import bg.sofia.uni.fmi.mjt.escaperoom.room.EscapeRoom;
import bg.sofia.uni.fmi.mjt.escaperoom.room.Review;
import bg.sofia.uni.fmi.mjt.escaperoom.team.Team;

public interface EscapeRoomPortalAPI {

    /**
     * Returns the escape room with the specified name.
     *
     * @throws IllegalArgumentException if the room name is null, empty or blank.
     * @throws RoomNotFoundException    if no escape room with the given name exists.
     */
    EscapeRoom getEscapeRoomByName(String roomName) throws RoomNotFoundException;

    /**
     * Adds a review for the escape room with the specified name.
     *
     * @throws IllegalArgumentException if the room name is null/empty/blank, or if the review is null.
     * @throws RoomNotFoundException    if no escape room with the given name exists.
     */
    void reviewEscapeRoom(String roomName, Review review) throws RoomNotFoundException;

    /**
     * Returns all reviews for the escape room with the specified name,
     * in the order they were added (oldest to newest).
     * Returns an empty array if there are no reviews.
     *
     * @throws IllegalArgumentException if the room name is null, empty or blank.
     * @throws RoomNotFoundException    if no escape room with the given name exists.
     */
    Review[] getReviews(String roomName) throws RoomNotFoundException;

    /**
     * Returns the team with the highest rating.
     *
     * Scoring rules — for each successfully escaped room (within the max time):
     * - Base points = room difficulty rank (1–4)
     * - +2 bonus if escape time ≤ 50% of max time
     * - +1 bonus if escape time ≤ 75% (and > 50%) of max time
     *
     * A team's rating is the sum of all its points.
     *
     * @return the top team, or null if there are no teams.
     */
    Team getTopTeamByRating();

}
```

### Escape Rooms

Rooms are uniquely identified by name and represented by the class `EscapeRoom` in `bg.sofia.uni.fmi.mjt.escaperoom.room`:

```java
public EscapeRoom(String name, Theme theme, Difficulty difficulty, int maxTimeToEscape,
                  double priceToPlay, int maxReviewsCount)
```

`EscapeRoom` implements `Ratable`:

```java
package bg.sofia.uni.fmi.mjt.escaperoom.rating;

public interface Ratable {
    /** Returns the rating of this object. */
    double getRating();
}
```

The rating is a floating-point number in [0.0, 10.0] computed as the average of all submitted review ratings. A room with no reviews has a rating of 0.0.

`EscapeRoom` contains the following methods (you may add more as needed):

```java
    public String getName() { return name; }

    public Difficulty getDifficulty() { return difficulty; }

    public int getMaxTimeToEscape() { return maxTimeToEscape; }

    /** Returns all stored reviews in insertion order. */
    public Review[] getReviews() {
        // TODO: implement
    }

    /**
     * Adds a review. The platform keeps at most {@code maxReviewsCount} reviews;
     * when full, the oldest review is overwritten by the newest.
     * The room rating always averages ALL submitted reviews, including overwritten ones.
     */
    public void addReview(Review review) {
        // TODO: implement
    }
```

### Theme and Difficulty

```java
package bg.sofia.uni.fmi.mjt.escaperoom.room;

public enum Theme {
    HORROR,
    FANTASY,
    SCIFI,
    MYSTERY
}
```

```java
package bg.sofia.uni.fmi.mjt.escaperoom.room;

public enum Difficulty {

    EASY(1),
    MEDIUM(2),
    HARD(3),
    EXTREME(4);

    private final int rank;

    Difficulty(int rank) {
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

}
```

### Review

Users can leave reviews for rooms. A review is modelled as the record `Review(int rating, String reviewText)`.

`Review` must have a compact constructor that validates:
- `rating` is in the range [0, 10]
- `reviewText` is not `null` and does not exceed 200 characters

On validation failure the compact constructor must throw `IllegalArgumentException`.

### Teams

Teams are represented by the class `Team` in `bg.sofia.uni.fmi.mjt.escaperoom.team`. Instances cannot be created directly — they are obtained via the public static factory method:

```java
public static Team of(String name, TeamMember[] members)
```

`Team` also implements `Ratable` and contains:

```java
    /**
     * Adds the given points to the team's rating.
     *
     * @throws IllegalArgumentException if points is negative.
     */
    public void updateRating(int points) {
        // TODO: implement
    }

    public String getName() { return name; }
```

Team members are modelled as:

```java
public record TeamMember(String name, LocalDateTime birthday) {}
```

### Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.escaperoom
    ├── exception
    │      ├── PlatformCapacityExceededException.java
    │      ├── RoomAlreadyExistsException.java
    │      ├── RoomNotFoundException.java
    │      └── TeamNotFoundException.java
    ├── rating
    │      └── Ratable.java
    ├── room
    │      ├── Difficulty.java
    │      ├── EscapeRoom.java
    │      ├── Review.java
    │      └── Theme.java
    ├── team
    │      ├── Team.java
    │      └── TeamMember.java
    ├── EscapeRoomAdminAPI.java
    ├── EscapeRoomPlatform.java
    └── EscapeRoomPortalAPI.java
```

### Notes

- Do not modify the interfaces provided in the problem statement.
- Using data structures other than arrays is **not allowed**. Solve the task using only the knowledge covered in the course so far.
