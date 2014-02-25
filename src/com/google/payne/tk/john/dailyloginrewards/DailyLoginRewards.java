package com.google.payne.tk.john.dailyloginrewards;

import net.milkbowl.vault.economy.Economy;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import com.google.payne.tk.john.dailyloginrewards.configuration.ConsecutiveRewards;
import com.google.payne.tk.john.dailyloginrewards.configuration.DailyReward;
import com.google.payne.tk.john.dailyloginrewards.configuration.Lang;
import com.google.payne.tk.john.dailyloginrewards.configuration.RewardsEconomy;
import com.google.payne.tk.john.dailyloginrewards.configuration.Settings;
import com.google.payne.tk.john.dailyloginrewards.configuration.TotalDaysRewards;
import com.google.payne.tk.john.dailyloginrewards.listeners.PlayerJoinListener;

public class DailyLoginRewards extends JavaPlugin
{
	private final PlayerJoinListener joinListener = new PlayerJoinListener();
	
	public static DailyLoginRewards main;
	public static Economy economy = null;
	
	@Override
	public void onDisable() {
		getServer().getScheduler().cancelTasks(this);
		PluginDescriptionFile pdfFile = this.getDescription();
		getLogger().info( pdfFile.getName() + " by " + pdfFile.getAuthors() + " V." +
				pdfFile.getVersion() + " disabled!" );
	}
	
	@Override
	public void onEnable() {	
		
		main = this;
		
		// Register events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(joinListener, this);
		
		//Load settings
		Settings.LoadConfig();
		setupEconomy();
		ConsecutiveRewards.LoadConsecutiveRewards();
		TotalDaysRewards.LoadTotalDaysRewards();
		DailyReward.LoadDailyRewards();
		Lang.LoadLang();
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label,
			String[] split) {	
		if ( label.equalsIgnoreCase("rewards") || label.equalsIgnoreCase("dailyloginrewards") || label.equalsIgnoreCase("dlr") ) {
			if ( (split.length == 0) || (split == null) ) {
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRewards | Reload"));
				return true;
			}
			switch (split[0].toLowerCase()) {
			//reload all configuration files
			case "reload":
				if (sender.hasPermission("DailyLoginRewards.reload")) {
					Settings.LoadConfig();
					DailyLoginRewards.setupEconomy();
					ConsecutiveRewards.LoadConsecutiveRewards();
					TotalDaysRewards.LoadTotalDaysRewards();
					DailyReward.LoadDailyRewards();
					Lang.LoadLang();
					sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lDailyLoginRewards reloaded!"));
					return true;
				}
			default:
				sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&d&lRewards | Reload"));
				return true;
			}
		
		}
		return false;
	}
	
	public static boolean setupEconomy() {
		if ( Bukkit.getServer().getPluginManager().getPlugin("Vault") == null ) {
			return false;
		}
		RegisteredServiceProvider<Economy> economyProvider = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
		if ( economyProvider != null) {
			economy = economyProvider.getProvider();
		}
		RewardsEconomy.ecoEnabled = true;
		return (economy != null);
	}
	
	
}
