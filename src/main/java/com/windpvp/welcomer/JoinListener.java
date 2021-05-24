package com.windpvp.welcomer;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {

	private JavaPlugin plugin;
	private FileConfiguration config = plugin.getServer().getPluginManager().getPlugin("Welcomer").getConfig();

	public JoinListener(JavaPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
	}

	@EventHandler
	public void on(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (config.getBoolean("message.enabled")) {
			for (int count = 1; count > 0; count++) {
				String messageData = config.getString("message.line" + count);
				if (messageData == null) {
					break;
				}
				player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageData));
			}
		}
		if (config.getBoolean("console_command")) {
			for (int count = 1; count > 0; count++) {
				String cmdData = config.getString("console_command.cmd" + count);
				if (cmdData == null) {
					break;
				}
				if (cmdData.contains("PLAYER")) {
					cmdData = cmdData.replace("PLAYER", "");
				}
				plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), cmdData);
			}
		}
	}

}
