package bg.sofia.uni.fmi.mjt.gifts.gift;

import bg.sofia.uni.fmi.mjt.gifts.person.Person;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

public class BirthdayGift<T extends Priceable> implements Gift<T> {

    private final PriorityQueue<T> giftItems;
    private final Person<?> sender;
    private final Person<?> receiver;
    private double giftPrice;

    public BirthdayGift(Person<?> sender, Person<?> receiver, Collection<T> items) {
        this.sender = sender;
        this.receiver = receiver;
        this.giftItems = new PriorityQueue<>(Comparator.comparingDouble(Priceable::getPrice).reversed());
        this.giftPrice = 0.0;

        if (items != null) {
            for (T current : items) {
                if (current != null) {
                    giftPrice += current.getPrice();
                    giftItems.add(current);
                }
            }
        }
    }

    @Override
    public Person<?> getSender() {
        return sender;
    }

    @Override
    public Person<?> getReceiver() {
        return receiver;
    }

    @Override
    public double getPrice() {
        return giftPrice;
    }

    @Override
    public void addItem(T item) {
        if (item == null) {
            throw new IllegalArgumentException("item must not be null");
        }
        giftPrice += item.getPrice();
        giftItems.add(item);
    }

    @Override
    public boolean removeItem(T item) {
        if (item == null) {
            return false;
        }
        if (!giftItems.contains(item)) {
            return false;
        }
        giftPrice -= item.getPrice();
        return giftItems.remove(item);
    }

    @Override
    public Collection<T> getItems() {
        return List.copyOf(giftItems);
    }

    @Override
    public T getMostExpensiveItem() {
        if (giftItems.isEmpty()) {
            return null;
        }
        return giftItems.peek();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof BirthdayGift<?> other) {
            return other.receiver.equals(this.receiver)
                && other.sender.equals(this.sender)
                && this.giftItems.equals(other.giftItems);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sender, receiver, giftItems);
    }
}
