# Spotify

*Spotify* is one of the world's most popular audio streaming services.

We will build a system that models a subset of its core functionality.

### Spotify

In the package `bg.sofia.uni.fmi.mjt.spotify`, create a class `Spotify` with the constructor

```java
public Spotify(Account[] accounts, Playable[] playableContent)
```

that implements the `StreamingService` interface:

```java
package bg.sofia.uni.fmi.mjt.spotify;

import bg.sofia.uni.fmi.mjt.spotify.account.Account;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.AccountNotFoundException;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlayableNotFoundException;
import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

public interface StreamingService {

    /**
     * Simulates playing content with the specified title by a user with the given account.
     *
     * @param account the account of the user that will play the content. The account must be registered in the platform
     *                in order to access its contents.
     * @param title   the exact name of the content.
     * @throws IllegalArgumentException  if the account is null or if the title is null or empty.
     * @throws AccountNotFoundException  if the account is not registered in the platform.
     * @throws PlayableNotFoundException if content with the specified title is not present in the platform.
     */
    void play(Account account, String title) throws AccountNotFoundException, PlayableNotFoundException;

    /**
     * Adds the content with the given title to the "Liked Content" default playlist in the library of the specified account.
     *
     * @param account the account of the user. The account must be registered in the platform
     *                in order to access its contents.
     * @param title   the exact name of the content.
     * @throws IllegalArgumentException  if the account is null or if the title is null or empty.
     * @throws AccountNotFoundException  if the account is not registered in the platform.
     * @throws PlayableNotFoundException if content with the specified title is not present in the platform.
     * @throws StreamingServiceException if the content could not be added to the "Liked Content" default playlist.
     */
    void like(Account account, String title)
            throws AccountNotFoundException, PlayableNotFoundException, StreamingServiceException;

    /**
     * Returns the playable content with the given title.
     *
     * @param title the exact name of the content.
     * @throws IllegalArgumentException  if the title is null or empty.
     * @throws PlayableNotFoundException if content with the specified title is not present in the platform.
     */
    Playable findByTitle(String title) throws PlayableNotFoundException;

    /**
     * Returns the content which has been played the most. If there is no content in the platform, returns null.
     * If there is a tie, returns any of the most played content.
     */
    Playable getMostPlayed();

    /**
     * Returns the sum of total listen times for all accounts in the platform.
     */
    double getTotalListenTime();

    /**
     * Returns the total revenue of the platform.
     * The platform makes money from each registered account:
     * - for FREE accounts, the revenue is 0.10 for every ad that has been played
     * - for PREMIUM accounts, the revenue is a one-time subscription of 25.00
     */
    double getTotalPlatformRevenue();

}
```

### Accounts

Users of the platform are represented by accounts. Each account is uniquely identified by the user's email address.

In the package `bg.sofia.uni.fmi.mjt.spotify.account`, create two classes — `FreeAccount` and `PremiumAccount` — that extend the abstract class `Account` and have constructors with the parameter list `(String email, Library library)`. `Account` contains the following methods (you may add additional ones as needed):

```java
    /**
     * Returns the number of ads listened to.
     * - Free accounts get one ad after every 5 pieces of content played
     * - Premium accounts get no ads
     */
    public abstract int getAdsListenedTo();

    /**
     * Returns the account type as an enum with possible values FREE and PREMIUM
     */
    public abstract AccountType getType();

    /**
     * Simulates listening of the specified content.
     * Increments the total number of content listened and the total listen time for this account.
     */
    public void listen(Playable playable) {
        // TODO: add implementation here
    }

    /**
     * Returns the library for this account.
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * Returns the total listen time for this account. Ad listen time is not included.
     */
    public double getTotalListenTime() {
        return totalListenTime;
    }
```

### Playable

Create two classes — `Audio` and `Video`* — that implement the `Playable` interface:

```java
package bg.sofia.uni.fmi.mjt.spotify.playable;

public interface Playable {

    /**
     * Simulates playing the content. Increments the total play count and returns a message:
     * "Currently playing <type> content: <content_title>"
     * where <type> is the lowercase type of the content.
     */
    String play();

    /** Returns the total number of times the content has been played. */
    int getTotalPlays();

    /** Returns the title of the playable. */
    String getTitle();

    /** Returns the artist of the playable. */
    String getArtist();

    /** Returns the publishing year of the playable. */
    int getYear();

    /** Returns the duration of the playable. */
    double getDuration();

}
```

Both classes have constructors with parameters `(String title, String artist, int year, double duration)`.

\* Since around 2021, [Spotify also supports video streaming](https://support.spotify.com/us/article/videos/).

### Playlist

Spotify provides functionality for creating and managing playlists.

Create a class `UserPlaylist` with constructor `public UserPlaylist(String name)` that implements the `Playlist` interface:

```java
package bg.sofia.uni.fmi.mjt.spotify.playlist;

import bg.sofia.uni.fmi.mjt.spotify.playable.Playable;

public interface Playlist {

    /**
     * Adds the given playable content to the playlist.
     *
     * @throws PlaylistCapacityExceededException when the playlist has exceeded its capacity of 20 items.
     */
    void add(Playable playable) throws PlaylistCapacityExceededException;

    /** Returns the name of the playlist. */
    String getName();

}
```

Playlists are managed through a `Library`:

```java
package bg.sofia.uni.fmi.mjt.spotify.library;

import bg.sofia.uni.fmi.mjt.spotify.playlist.Playlist;
import bg.sofia.uni.fmi.mjt.spotify.exceptions.PlaylistNotFoundException;

public interface Library {

    /**
     * Adds the provided playlist to the library.
     *
     * @throws LibraryCapacityExceededException when the library capacity (max 21) is exceeded.
     */
    void add(Playlist playlist) throws LibraryCapacityExceededException;

    /**
     * Removes the playlist with the given name from the library.
     *
     * @throws IllegalArgumentException if the name matches the default "Liked Content" playlist.
     * @throws EmptyLibraryException if the library is empty.
     * @throws PlaylistNotFoundException if no playlist with the given name exists.
     */
    void remove(String name) throws EmptyLibraryException, PlaylistNotFoundException;

    /** Returns the default "Liked Content" playlist. */
    Playlist getLiked();

}
```

The implementation of `Library` is the class `UserLibrary` with a no-argument constructor. Every user automatically has a "Liked Content" playlist that cannot be deleted.

### Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.spotify
    ├── account
    │      ├── Account.java
    │      ├── AccountType.java
    │      ├── FreeAccount.java
    │      └── PremiumAccount.java
    ├── exceptions
    │      ├── AccountNotFoundException.java
    │      ├── EmptyLibraryException.java
    │      ├── LibraryCapacityExceededException.java
    │      ├── PlayableNotFoundException.java
    │      ├── PlaylistCapacityExceededException.java
    │      ├── PlaylistNotFoundException.java
    │      └── StreamingServiceException.java
    ├── library
    │      ├── Library.java
    │      └── UserLibrary.java
    ├── playable
    │      ├── Audio.java
    │      ├── Playable.java
    │      └── Video.java
    ├── playlist
    │      ├── Playlist.java
    │      └── UserPlaylist.java
    ├── Spotify.java
    └── StreamingService.java
```

### Notes

- Do not modify the interfaces provided in the problem statement.
- Using data structures other than arrays is **not allowed**. Solve the task using only the knowledge covered in the course so far.
