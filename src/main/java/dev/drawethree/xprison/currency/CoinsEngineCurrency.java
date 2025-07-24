package dev.drawethree.xprison.currency;

import dev.drawethree.xprison.api.currency.model.XPrisonCurrency;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import dev.drawethree.xprison.currency.handler.CoinsEngineCurrencyHandler;
import su.nightexpress.coinsengine.api.currency.Currency;

public final class CoinsEngineCurrency implements XPrisonCurrency {

    private final Currency currency;

    public CoinsEngineCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String getName() {
        return currency.getName();
    }

    @Override
    public double getMaxAmount() {
        return currency.getMaxValue();
    }

    @Override
    public String getDisplayName() {
        return currency.getColumnName();
    }

    @Override
    public String getPrefix() {
        return currency.getPrefix();
    }

    @Override
    public String getSuffix() {
        return null;
    }

    @Override
    public String format(double v) {
        return currency.format(v);
    }

    @Override
    public XPrisonCurrencyHandler getHandler() {
        return new CoinsEngineCurrencyHandler(currency);
    }
}