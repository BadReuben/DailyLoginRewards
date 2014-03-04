package com.google.payne.tk.john.dailyloginrewards.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;

public class DailyReward {
	static FileConfiguration DailyRewards = null;
	static File fileDaily = new File(DailyLoginRewards.main.getDataFolder(), "Daily.yml");
	
	public static void LoadDailyRewards() {
		if ( !fileDaily.exists() ) {
			DailyRewards = new YamlConfiguration();
			DailyRewards.set("dailyreward.pay", 50);
			try {
				DailyRewards.save(fileDaily);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			DailyRewards = YamlConfiguration.loadConfiguration(fileDaily);
		}
	}
	
	public static int CheckDailyReward(Player player) {
		
		//Import any rewards for receiving player
		int Pay = DailyRewards.getInt("dailyreward.pay", -1);
		String Broadcast = DailyRewards.getString("dailyreward.broadcast", null);
		String Say = DailyRewards.getString("dailyreward.say", null);
		String Cmd = DailyRewards.getString("dailyreward.command", null);
		
		if ( Broadcast != null ) {
			Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Broadcast.replace("%p", player.getName())));
		}
		
		if ( Say != null ) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', Say));
		}
		
		if ( Cmd != null ) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Cmd.replace("%p", player.getName()));
		}
		
		if ( Pay > 0 && (RewardsEconomy.ecoEnabled == true) ) {
			DailyLoginRewards.economy.depositPlayer(player.getName(), Pay);
			
			if ( (Lang.DailyPay != null) && (Lang.DailyPay != "") && (Settings.UseMessages) && !(Settings.UseCombineTotals) ) {
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', 
						Lang.DailyPay.replace("%m", Integer.toString(Pay))) );
			}
			return Pay;
		}
		return 0;
		
	}
}
