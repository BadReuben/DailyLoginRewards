package com.google.payne.tk.john.dailyloginrewards.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;

public class TotalDaysRewards {
	static FileConfiguration TotalDaysRewards = null;
	static File fileTotalDays = new File(DailyLoginRewards.main.getDataFolder(), "TotalDays.yml");
	
	public static void LoadTotalDaysRewards() {
		if ( !fileTotalDays.exists() ) {
			TotalDaysRewards = new YamlConfiguration();
			TotalDaysRewards.set("2.pay", 50);
			TotalDaysRewards.set("4.pay", 150);
			TotalDaysRewards.set("7.pay", 400);
			TotalDaysRewards.set("7.broadcast", "&9&o%p logged in for 7 days total!");
			TotalDaysRewards.set("7.say", "&9&o&4&oYou logged in for 7 days total!");
			TotalDaysRewards.set("7.command", "give %p 264 1");
			TotalDaysRewards.set("14.pay", 700);
			TotalDaysRewards.set("21.pay", 700);
			TotalDaysRewards.set("28.pay", 1400);
			TotalDaysRewards.set("28.broadcast", "&9&o%p logged in for 28 days total!");
			TotalDaysRewards.set("28.say", "&9&o&4&oYou logged in for 28 days total!");
			TotalDaysRewards.set("35.pay", 700);
			TotalDaysRewards.set("42.pay", 700);
			TotalDaysRewards.set("49.pay", 700);
			TotalDaysRewards.set("56.pay", 700);
			TotalDaysRewards.set("63.pay", 700);
			TotalDaysRewards.set("70.pay", 700);
			TotalDaysRewards.set("77.pay", 700);
			TotalDaysRewards.set("84.pay", 700);
			TotalDaysRewards.set("91.pay", 700);
			TotalDaysRewards.set("98.pay", 700);
			TotalDaysRewards.set("105.pay", 700);
			TotalDaysRewards.set("112.pay", 700);
			TotalDaysRewards.set("119.pay", 700);
			TotalDaysRewards.set("126.pay", 700);
			TotalDaysRewards.set("133.pay", 700);
			TotalDaysRewards.set("140.pay", 700);
			TotalDaysRewards.set("147.pay", 700);
			TotalDaysRewards.set("154.pay", 700);
			TotalDaysRewards.set("161.pay", 700);
			TotalDaysRewards.set("168.pay", 700);
			TotalDaysRewards.set("175.pay", 700);
			TotalDaysRewards.set("182.pay", 700);
			TotalDaysRewards.set("189.pay", 700);
			TotalDaysRewards.set("196.pay", 700);
			
			try {
				TotalDaysRewards.save(fileTotalDays);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			TotalDaysRewards = YamlConfiguration.loadConfiguration(fileTotalDays);
		}
	}
	
	public static void CheckTotalDaysReward(int Days, Player player) {
		
		//Import any rewards for receiving player
		int Pay = TotalDaysRewards.getInt(Days + ".pay", -1);
		String Broadcast = TotalDaysRewards.getString(Days + ".broadcast", null);
		String Say = TotalDaysRewards.getString(Days + ".say", null);
		String Cmd = TotalDaysRewards.getString(Days + ".command", null);
		
		
		
		if ( Pay > 0 && (RewardsEconomy.ecoEnabled == true) ) {
			DailyLoginRewards.economy.depositPlayer(player.getName(), Pay);
			
			if ( (Lang.TotalPay != null) && (Lang.TotalPay != "") && (Settings.UseMessages) ) {
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', 
						Lang.TotalPay.replace("%m", Integer.toString(Pay))) );
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
