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
			
			//Set default configuration
			config.set("dailyloginrewards.version", 1);
			config.set("dailyloginrewards.variables.today", Today);
			config.set("dailyloginrewards.variables.yesterday", Yesterday);
			config.set("dailyloginrewards.booleans.usePermissions", UsePermissions);
			config.set("dailyloginrewards.booleans.UseMessages", UseMessages);
			
		} else {
			config = YamlConfiguration.loadConfiguration(fileConfig);
			
			int today = config.getInt("dailyloginrewards.variables.today", TimeDate.getTodaysDate());
			Today = TimeDate.getTodaysDate();
			
			//Check if today is a new day 	
			if ( today != TimeDate.getTodaysDate() ) {
				//"Yesterday" becomes the last "today." This allows for compensation due to server down-time.
				Yesterday = today;
			} else {
				//Get "yesterday" from the config when it is not a new day.
				Yesterday = config.getInt("dailyloginrewards.variables.yesterday", TimeDate.getYesterdaysDate());
			}
			
			UsePermissions = config.getBoolean("dailyloginrewards.booleans.usePermissions");
			
			config.set("dailyloginrewards.variables.today", Today);
			config.set("dailyloginrewards.variables.yesterday", Yesterday);
			config.set("dailyloginrewards.booleans.usePermissions", UsePermissions);
		}
		try {
			config.save(fileConfig);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
