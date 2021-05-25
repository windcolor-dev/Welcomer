package com.windpvp.welcomer;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.md_5.bungee.api.ChatColor;

public class JoinListener implements Listener {

	private JavaPlugin plugin;
	private FileConfiguration config;
	private String cmdData;
	private String replacedCmdData;

	public JoinListener(JavaPlugin plugin) {
		this.plugin = plugin;
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		config = plugin.getConfig();
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
		if (config.getBoolean("message.enabled")) {
			for (int count = 1; count > 0; count++) {
				String messageData = config.getString("message.line" + count);
				if (messageData == null) {
					break;
				}
				new BukkitRunnable() {

					@Override
					public void run() {
						player.sendMessage(ChatColor.translateAlternateColorCodes('&', messageData));
					}
				}.runTaskLater(plugin, 10);
			}
		}
		if (config.getBoolean("console_command.enabled")) {
			for (int count = 1; count > 0; count++) {
				cmdData = config.getString("console_command.cmd" + count);
				if (cmdData == null) {
					break;
				}
				if (cmdData.contains("PLAYER")) {
					replacedCmdData = cmdData.replace("PLAYER", player.getDisplayName());
				}

				new BukkitRunnable() {

					@Override
					public void run() {
						Bukkit.getServer().dispatchCommand(Bukkit.getServer().getConsoleSender(), replacedCmdData);
					}
					
				}.runTaskLater(plugin, 10);
			}
		}
	}

}
