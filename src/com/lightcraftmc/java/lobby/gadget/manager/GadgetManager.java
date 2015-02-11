package com.lightcraftmc.java.lobby.gadget.manager;

import java.util.ArrayList;

import mc.lightcraft.java.local.util.ItemUtils;
import mc.lightcraft.java.local.util.UtilMenu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.lightcraftmc.java.lobby.gadget.gadgets.TeleportBow;

public class GadgetManager {

	public ArrayList<Gadget> gadgets = new ArrayList<Gadget>();
	private static GadgetManager instance;

	private GadgetManager() {
	}

	/**
	 * @return Instance of GadgetManager
	 */
	public static GadgetManager getInstance() {
		if (instance == null) {
			instance = new GadgetManager();
		}
		return instance;
	}

	/**
	 * Used to initialize the gadgets.
	 */
	public void init() {
		getInstance();
		for (int i = 0; i < 50; i++) {
			new TeleportBow();
		}
		System.out.println("[Success] Loaded " + gadgets.size());
	}

	/**
	 * Open the gadget inventory to a specific page!
	 * 
	 * @param p
	 *            The player you want to open the inventory
	 * @param page
	 *            The page you want to use.
	 * @return the inventory the player just opened
	 */
	public Inventory openInventory(Player p, int page) {
		if(page == 0){
			page = 1;
		}else{
			page++;
		}
		Inventory i = Bukkit.createInventory(null, 9 * 6, "Gadgets | page "
				+ page);
		int starting = (page-1) * UtilMenu.getAllowedSlots().length;
		for (int counter = 0; counter < UtilMenu.getAllowedSlots().length; counter++) {
			try {
				Gadget obj = gadgets.get(starting + counter);
				i.setItem(UtilMenu.getAllowedSlots()[counter], obj.getDisplay());
			} catch (Exception ex) {
			}
		}
		if (page != 1) {
			i.setItem(48, ItemUtils.setNameAndLore(
					new ItemStack(Material.ARROW), "§c<§c§m--§c Go Back",
					"§7Go to page " + (page - 1)));
		} else {
			i.setItem(48, ItemUtils.setNameAndLore(
					new ItemStack(Material.ARROW), "§c<§c§m--§c Go Back",
					"§7Close menu."));
		}
		if(i.getItem(43) == null){
			i.setItem(50, ItemUtils.setNameAndLore(
					new ItemStack(Material.ARROW), "§cNext page §c§m--§c>",
					"§7Close menu."));
		}else{
			i.setItem(50, ItemUtils.setNameAndLore(new ItemStack(Material.ARROW),
					"§cNext page §c§m--§c>", "§7Go to page " + (page + 1)));
		}
		i.setItem(49, ItemUtils.setNameAndLore(new ItemStack(Material.BARRIER),
				"§cDisable any active gadget",
				"§7Click here to remove your active gadget!"));


		p.openInventory(i);
		return i;
	}
}
