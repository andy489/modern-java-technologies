# Investment Wallet

Everyone wants to grow their money. Today, investing is no longer exclusive to professional brokers. Alongside classic assets such as securities and metals, cryptocurrencies are rapidly growing in popularity. We will build an application to manage a personal *investment portfolio*.

### Investment Wallet

In the package `bg.sofia.uni.fmi.mjt.investment.wallet` create a class `InvestmentWallet` with constructor

```java
public InvestmentWallet(QuoteService quoteService)
```

that implements the `Wallet` interface:

```java
package bg.sofia.uni.fmi.mjt.investment.wallet;

import java.util.Collection;
import java.util.Set;

import bg.sofia.uni.fmi.mjt.investment.wallet.acquisition.Acquisition;
import bg.sofia.uni.fmi.mjt.investment.wallet.asset.Asset;
import bg.sofia.uni.fmi.mjt.investment.wallet.exception.InsufficientResourcesException;
import bg.sofia.uni.fmi.mjt.investment.wallet.exception.OfferPriceException;
import bg.sofia.uni.fmi.mjt.investment.wallet.exception.UnknownAssetException;
import bg.sofia.uni.fmi.mjt.investment.wallet.exception.WalletException;

public interface Wallet {

    /**
     * @param cash the cash to deposit in the investment wallet
     * @return the cash balance in the wallet after the transaction
     * @throws IllegalArgumentException if cash is negative
     */
    double deposit(double cash);

    /**
     * @param cash the cash to withdraw from the investment wallet
     * @return the cash balance in the wallet after the transaction
     * @throws IllegalArgumentException       if cash is negative
     * @throws InsufficientResourcesException if the balance is insufficient
     */
    double withdraw(double cash) throws InsufficientResourcesException;

    /**
     * @param asset    the asset to buy
     * @param quantity the quantity to buy
     * @param maxPrice the maximum price to pay per unit of asset
     * @return the acquisition record
     * @throws IllegalArgumentException       if quantity or maxPrice is negative, or asset is null
     * @throws UnknownAssetException          if there is no defined quote for the asset
     * @throws OfferPriceException            if the ask price is higher than maxPrice
     * @throws InsufficientResourcesException if there is not enough balance
     */
    Acquisition buy(Asset asset, int quantity, double maxPrice) throws WalletException;

    /**
     * @param asset    the asset to sell
     * @param quantity the quantity to sell
     * @param minPrice the minimum acceptable price per unit
     * @return the cash added to the balance after the sale
     * @throws IllegalArgumentException       if asset is null, or quantity or minPrice is negative
     * @throws InsufficientResourcesException if there is not enough quantity of the asset
     * @throws UnknownAssetException          if there is no defined quote for the asset
     * @throws OfferPriceException            if the bid price is lower than minPrice
     */
    double sell(Asset asset, int quantity, double minPrice) throws WalletException;

    /**
     * @return the combined valuation of all assets in the wallet
     */
    double getValuation();

    /**
     * Valuation is the current bid price of the asset multiplied by the quantity held.
     *
     * @param asset the asset to valuate
     * @return the valuation of the asset
     * @throws IllegalArgumentException if asset is null
     * @throws UnknownAssetException    if asset is not currently in the wallet or has no quote
     */
    double getValuation(Asset asset) throws UnknownAssetException;

    /**
     * @return the asset with the highest valuation
     */
    Asset getMostValuableAsset();

    /**
     * @return all acquisitions in the wallet as an unmodifiable copy
     */
    Collection<Acquisition> getAllAcquisitions();

    /**
     * @param n the maximum number of acquisitions to return
     * @return an unmodifiable copy of the last n acquisitions; returns all if fewer than n exist
     * @throws IllegalArgumentException if n is negative
     */
    Set<Acquisition> getLastNAcquisitions(int n);
}
```

> Note: `WalletException` is the base class for `InsufficientResourcesException`, `OfferPriceException`, and `UnknownAssetException`.

### Assets

Assets come in four types: cryptocurrency (`Crypto`), regular currency (`Fiat`), gold (`Gold`), and stock (`Stock`), described by the `AssetType` enum:

```java
public enum AssetType {
    CRYPTO, FIAT, GOLD, STOCK
}
```

Each asset has a public constructor with parameters `(String id, String name)` and implements the `Asset` interface:

```java
public interface Asset {
    String getId();
    String getName();
    AssetType getType();
}
```

### Asset Acquisitions

The acquisition of an asset is modelled by a class implementing the `Acquisition` interface:

```java
public interface Acquisition {
    double getPrice();
    LocalDateTime getTimestamp();
    int getQuantity();
    Asset getAsset();
}
```

### Quotes

The current ask price and bid price of each asset are modelled by the `Quote` record:

```java
public record Quote(double askPrice, double bidPrice) { }
```

and accessed via the `QuoteService` interface:

```java
public interface QuoteService {
    /**
     * @param asset the asset to quote
     * @return the quote, or null if unavailable
     * @throws IllegalArgumentException if asset is null
     */
    Quote getQuote(Asset asset);
}
```

### Package Structure

```
src
└── bg.sofia.uni.fmi.mjt.investment.wallet
    ├── acquisition
    │      ├── Acquisition.java
    │      └── (...)
    ├── asset
    │      ├── Asset.java
    │      ├── AssetType.java
    │      ├── Crypto.java
    │      ├── Fiat.java
    │      ├── Gold.java
    │      ├── Stock.java
    │      └── (...)
    ├── exception
    │      ├── InsufficientResourcesException.java
    │      ├── OfferPriceException.java
    │      ├── UnknownAssetException.java
    │      └── WalletException.java
    ├── quote
    │      ├── Quote.java
    │      ├── QuoteService.java
    │      └── (...)
    ├── InvestmentWallet.java
    └── Wallet.java
```

### Notes

- Do not modify the interfaces provided in the problem statement.
