package com.google.payne.tk.john.dailyloginrewards.configuration;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;

public class ConsecutiveRewards {
	static FileConfiguration ConsecutiveRewards = null;
	static File fileConsecutive = new File(DailyLoginRewards.main.getDataFolder(), "Consecutive.yml");
	
	public static void LoadConsecutiveRewards() {
		if ( !fileConsecutive.exists() ) {
			ConsecutiveRewards = new YamlConfiguration();
			ConsecutiveRewards.set("2.pay", 50);
			ConsecutiveRewards.set("4.pay", 150);
			ConsecutiveRewards.set("7.pay", 400);
			ConsecutiveRewards.set("7.broadcast", "&9&o%p logged in for 7 days in a row!");
			ConsecutiveRewards.set("7.say", "&9&o&4&oYou logged in for 7 days in a row!");
			ConsecutiveRewards.set("7.command", "give %p 264 1");
			ConsecutiveRewards.set("14.pay", 700);
			ConsecutiveRewards.set("21.pay", 700);
			ConsecutiveRewards.set("28.pay", 1400);
			ConsecutiveRewards.set("28.broadcast", "&9&o%p logged in for 28 days in a row!");
			ConsecutiveRewards.set("28.say", "&9&o&4&oYou logged in for 28 days in a row!");
			ConsecutiveRewards.set("35.pay", 700);
			ConsecutiveRewards.set("42.pay", 700);
			ConsecutiveRewards.set("49.pay", 700);
			ConsecutiveRewards.set("56.pay", 700);
			ConsecutiveRewards.set("63.pay", 700);
			ConsecutiveRewards.set("70.pay", 700);
			ConsecutiveRewards.set("77.pay", 700);
			ConsecutiveRewards.set("84.pay", 700);
			ConsecutiveRewards.set("91.pay", 700);
			ConsecutiveRewards.set("98.pay", 700);
			ConsecutiveRewards.set("105.pay", 700);
			ConsecutiveRewards.set("112.pay", 700);
			ConsecutiveRewards.set("119.pay", 700);
			ConsecutiveRewards.set("126.pay", 700);
			ConsecutiveRewards.set("133.pay", 700);
			ConsecutiveRewards.set("140.pay", 700);
			ConsecutiveRewards.set("147.pay", 700);
			ConsecutiveRewards.set("154.pay", 700);
			ConsecutiveRewards.set("161.pay", 700);
			ConsecutiveRewards.set("168.pay", 700);
			ConsecutiveRewards.set("175.pay", 700);
			ConsecutiveRewards.set("182.pay", 700);
			ConsecutiveRewards.set("189.pay", 700);
			ConsecutiveRewards.set("196.pay", 700);
			
			try {
				ConsecutiveRewards.save(fileConsecutive);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			ConsecutiveRewards = YamlConfiguration.loadConfiguration(fileConsecutive);
		}
	}
	
	public static void CheckConsecutiveReward(int Days, Player player) {
		
		//Import any rewards for receiving player
		int Pay = ConsecutiveRewards.getInt(Days + ".pay", -1);
		String Broadcast = ConsecutiveRewards.getString(Days + ".broadcast", null);
		String Say = ConsecutiveRewards.getString(Days + ".say", null);
		String Cmd = ConsecutiveRewards.getString(Days + ".command", null);
		
		
		
		if ( Pay > 0 && (RewardsEconomy.ecoEnabled == true) ) {
			DailyLoginRewards.economy.depositPlayer(player.getName(), Pay);
			
			if ( (Lang.ConsecutivePay != null) && (Lang.ConsecutivePay != "") && (Settings.UseMessages) ) {
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', 
						Lang.ConsecutivePay.replace("%m", Integer.toString(Pay))) );
			}
		}
		
		if ( Broadcast != null ) {
			Bukkit.getServer().broadcastMessage(ChatColor.translateAlternateColorCodes('&', Broadcast.replace("%p", player.getName())));
		}
		
		if ( Say != null ) {
			player.sendMessage(ChatColor.translateAlternateColorCodes('&', Say));
		}
		
		if ( Cmd != null ) {
			Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), Cmd.replace("%p", player.getName()));
		}
	}
}
