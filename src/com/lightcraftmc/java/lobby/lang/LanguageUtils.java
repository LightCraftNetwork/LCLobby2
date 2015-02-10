package com.lightcraftmc.java.lobby.lang;

import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;

import com.lightcraftmc.java.lobby.LCLobby;

public class LanguageUtils {

	/**
	 * Get the default locale.
	 * @return string in default locale form
	 */
	public static String getDefaultLocale() {
		//return LCLobby.getInstance().getConfig().getString("defaultlocale");
		return "en-us";
	}
	
	/**
	 * Get a string from the language file.
	 * @param locale the local you will use
	 * @param name the name of the string you will use
	 * @return string from language file
	 */
	public static String getString(String locale, String name) {
		String built = "lang." + locale.toLowerCase() + "."
				+ name.toLowerCase();
		return ChatColor.translateAlternateColorCodes('&', LCLobby.getInstance().getConfig().getString(built).replace("#nl", "\n"));
	}
	
	/**
	 * Get a string from the language file with the default locale.
	 * @param name the name of the string you will use
	 * @return string from language file
	 */
	public static String getString(String name) {
		return getString(getDefaultLocale(), name);
	}
	
	/**
	 * Get a string from the language file according to a player
	 * @param locale the local you will use
	 * @param name the name of the string you will use
	 * @param p the player you will take data from
	 * @return string from language file
	 */
	public static String getString(String locale, String name, OfflinePlayer p) {
		String s = getString(locale, name);
		s = s.replace("{PLAYERNAME}", p.getName());
		s = s.replace("{PLAYERUUID}", p.getUniqueId().toString());
		return s;
	}
	
	/**
	 * Get a string from the language file according to a player with the default locale.
	 * @param name the name of the string you will use
	 * @param p the player you will take data from
	 * @return string from language file
	 */
	public static String getString(String name, OfflinePlayer p) {
		return getString(getDefaultLocale(), name, p);
	}

}
