package dev.drawethree.xprison;

import dev.drawethree.xprison.api.XPrisonAPI;
import dev.drawethree.xprison.api.addons.XPrisonAddon;
import dev.drawethree.xprison.currency.CoinsEngineCurrency;
import org.bukkit.event.Listener;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class CoinsEngineHookAddon implements XPrisonAddon, Listener {

    private static CoinsEngineHookAddon instance;
    private XPrisonAPI api;
    private List<CoinsEngineCurrency> coinsEngineCurrencyList;

    @Override
    public void onEnable() {
        instance = this;
        api = XPrisonAPI.getInstance();
        coinsEngineCurrencyList = new ArrayList<>();

        Collection<Currency> currencies = CoinsEngineAPI.getCurrencies();
        for (Currency currency : currencies) {
            coinsEngineCurrencyList.add(new CoinsEngineCurrency(currency));
        }
        for (CoinsEngineCurrency currency : coinsEngineCurrencyList) {
            api.getCurrencyApi().registerCurrency(currency);
        }

    }


    @Override
    public void onDisable() {
        for (CoinsEngineCurrency currency : coinsEngineCurrencyList) {
            api.getCurrencyApi().unregisterCurrency(currency);
        }
    }

    public XPrisonAPI getApi() {
        return api;
    }
}
