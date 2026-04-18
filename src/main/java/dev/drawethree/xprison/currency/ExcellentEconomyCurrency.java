package dev.drawethree.xprison.currency;

import dev.drawethree.xprison.api.currency.model.XPrisonCurrency;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import dev.drawethree.xprison.currency.handler.ExcellentEconomyCurrencyHandler;
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI;
import su.nightexpress.excellenteconomy.api.currency.ExcellentCurrency;

public final class ExcellentEconomyCurrency implements XPrisonCurrency {

    private final ExcellentEconomyAPI api;
    private final ExcellentCurrency currency;

    public ExcellentEconomyCurrency(ExcellentEconomyAPI api, ExcellentCurrency currency) {
        this.api = api;
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
        return new ExcellentEconomyCurrencyHandler(api, currency);
    }
}