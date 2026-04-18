package dev.drawethree.xprison;

import dev.drawethree.xprison.api.XPrisonAPI;
import dev.drawethree.xprison.api.addons.XPrisonAddon;
import dev.drawethree.xprison.currency.ExcellentEconomyCurrency;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI;
import su.nightexpress.excellenteconomy.api.currency.ExcellentCurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class ExcellentEconomyHookAddon implements XPrisonAddon, Listener {

    private static ExcellentEconomyHookAddon instance;
    private XPrisonAPI api;
    private List<ExcellentEconomyCurrency> excellentEconomyCurrencyList;

    @Override
    public void onEnable() {
        instance = this;
        api = XPrisonAPI.getInstance();
        excellentEconomyCurrencyList = new ArrayList<>();

        if (!Bukkit.getPluginManager().getPlugin("CoinsEngine").isEnabled()) {
            Bukkit.getLogger().warning("ExcellentEconomy plugin not found! No ExcellentEconomy currencies will be supported.");
            onDisable();
            return;
        }

        RegisteredServiceProvider<ExcellentEconomyAPI> provider = Bukkit.getServer().getServicesManager().getRegistration(ExcellentEconomyAPI.class);
        if (provider == null) {
            Bukkit.getLogger().warning("Unable to find ExcellentEconomyAPI service! Disabling.");
            onDisable();
            return;
        }

        ExcellentEconomyAPI api = provider.getProvider();

        Collection<ExcellentCurrency> currencies = api.getCurrencies();
        for (ExcellentCurrency currency : currencies) {
            excellentEconomyCurrencyList.add(new ExcellentEconomyCurrency(api, currency));
        }
        for (ExcellentEconomyCurrency currency : excellentEconomyCurrencyList) {
            this.api.getCurrencyApi().registerCurrency(currency);
        }

    }


    @Override
    public void onDisable() {
        for (ExcellentEconomyCurrency currency : excellentEconomyCurrencyList) {
            api.getCurrencyApi().unregisterCurrency(currency);
        }
    }

    public XPrisonAPI getApi() {
        return api;
    }
}
