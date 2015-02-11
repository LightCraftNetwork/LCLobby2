package com.lightcraftmc.java.lobby.gadget.gadgets;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.java.lobby.gadget.manager.EntityInteractAction;
import com.lightcraftmc.java.lobby.gadget.manager.Gadget;
import com.lightcraftmc.java.lobby.gadget.manager.SpecialAction;

public class TeleportBow extends Gadget {

	public TeleportBow() {
		super("tpBow", "§bTeleport Bow", new ItemStack(Material.BOW));
	}

	@Override
	public boolean interactEntity(Player p, Entity e,
			EntityInteractAction action) {
		return false;
	}

	@Override
	public boolean interact(Player p, Location location, Action action) {
		return false;
	}

	@Override
	public boolean specialAction(Player p, Location location,
			SpecialAction action) {
		if (!action.equals(SpecialAction.BOW_SHOOT)) {
			return false;
		}
		return false;
	}

}
