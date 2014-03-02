package com.google.payne.tk.john.dailyloginrewards.listeners;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;
import com.google.payne.tk.john.dailyloginrewards.DelayedJoinTask;
import com.google.payne.tk.john.dailyloginrewards.configuration.Settings;

public class PlayerJoinListener implements Listener {
	
	
	@EventHandler
	public void onPlayerJoinEvent (PlayerJoinEvent event) {
		
		if ( (event.getPlayer().hasPermission("DailyLoginRewards.rewards") || (!Settings.UsePermissions)) ) {
			
			final Player player = event.getPlayer();
			final File folder = new File(DailyLoginRewards.main.getDataFolder(), "players");
			final File playerFile = new File(folder, player.getName() + ".yml");
			
			Bukkit.getScheduler().scheduleSyncDelayedTask(DailyLoginRewards.main, new DelayedJoinTask(player, playerFile), Settings.delay);
		}
	}
}
