package com.windpvp.welcomer;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin { 

	@Override
	public void onEnable() {
		saveDefaultConfig();
		new JoinListener(this);
	}

	@Override
	public void onDisable() {
		saveDefaultConfig();
	}

}
