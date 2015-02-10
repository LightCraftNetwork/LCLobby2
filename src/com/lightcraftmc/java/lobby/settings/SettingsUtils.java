package com.lightcraftmc.java.lobby.settings;

import com.lightcraftmc.java.lobby.LCLobby;

public class SettingsUtils {

	public static String getSetting(String setting){
		return LCLobby.getInstance().getConfig().getString("settings." + setting);
	}
	
	public static String setSetting(String settingName, String value){
		LCLobby.getInstance().getConfig().set("settings." + settingName, value);
		LCLobby.getInstance().saveConfig();
		return value;
	}
}
