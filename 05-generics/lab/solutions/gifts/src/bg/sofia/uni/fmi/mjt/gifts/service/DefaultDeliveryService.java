package bg.sofia.uni.fmi.mjt.gifts.service;

import bg.sofia.uni.fmi.mjt.gifts.gift.Gift;
import bg.sofia.uni.fmi.mjt.gifts.person.Person;

public class DefaultDeliveryService implements DeliveryService {

    @Override
    public void send(Person<?> receiver, Gift<?> gift) {
        if (receiver == null) {
            throw new IllegalArgumentException("receiver must not be null");
        }
        if (gift == null) {
            throw new IllegalArgumentException("gift must not be null");
        }
        receiver.receiveGift(gift);
    }
}
