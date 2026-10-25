package bg.sofia.uni.fmi.mjt.gifts.service;

import bg.sofia.uni.fmi.mjt.gifts.gift.BirthdayGift;
import bg.sofia.uni.fmi.mjt.gifts.gift.Gift;
import bg.sofia.uni.fmi.mjt.gifts.gift.Priceable;
import bg.sofia.uni.fmi.mjt.gifts.person.Person;

import java.util.Collection;
import java.util.Set;

public class DefaultPackingService<T extends Priceable> implements PackingService<T> {

    @Override
    public Gift<T> pack(Person<?> sender, Person<?> receiver, T item) {
        if (sender == null) throw new IllegalArgumentException("sender must not be null");
        if (receiver == null) throw new IllegalArgumentException("receiver must not be null");
        if (item == null) throw new IllegalArgumentException("item must not be null");

        return new BirthdayGift<>(sender, receiver, Set.of(item));
    }

    @Override
    @SuppressWarnings("varargs")
    public Gift<T> pack(Person<?> sender, Person<?> receiver, T... items) {
        if (sender == null) throw new IllegalArgumentException("sender must not be null");
        if (receiver == null) throw new IllegalArgumentException("receiver must not be null");
        for (T item : items) {
            if (item == null) throw new IllegalArgumentException("each item must not be null");
        }

        return new BirthdayGift<>(sender, receiver, Set.of(items));
    }

    @Override
    public Collection<T> unpack(Gift<T> gift) {
        if (gift == null) throw new IllegalArgumentException("gift must not be null");
        return gift.getItems();
    }
}
