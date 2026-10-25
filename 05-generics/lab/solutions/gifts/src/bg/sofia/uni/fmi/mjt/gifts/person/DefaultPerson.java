package bg.sofia.uni.fmi.mjt.gifts.person;

import bg.sofia.uni.fmi.mjt.gifts.exception.WrongReceiverException;
import bg.sofia.uni.fmi.mjt.gifts.gift.Gift;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class DefaultPerson<I> implements Person<I> {
    private final I id;
    private final Collection<Gift<?>> gifts;

    public DefaultPerson(I id) {
        this.id = id;
        this.gifts = new TreeSet<>(
            Comparator.comparingDouble(Gift<?>::getPrice).reversed()
                .thenComparingInt(System::identityHashCode)
        );
    }

    @Override
    public Collection<Gift<?>> getNMostExpensiveReceivedGifts(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }

        var result = new HashSet<Gift<?>>();
        var it = gifts.iterator();
        int remaining = n;
        while (it.hasNext() && remaining > 0) {
            result.add(it.next());
            remaining--;
        }
        return Set.copyOf(result);
    }

    @Override
    public Collection<Gift<?>> getGiftsBy(Person<?> person) {
        if (person == null) {
            throw new IllegalArgumentException("person must not be null");
        }

        var result = new HashSet<Gift<?>>();
        for (var gift : gifts) {
            if (gift.getSender().equals(person)) {
                result.add(gift);
            }
        }
        return Set.copyOf(result);
    }

    @Override
    public I getId() {
        return id;
    }

    @Override
    public void receiveGift(Gift<?> gift) {
        if (gift == null) {
            throw new IllegalArgumentException("gift must not be null");
        }
        if (!gift.getReceiver().equals(this)) {
            throw new WrongReceiverException("gift is not addressed to this person");
        }
        gifts.add(gift);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultPerson<?> that = (DefaultPerson<?>) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
