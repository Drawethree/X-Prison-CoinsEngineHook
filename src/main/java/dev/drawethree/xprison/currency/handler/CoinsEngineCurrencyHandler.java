package dev.drawethree.xprison.currency.handler;

import dev.drawethree.xprison.api.currency.enums.LostCause;
import dev.drawethree.xprison.api.currency.enums.ReceiveCause;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import org.bukkit.OfflinePlayer;
import su.nightexpress.coinsengine.api.CoinsEngineAPI;
import su.nightexpress.coinsengine.api.currency.Currency;

public final class CoinsEngineCurrencyHandler implements XPrisonCurrencyHandler {

    private final Currency currency;

    public CoinsEngineCurrencyHandler(Currency currency) {
        this.currency = currency;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        return CoinsEngineAPI.getBalance(offlinePlayer.getUniqueId(),currency);
    }

    @Override
    public boolean setBalance(OfflinePlayer offlinePlayer, double v) {
        return CoinsEngineAPI.setBalance(offlinePlayer.getUniqueId(),currency,v);
    }

    @Override
    public boolean addBalance(OfflinePlayer offlinePlayer, double v, ReceiveCause receiveCause) {
        return CoinsEngineAPI.addBalance(offlinePlayer.getUniqueId(),currency,v);
    }

    @Override
    public boolean removeBalance(OfflinePlayer offlinePlayer, double v, LostCause lostCause) {
        return CoinsEngineAPI.removeBalance(offlinePlayer.getUniqueId(),currency,v);
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return getBalance(offlinePlayer) >= v;

    }
}