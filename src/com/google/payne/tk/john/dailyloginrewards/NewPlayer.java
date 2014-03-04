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

public class NewPlayer {

	public static void createNewPlayer(Player player, File playerFile) {
		FileConfiguration playerConfig = null;
		int today = TimeDate.getTodaysDate();
		
		playerConfig = new YamlConfiguration();
		
		String name = player.getName();
				
		playerConfig.set(name + ".LastOnline", today);
		playerConfig.set(name + ".ConsecutiveDays", 1);
		playerConfig.set(name + ".TotalDays", 1);
		playerConfig.set(name + ".Anniversary", today);
		
		try {
			playerConfig.save(playerFile);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		//Check Daily reward
		int daily = DailyReward.CheckDailyReward(player);
		//Check for consecutive day reward
		int consecutive = ConsecutiveRewards.CheckConsecutiveReward(1, player);
		//TCheck for total days reward
		int total = TotalDaysRewards.CheckTotalDaysReward(1, player);
		//TCheck DOB with current date and check for reward
		int GrandTotal = daily + consecutive + total;
		
		if ( (GrandTotal > 0) && Settings.UseCombineTotals ) {
			player.sendMessage( ChatColor.translateAlternateColorCodes('&', 
					Lang.CombinedTotals.replace("%g", Integer.toString(GrandTotal))) );
		}
	}
}
