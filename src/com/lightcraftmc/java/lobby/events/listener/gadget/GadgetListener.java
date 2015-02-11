package com.lightcraftmc.java.lobby.events.listener.gadget;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import com.lightcraftmc.java.lobby.gadget.manager.EntityInteractAction;
import com.lightcraftmc.java.lobby.gadget.manager.Gadget;
import com.lightcraftmc.java.lobby.gadget.manager.GadgetManager;
import com.lightcraftmc.java.lobby.gadget.manager.SpecialAction;

public class GadgetListener implements Listener {

	/**
	 * Handles the generic interact event.
	 */
	@EventHandler
	public void interact(PlayerInteractEvent e) {

		if (!e.getAction().toString().contains("CLICK")) {
			return;
		}

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().equals(e.getPlayer().getItemInHand())) {
				if (g.interact(e.getPlayer(),
						e.getClickedBlock().getLocation(), e.getAction())) {
					e.setCancelled(true);
				}
				return;
			}
		}

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().getType()
					.equals(e.getPlayer().getItemInHand().getType())) {
				if (g.interact(e.getPlayer(),
						e.getClickedBlock().getLocation(), e.getAction())) {
					e.setCancelled(true);
				}
				return;
			}
		}
	}

	/**
	 * Handles the entity-right click interact event.
	 */
	@EventHandler
	public void interactEntityRight(PlayerInteractEntityEvent e) {

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().equals(e.getPlayer().getItemInHand())) {
				if (g.interactEntity(e.getPlayer(), e.getRightClicked(),
						EntityInteractAction.RIGHT_CLICK)) {
					e.setCancelled(true);
				}
				return;
			}
		}

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().getType()
					.equals(e.getPlayer().getItemInHand().getType())) {
				if (g.interactEntity(e.getPlayer(), e.getRightClicked(),
						EntityInteractAction.RIGHT_CLICK)) {
					e.setCancelled(true);
				}
				return;
			}
		}
	}

	/**
	 * Handles the entity-left click interact event.
	 */
	@EventHandler
	public void interactEntityRight(EntityDamageByEntityEvent e) {

		if (!(e.getDamager() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getDamager();
		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().equals(p.getItemInHand())) {
				if (g.interactEntity(p, e.getEntity(),
						EntityInteractAction.RIGHT_CLICK)) {
					e.setCancelled(true);
				}
				return;
			}
		}

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().getType().equals(p.getItemInHand().getType())) {
				if (g.interactEntity(p, e.getEntity(),
						EntityInteractAction.RIGHT_CLICK)) {
					e.setCancelled(true);
				}
				return;
			}
		}
	}

	/**
	 * Handles the bow-shoot special action event.
	 */
	@EventHandler
	public void bowShootSpecialAction(EntityShootBowEvent e) {

		if (!(e.getEntity() instanceof Player)) {
			return;
		}
		Player p = (Player) e.getEntity();
		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().equals(p.getItemInHand())) {
				if (g.specialAction(p, p.getLocation(), SpecialAction.BOW_SHOOT)) {
					e.setCancelled(true);
				}
				return;
			}
		}

		for (Gadget g : GadgetManager.getInstance().gadgets) {
			if (g.getDisplay().getType().equals(p.getItemInHand().getType())) {
				if (g.specialAction(p, p.getLocation(), SpecialAction.BOW_SHOOT)) {
					e.setCancelled(true);
				}
				return;
			}
		}
	}
}
