package com.lightcraftmc.java.lobby.events.listener.world;

import java.util.ArrayList;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockFadeEvent;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.LeavesDecayEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class WorldEvents implements Listener {

	boolean disableHunger = true;
	boolean allowBreakCreative = true;
	ArrayList<String> allowBlockBreak = new ArrayList<String>();

	@EventHandler
	public void handleLeafDecay(LeavesDecayEvent e) {
		e.setCancelled(true);
	}

	@EventHandler
	public void hunger(FoodLevelChangeEvent event) {
		if (!disableHunger)
			return;
		Player player = (Player) event.getEntity();
		event.setCancelled(true);
		player.setFoodLevel(20);
	}

	@EventHandler
	public void handleBlockFade(BlockFadeEvent e) {
		e.setCancelled(true);
	}

	Material[] blockPhysics = { Material.SIGN, Material.SIGN_POST,
			Material.LEAVES, Material.LEAVES_2, Material.VINE };

	@EventHandler
	public void handleBlockPhysics(BlockPhysicsEvent e) {
		for (Material m : blockPhysics) {
			if (e.getBlock().getType().equals(m)) {
				e.setCancelled(true);
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			if (allowBreakCreative) {
				return;
			}
		}
		if (allowBlockBreak.contains(e.getBlock().getWorld().getName())) {
			return;
		}
		e.setCancelled(true);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if (e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
			if (allowBreakCreative) {
				return;
			}
		}
		if (allowBlockBreak.contains(e.getBlock().getWorld().getName())) {
			return;
		}
		e.setCancelled(true);
	}


}
