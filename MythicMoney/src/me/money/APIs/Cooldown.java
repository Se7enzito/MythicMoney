package me.money.APIs;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.entity.Player;

public class Cooldown {
	public static HashMap<UUID, Double> cooldowns;

	public static void setupCooldown() {
		cooldowns = new HashMap<>();
	}

	public static void setCooldown(Player player, int seconds) {
		double delay = (System.currentTimeMillis() + (seconds * 1000));
		cooldowns.put(player.getUniqueId(), Double.valueOf(delay));
	}

	public static int getCooldown(Player player) {
		return Math.toIntExact(Math.round(
				(((Double) cooldowns.get(player.getUniqueId())).doubleValue() - System.currentTimeMillis()) / 1000.0D));
	}

	public static boolean checkCooldown(Player player) {
		if (!cooldowns.containsKey(player.getUniqueId())
				|| ((Double) cooldowns.get(player.getUniqueId())).doubleValue() <= System.currentTimeMillis())
			return true;
		return false;
	}
}