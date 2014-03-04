package com.google.payne.tk.john.dailyloginrewards.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;
import com.google.payne.tk.john.dailyloginrewards.util.TimeDate;

public class Settings {
	public static int Today = 0;
	public static int Yesterday = 0;
	public static boolean UsePermissions = false;
	public static boolean UseMessages = true;
	public static boolean UseCombineTotals = true;
	public static long delay = 200L;
	public static String timezone = "GMT-0500";
	
	public static void LoadConfig() {
		FileConfiguration config = null;
		File fileConfig = new File(DailyLoginRewards.main.getDataFolder(), "config.yml");
		
		if( !fileConfig.exists() ) {
			config = new YamlConfiguration();
			
			//Set primitive data to defaults
			Today = TimeDate.getTodaysDate();
			Yesterday = TimeDate.getYesterdaysDate();
			UsePermissions = false;
			UseMessages = true;
			delay = 300L;
			timezone = "GMT-0500";
			UseCombineTotals = true;
			
			//Set default configuration
			config.set("dailyloginrewards.version", 1);
			config.set("dailyloginrewards.variables.today", Today);
			config.set("dailyloginrewards.variables.yesterday", Yesterday);
			config.set("dailyloginrewards.variables.delay", delay);
			config.set("dailyloginrewards.variables.timezone", timezone);
			config.set("dailyloginrewards.booleans.usePermissions", UsePermissions);
			config.set("dailyloginrewards.booleans.useMessages", UseMessages);
			config.set("dailyloginrewards.booleans.useCombinedTotalsMessage", UseCombineTotals);
			
		} else {
			config = YamlConfiguration.loadConfiguration(fileConfig);
			
			timezone = config.getString("dailyloginrewards.variables.timezone", timezone);
			
			int today = config.getInt("dailyloginrewards.variables.today", TimeDate.getTodaysDate());
			Today = TimeDate.getTodaysDate();
			
			//Check if today is a new day 	
			if ( today != Today ) {
				//"Yesterday" becomes the last "today." This allows for compensation due to server down-time.
				Yesterday = today;
			} else {
				//Get "yesterday" from the config when it is not a new day.
				Yesterday = config.getInt("dailyloginrewards.variables.yesterday", TimeDate.getYesterdaysDate());
			}
			
			UsePermissions = config.getBoolean("dailyloginrewards.booleans.usePermissions");
			UseMessages = config.getBoolean("dailyloginrewards.booleans.useMessages", UseMessages);
			
			
			delay = config.getLong("dailyloginrewards.variables.delay", 300);
			if ( delay < 1L ) {
				delay = 1L;
			}
			
			UseCombineTotals = config.getBoolean("dailyloginrewards.booleans.useCombinedTotalsMessage", false);
			
			config.set("dailyloginrewards.variables.today", Today);
			config.set("dailyloginrewards.variables.yesterday", Yesterday);
			config.set("dailyloginrewards.variables.delay", delay);
			config.set("dailyloginrewards.variables.timezone", timezone);
			config.set("dailyloginrewards.booleans.usePermissions", UsePermissions);
			config.set("dailyloginrewards.booleans.useCombinedTotalsMessage", UseCombineTotals);
			//removes caplitalized UseMessage from config in 0.1
			config.set("dailyloginrewards.booleans.UseMessages", null);
			config.set("dailyloginrewards.booleans.useMessages", UseMessages);
		}
		try {
			config.save(fileConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
