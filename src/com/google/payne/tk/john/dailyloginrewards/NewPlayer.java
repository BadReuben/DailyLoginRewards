package com.google.payne.tk.john.dailyloginrewards;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import com.google.payne.tk.john.dailyloginrewards.configuration.ConsecutiveRewards;
import com.google.payne.tk.john.dailyloginrewards.configuration.DailyReward;
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
		DailyReward.CheckDailyReward(player);
		//Check for consecutive day reward
		ConsecutiveRewards.CheckConsecutiveReward(1, player);
		//TCheck for total days reward
		TotalDaysRewards.CheckTotalDaysReward(1, player);
		//TCheck DOB with current date and check for reward
	}
}
