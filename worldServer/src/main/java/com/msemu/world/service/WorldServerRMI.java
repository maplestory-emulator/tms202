package com.msemu.world.service;

import com.msemu.commons.rmi.ILoginServerRMI;
import com.msemu.commons.rmi.IWorldServerRMI;
import com.msemu.commons.rmi.model.WorldRegisterResult;
import com.msemu.commons.thread.EventManager;
import com.msemu.core.configs.NetworkConfig;
import com.msemu.world.World;
import com.msemu.world.channel.Channel;
import com.msemu.world.client.GameClient;
import com.msemu.world.client.character.Character;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

/**
 * Created by Weber on 2018/3/31.
 */
public class WorldServerRMI extends UnicastRemoteObject implements IWorldServerRMI {

    private static final Logger log = LoggerFactory.getLogger(WorldServerRMI.class);

    private ILoginServerRMI connection;
    private transient ScheduledFuture<?> reconnectTask;

    public WorldServerRMI() throws RemoteException {
        EventManager.getInstance().addFixedRateEvent(this::watchLoginServerStatus, 2000, 5000);
    }

    private void connectToLoginServer() {
        try {
            final Registry registry = LocateRegistry.getRegistry(NetworkConfig.RMI_PORT);
            this.connection = (ILoginServerRMI) registry.lookup("msemu_login_server");
            final WorldRegisterResult registerResult = this.connection.registerWorld(this, World.getInstance().getWorldInfo());
            switch (registerResult) {
                case SUCCESS: {
                    log.info("Connected to Login server successfully.");
                    break;
                }
                default: {
                    log.warn("Connection to Login server failed. Reason: {}", registerResult.toString());
                    break;
                }
            }
        } catch (ConnectException e2) {
            log.warn("Login server isn't available. Make sure it's up and running. {}", e2);
        } catch (Exception e) {
            log.error("Connection to Login server failed", e);
        } finally {
            this.reconnectTask = null;
            if (this.connection == null) {
                this.onConnectionLost();
            }
        }
    }

    public void updateWorld() {
        try {
            this.connection.updateWorld(this, World.getInstance().getWorldInfo());
        } catch (RemoteException e) {
            WorldServerRMI.log.error("Error while updateWorld");
        }
    }

    public void addClient(int channel, GameClient client) {
      // TODO
    }

    public void removeClient(Integer accID) {
        // TODO
    }

    @Override
    public boolean testConnection() throws RemoteException {
        return true;
    }

    @Override
    public boolean isAccountOnServer(int accountId) throws RemoteException {
        return World.getInstance().getChannels().stream().filter(ch-> ch.isAccountOnChannel(accountId)).findFirst().isPresent();
    }

    @Override
    public boolean kickByAccountId(int accountId) throws RemoteException {
        if (isAccountOnServer(accountId)) {
            World.getInstance().getChannels()
                    .stream()
                    .forEach(ch -> ch.getCharacters().stream()
                    .forEach(chr -> chr.logout()));
            return true;
        }
        return false;
    }

    private void onConnectionLost() {
        if (this.reconnectTask != null) {
            return;
        }
        log.info("Connection with Login server lost.");
        this.connection = null;
        reconnectTask = EventManager.getInstance().addEvent(() -> {
            log.info("Reconnecting to Login server...");
            connectToLoginServer();
        }, 2000);
    }

    private void watchLoginServerStatus() {
        try {
            WorldServerRMI.this.connection.testConnection();
        } catch (Exception e) {
            if (WorldServerRMI.this.reconnectTask == null) {
                WorldServerRMI.this.onConnectionLost();
            }
        }
    }
}