package com.google.payne.tk.john.dailyloginrewards;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.payne.tk.john.dailyloginrewards.configuration.ConsecutiveRewards;
import com.google.payne.tk.john.dailyloginrewards.configuration.DailyReward;
import com.google.payne.tk.john.dailyloginrewards.configuration.Lang;
import com.google.payne.tk.john.dailyloginrewards.configuration.Settings;
import com.google.payne.tk.john.dailyloginrewards.configuration.TotalDaysRewards;
import com.google.payne.tk.john.dailyloginrewards.util.TimeDate;

public class ExistingPlayer {
	
	public static void checkExistingPlayer(Player player, File playerFile) {
		FileConfiguration playerConfig = null;
		int today = TimeDate.getTodaysDate();
		int yesterday = Settings.Yesterday;
		
		String name = player.getName();
		
		playerConfig = YamlConfiguration.loadConfiguration(playerFile);
		int lastOnline = playerConfig.getInt(name + ".LastOnline", today);
		int consecutiveDays = playerConfig.getInt(name + ".ConsecutiveDays", 0);
		int totalDays = playerConfig.getInt(name + ".TotalDays", 0);
		//String DOB = playerConfig.getString("Player.DOB", "None");
		
		if ( lastOnline >= today ) {
			return;
		} else if ( lastOnline >= yesterday ) {
			consecutiveDays++;
			totalDays++;
			
			if ( (Lang.NewDay != null) && (Lang.NewDay != "") && (Settings.UseMessages) ) {
				String newday = Lang.NewDay;
				newday = newday.replace("%c", Integer.toString(consecutiveDays));
				newday = newday.replace("%t", Integer.toString(totalDays));
				
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', newday));
			}
			
			if ( (Lang.Totals != null) && (Lang.Totals != "") && (Settings.UseMessages) ) {
				String total = Lang.Totals;
				total = total.replace("%c", Integer.toString(consecutiveDays));
				total = total.replace("%t", Integer.toString(totalDays));
				
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', total));
			}
			
			playerConfig.set(name + ".LastOnline", today);
			playerConfig.set(name + ".ConsecutiveDays", consecutiveDays);
			playerConfig.set(name + ".TotalDays", totalDays);
			
			try {
				playerConfig.save(playerFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			//Check Daily reward
			DailyReward.CheckDailyReward(player);
			//Check for consecutive day reward
			ConsecutiveRewards.CheckConsecutiveReward(consecutiveDays, player);
			//Check for total days reward
			TotalDaysRewards.CheckTotalDaysReward(totalDays, player);
			//TODO Check DOB with current date and check for reward
			
		} else {
			consecutiveDays = 1;
			totalDays++;
			
			if ( (Lang.NewDay != null) && (Lang.NewDay != "") && (Settings.UseMessages) ) {
				String newday = Lang.NewDay;
				newday = newday.replace("%c", Integer.toString(consecutiveDays));
				newday = newday.replace("%t", Integer.toString(totalDays));
				
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', newday));
			}
			
			if ( (Lang.Totals != null) && (Lang.Totals != "") && (Settings.UseMessages) ) {
				String total = Lang.Totals;
				total = total.replace("%c", Integer.toString(consecutiveDays));
				total = total.replace("%t", Integer.toString(totalDays));
				
				player.sendMessage( ChatColor.translateAlternateColorCodes('&', total));
			}
			
			playerConfig.set(name + ".LastOnline", today);
			playerConfig.set(name + ".ConsecutiveDays", consecutiveDays);
			playerConfig.set(name + ".TotalDays", totalDays);
			
			try {
				playerConfig.save(playerFile);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			
			//Check Daily reward
			DailyReward.CheckDailyReward(player);
			//Check for total days reward
			TotalDaysRewards.CheckTotalDaysReward(totalDays, player);
			//TODO Check DOB and Anniv with current date and check for reward
		}
		
		
		
	}
}
