package com.google.payne.tk.john.dailyloginrewards.listeners;

import java.io.File;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;
import com.google.payne.tk.john.dailyloginrewards.ExistingPlayer;
import com.google.payne.tk.john.dailyloginrewards.NewPlayer;
import com.google.payne.tk.john.dailyloginrewards.configuration.Settings;

public class PlayerJoinListener implements Listener {
	
	
	@EventHandler
	public void onPlayerJoinEvent (PlayerJoinEvent event) {
		
		if ( (event.getPlayer().hasPermission("DailyLoginRewards.rewards") || (!Settings.UsePermissions)) ) {
			final Player player = event.getPlayer();
			final File folder = new File(DailyLoginRewards.main.getDataFolder(), "players");
			final File playerFile = new File(folder, player.getName() + ".yml");
			
			if ( !(event.getPlayer() instanceof Player) ) {
				return;
			}
			else if ( playerFile.exists() ) {
				ExistingPlayer.checkExistingPlayer(player, playerFile);
			} else {
				NewPlayer.createNewPlayer(player, playerFile);
			}
		}
	}
}
