package me.money.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.money.APIs.money.MoneyAPI;

public class MoneyEvents implements Listener {
	
	@EventHandler
	public void playerJoinEvent(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		
		MoneyAPI.adicionarPlayerComVerificacao(player.getUniqueId().toString());
	}

}
