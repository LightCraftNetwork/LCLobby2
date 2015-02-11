package com.lightcraftmc.java.lobby.events.listener.gadget;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import com.lightcraftmc.java.lobby.gadget.manager.GadgetManager;

public class GadgetMenuListener implements Listener {

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();

		if (!e.getInventory().getTitle().contains("Gadgets |")) {
			return;
		}

		e.setCancelled(true);

		if (e.getCurrentItem() == null) {
			return;
		}

		if (!e.getCurrentItem().hasItemMeta()) {
			return;
		}

		if (!e.getCurrentItem().getItemMeta().hasDisplayName()) {
			return;
		}

		if (e.getCurrentItem().getItemMeta().hasLore()) {
			String lore = e.getCurrentItem().getItemMeta().getLore().get(0);
			lore = ChatColor.stripColor(lore);
			if (lore.startsWith("Go to page ")) {
				int page = Integer.parseInt(lore.split("Go to page ")[1]);
				page--;
				GadgetManager.getInstance().openInventory(p, page);
				return;
			}
			if (lore.startsWith("Close menu.")) {
				p.closeInventory();
			}
		}

	}

}
