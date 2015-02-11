package com.lightcraftmc.java.lobby;

import mc.lightcraft.java.remote.LCDatabase.DatabaseManager;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import com.lightcraftmc.java.lobby.events.listener.chat.ChatListener;
import com.lightcraftmc.java.lobby.events.listener.connection.ConnectionListener;
import com.lightcraftmc.java.lobby.events.listener.gadget.GadgetListener;
import com.lightcraftmc.java.lobby.events.listener.gadget.GadgetMenuListener;
import com.lightcraftmc.java.lobby.events.listener.world.WorldEvents;
import com.lightcraftmc.java.lobby.gadget.manager.GadgetManager;
import com.lightcraftmc.java.lobby.item.ItemManager;

public class LCLobby extends JavaPlugin {

	private static LCLobby instance;
	private static ItemManager itemManager;
	private DatabaseManager dbManager;

	/**
	 * Get the DB manager
	 */
	public DatabaseManager getDBManager() {
		return dbManager;
	}

	/**
	 * Get the main instance of the plugin
	 */
	public static LCLobby getInstance() {
		return instance;
	}

	/**
	 * Get the Item Manager
	 */
	public ItemManager getItemManager() {
		return itemManager;
	}

	@Override
	/**
	 * Spigot calls this to initiate the plugin.
	 */
	public void onEnable() {
		instance = this;
		itemManager = new ItemManager();
		dbManager = new DatabaseManager();
		dbManager.setAccessToken(getConfig().getString("database.accesstoken"));
		dbManager.setHost(getConfig().getString("database.host"));
		setupEvents();
		GadgetManager.getInstance().init();
	}

	/**
	 * Method called to setup events.
	 */
	private void setupEvents() {
		Bukkit.getServer().getPluginManager()
				.registerEvents(new WorldEvents(), this);

		Bukkit.getServer().getPluginManager()
				.registerEvents(new ConnectionListener(), this);

		Bukkit.getServer().getPluginManager()
				.registerEvents(new ChatListener(), this);

		Bukkit.getServer().getPluginManager()
				.registerEvents(new GadgetListener(), this);

		Bukkit.getServer().getPluginManager()
				.registerEvents(new GadgetMenuListener(), this);
	}

	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		GadgetManager.getInstance().openInventory((Player) sender,
				Integer.parseInt(args[0]));
		/*
		 * if (args.length == 1) { //
		 * Bukkit.broadcastMessage(getConfig().getString(args[0]));
		 * Bukkit.broadcastMessage(dbManager.query(args[0])); return false; }
		 * getConfig().set(args[0], args[1].replace("_", " ")); saveConfig();
		 */
		return false;
	}

}
