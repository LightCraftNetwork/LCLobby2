package com.lightcraftmc.java.lobby.events.listener.chat;

import mc.lightcraft.java.common.rank.tree.RankTree;
import mc.lightcraft.java.common.rank.tree.ServerRank;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		String format = "§7[{color}{rank}§7] {color}{name}§7: §f{message}";
		ServerRank rank = RankTree.getTree().getRank(p);
		String color = RankTree.getTree().getColor(rank);
		if (rank.equals(ServerRank.Default)) {
			format = "{color}{name}: {message}";
		}
		format = format.replace("{color}", color);
		format = format.replace("{rank}", rank.toString());
		format = format.replace("{name}", p.getName());
		format = format.replace("{message}", e.getMessage());
		e.setFormat(format);
	}

}
