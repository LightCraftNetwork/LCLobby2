package com.lightcraftmc.java.lobby.gadget.manager;

import mc.lightcraft.java.common.rank.tree.ServerRank;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

public abstract class Gadget {

	private String name;
	private String displayName;
	private ItemStack display;
	private int cooldown = 0;
	private boolean usesAmmo = false;
	private ServerRank permission = ServerRank.Default;
	private String description = "Default description!";

	/**
	 * Constructor for a Gadget
	 * 
	 * @param name
	 *            Name of the gadget, cannot be changed.
	 * @param displayName
	 *            Display name!
	 * @param display
	 *            Display ItemStack
	 */
	public Gadget(String name, String displayName, ItemStack display) {
		this.name = name;
		this.displayName = displayName;
		this.display = display;
		if (!GadgetManager.getInstance().gadgets.contains(this)) {
			GadgetManager.getInstance().gadgets.add(this);
		}
	}

	/**
	 * Ran when a player interacts with an entity.
	 * 
	 * @param p
	 *            Player that is interacting
	 * @param e
	 *            Entity the player interacted upon.
	 * @param action
	 *            Left/Right click
	 * @return true *if the event should be cancelled* (usually if the gadget
	 *         succeeded)
	 */
	public abstract boolean interactEntity(Player p, Entity e,
			EntityInteractAction action);

	/**
	 * Ran when a player interacts with a block or air.
	 * 
	 * @param p
	 *            Player that is interacting
	 * @param location
	 *            Location the player clicked on (Can be null!)
	 * @param action
	 *            Action from interact
	 * @return true *if the event should be cancelled* (usually if the gadget
	 *         succeeded)
	 */
	public abstract boolean interact(Player p, Location location, Action action);

	/**
	 * Ran when a player does a "special action" (For instance, shoot a bow,
	 * EnderPearl lands, etc)
	 * 
	 * @param p
	 *            Player that is interacting
	 * @param location
	 *            Location the player clicked on (Can be null!)
	 * @param action
	 *            Action from interact
	 * @return true *if the event should be tracked, otherwise will cancel
	 *         event.* (ex. will track arrow if bow is shot and will fire the
	 *         event again)
	 */
	public abstract boolean specialAction(Player p, Location location,
			SpecialAction action);

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName
	 *            the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	/**
	 * @return the display
	 */
	public ItemStack getDisplay() {
		return display;
	}

	/**
	 * @param display
	 *            the display to set
	 */
	public void setDisplay(ItemStack display) {
		this.display = display;
	}

	/**
	 * @return the cooldown
	 */
	public int getCooldown() {
		return cooldown;
	}

	/**
	 * @param cooldown
	 *            the cooldown to set
	 */
	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	/**
	 * @return the usesAmmo
	 */
	public boolean isUsesAmmo() {
		return usesAmmo;
	}

	/**
	 * @param usesAmmo
	 *            the usesAmmo to set
	 */
	public void setUsesAmmo(boolean usesAmmo) {
		this.usesAmmo = usesAmmo;
	}

	/**
	 * @return the permission
	 */
	public ServerRank getPermission() {
		return permission;
	}

	/**
	 * @param permission
	 *            the permission to set
	 */
	public void setPermission(ServerRank permission) {
		this.permission = permission;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

}
