package com.lightcraftmc.java.lobby.events.listener.connection;

import mc.lightcraft.java.common.rank.tree.RankTree;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import com.lightcraftmc.java.lobby.LCLobby;
import com.lightcraftmc.java.lobby.util.LocalQueryUtil;

public class ConnectionListener implements Listener {

	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		Player p = e.getPlayer();
		p.teleport(p.getWorld().getSpawnLocation());
		p.setGameMode(GameMode.ADVENTURE);
		RankTree.getTree().unloadRank(e.getPlayer());
		LCLobby.getInstance().getItemManager().giveItems(p);
		p.updateInventory();
		LocalQueryUtil.handleBan(p);
		LocalQueryUtil.handleRank(p, true);
		e.setJoinMessage(null);
	}

	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		new UtilQuery
	}

}
