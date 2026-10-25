# Gifts

The holiday season is approaching — Black Friday, Christmas, New Year — and it's time to think about gifts.
You will build an application that models gift wrapping and delivery.

## Gift

A gift can be anything. Some gifts consist of multiple items, and every gift has a price.

All gifts implement the generic interface `Gift<T extends Priceable>`:

```java
package bg.sofia.uni.fmi.mjt.gifts.gift;

import bg.sofia.uni.fmi.mjt.gifts.person.Person;
import java.util.Collection;

public interface Gift<T extends Priceable> {
    Person<?> getSender();
    Person<?> getReceiver();
    double getPrice();
    void addItem(T t);
    boolean removeItem(T t);
    Collection<T> getItems();
    T getMostExpensiveItem();
}
```

Price is modelled by the `Priceable` interface:

```java
package bg.sofia.uni.fmi.mjt.gifts.gift;

public interface Priceable {
    double getPrice();
}
```

In the package `bg.sofia.uni.fmi.mjt.gifts.gift`, create a generic class
`BirthdayGift<T extends Priceable> implements Gift<T>` with constructor:

```java
public BirthdayGift(Person<?> sender, Person<?> receiver, Collection<T> items)
```

## Person

People who exchange gifts are modelled by the generic class `DefaultPerson<I>` with constructor:

```java
public DefaultPerson(I id)
```

It implements the generic interface `Person<I>` (see full Javadoc in the source).

## Packing and Delivery

Create `DefaultPackingService<T extends Priceable> implements PackingService<T>` and
`DefaultDeliveryService implements DeliveryService` in the package `bg.sofia.uni.fmi.mjt.gifts.service`.

## Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.gifts
    ├── exception
    │      └── WrongReceiverException.java
    ├── gift
    │      ├── BirthdayGift.java
    │      ├── Gift.java
    │      └── Priceable.java
    ├── person
    │      ├── DefaultPerson.java
    │      └── Person.java
    └── service
           ├── DefaultDeliveryService.java
           ├── DefaultPackingService.java
           ├── DeliveryService.java
           └── PackingService.java
```

> **Note:** Do not modify any of the provided interfaces.
