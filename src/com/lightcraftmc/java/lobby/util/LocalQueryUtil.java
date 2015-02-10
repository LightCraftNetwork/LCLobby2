package com.lightcraftmc.java.lobby.util;

import mc.lightcraft.java.common.rank.tree.RankTree;
import mc.lightcraft.java.common.rank.tree.ServerRank;
import mc.lightcraft.java.remote.util.UtilQuery;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lightcraftmc.java.lobby.LCLobby;

public class LocalQueryUtil {

	@SuppressWarnings("deprecation")
	public static void handleBan(final Player p) {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(LCLobby.getInstance(),
				new Runnable() {
					public void run() {
						String query = (LCLobby.getInstance().getDBManager()
								.query(UtilQuery.getBannedQuery(p)));
						if ((!query.equals("") && !query.equals(" "))
								&& !query.equals("false")) {

							Bukkit.getScheduler().scheduleSyncDelayedTask(
									LCLobby.getInstance(), new Runnable() {
										public void run() {
											p.kickPlayer("§cYou are banned!");
										}
									}, 1);

						}
					}
				}, 1);
	}

	@SuppressWarnings("deprecation")
	public static void handleRank(final Player p, final boolean giveCapabilities) {
		Bukkit.getScheduler().scheduleAsyncDelayedTask(LCLobby.getInstance(),
				new Runnable() {
					public void run() {
						String query = LCLobby.getInstance().getDBManager()
								.query(UtilQuery.getRankQuery(p));
						ServerRank r2 = ServerRank.Default;
						for (ServerRank rr : RankTree.getTree().getTreeList()) {
							if (rr.toString().equalsIgnoreCase(query)) {
								r2 = rr;
							}
						}
						final ServerRank r = r2;
						RankTree.getTree().setRank(p, r);
						Bukkit.getScheduler().scheduleSyncDelayedTask(
								LCLobby.getInstance(), new Runnable() {
									public void run() {
										p.setPlayerListName(RankTree.getTree()
												.getColor(r) + p.getName());
										// Bukkit.broadcastMessage(r.toString());
										if (giveCapabilities) {
											if (RankTree.getTree().hasRank(r,
													ServerRank.VIP)) {
												p.setAllowFlight(true);
											}
										}

									}
								}, 1);

					}
				}, 1);
	}

}
