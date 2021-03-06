package com.google.payne.tk.john.dailyloginrewards.configuration;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import com.google.payne.tk.john.dailyloginrewards.DailyLoginRewards;

public class Lang {
	static FileConfiguration LangConfig = null;
	static File LangFile = new File(DailyLoginRewards.main.getDataFolder(), "Lang.yml");
	
	public static String NewDay = null;
	public static String Totals = null;
	public static String ConsecutivePay = null;
	public static String TotalPay = null;
	public static String DailyPay = null;
	public static String CombinedTotals = null;
	
	public static void LoadLang() {
		//Set to default values if no Lang.yml exists
		if ( !LangFile.exists() ) {
			LangConfig = new YamlConfiguration();
			LangConfig.set("Strings.NewDay", "&4&oA new day has begun...");
			LangConfig.set("Strings.Totals", "&4&oYou've logged in for %c consecutive day(s) and %t total days!");
			LangConfig.set("Strings.ConsecutivePay", "&c&lYou received the consecutive login reward $%m");
			LangConfig.set("Strings.TotalPay", "&9&lYou received the total days logged in reward $%m");
			LangConfig.set("Strings.DailyPay", "&e&lYou received the daily login reward $%m");
			LangConfig.set("Strings.CombinedTotals", "&1&lYou received the reward $%g!");

			try {
				LangConfig.save(LangFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			//Load from file if it already existed
			LangConfig = YamlConfiguration.loadConfiguration(LangFile);
		}
		
		
		
		CombinedTotals = LangConfig.getString("Strings.CombinedTotals", "&9&lYou received the reward $%g!");
		
		if ( Settings.UseMessages ) {
			NewDay = LangConfig.getString("Strings.NewDay", null);
			Totals = LangConfig.getString("Strings.Totals", null);
			TotalPay = LangConfig.getString("Strings.TotalPay", null);
			ConsecutivePay = LangConfig.getString("Strings.ConsecutivePay", null);
			DailyPay = LangConfig.getString("Strings.DailyPay", null);
			
		} else {
			NewDay = null;
			Totals = null;
			ConsecutivePay = null;
			TotalPay = null;
			DailyPay = null;
		}
		
		LangConfig.set("Strings.CombinedTotals", CombinedTotals);
		
		try {
			LangConfig.save(LangFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
