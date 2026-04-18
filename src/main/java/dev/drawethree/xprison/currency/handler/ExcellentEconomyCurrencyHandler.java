package dev.drawethree.xprison.currency.handler;

import dev.drawethree.xprison.api.currency.enums.LostCause;
import dev.drawethree.xprison.api.currency.enums.ReceiveCause;
import dev.drawethree.xprison.api.currency.model.XPrisonCurrencyHandler;
import org.bukkit.OfflinePlayer;
import su.nightexpress.excellenteconomy.api.ExcellentEconomyAPI;
import su.nightexpress.excellenteconomy.api.currency.ExcellentCurrency;
import su.nightexpress.excellenteconomy.api.currency.operation.NotificationTarget;
import su.nightexpress.excellenteconomy.api.currency.operation.OperationContext;

import java.util.concurrent.ExecutionException;

public final class ExcellentEconomyCurrencyHandler implements XPrisonCurrencyHandler {

    private final ExcellentEconomyAPI api;
    private final ExcellentCurrency currency;

    public ExcellentEconomyCurrencyHandler(ExcellentEconomyAPI api, ExcellentCurrency currency) {
        this.api = api;
        this.currency = currency;
    }

    @Override
    public double getBalance(OfflinePlayer offlinePlayer) {
        if (offlinePlayer.isOnline()) {
            return api.getBalance(offlinePlayer.getPlayer(),currency);
        } else {
            try {
                return api.getBalanceAsync(offlinePlayer.getUniqueId(), currency).get();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean setBalance(OfflinePlayer offlinePlayer, double v) {
        if (offlinePlayer.isOnline()) {
            return api.setBalance(offlinePlayer.getPlayer(),currency,v, createContext());
        } else {
            try {
                return api.setBalanceAsync(offlinePlayer.getUniqueId(), currency, v, createContext()).get().bool();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean addBalance(OfflinePlayer offlinePlayer, double v, ReceiveCause receiveCause) {
        if (offlinePlayer.isOnline()) {
            return api.deposit(offlinePlayer.getPlayer(),currency,v,createContext());
        } else {
            try {
                return api.depositAsync(offlinePlayer.getUniqueId(), currency, v, createContext()).get().bool();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean removeBalance(OfflinePlayer offlinePlayer, double v, LostCause lostCause) {
        if (offlinePlayer.isOnline()) {
            return api.withdraw(offlinePlayer.getPlayer(),currency,v,createContext());
        } else {
            try {
                return api.withdrawAsync(offlinePlayer.getUniqueId(), currency, v, createContext()).get().bool();
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public boolean has(OfflinePlayer offlinePlayer, double v) {
        return getBalance(offlinePlayer) >= v;
    }

    private OperationContext createContext() {
        return OperationContext.custom("X-Prison").silentFor(NotificationTarget.USER, NotificationTarget.EXECUTOR, NotificationTarget.CONSOLE_LOGGER);
    }
}