package com.google.payne.tk.john.dailyloginrewards;

import java.io.File;

import org.bukkit.entity.Player;

public class DelayedJoinTask implements Runnable {
	
	Player player;
	File playerFile;
	
	public DelayedJoinTask(Player p, File pF) {
		player = p;
		playerFile = pF;
	}

	@Override
	public void run() {
		if ( !(player instanceof Player) || !(player.isOnline()) ) {
			return;
		}
		else if ( playerFile.exists() ) {
			ExistingPlayer.checkExistingPlayer(player, playerFile);
		} else {
			NewPlayer.createNewPlayer(player, playerFile);
		}
	}
}
