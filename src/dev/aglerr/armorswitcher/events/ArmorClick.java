package dev.aglerr.armorswitcher.events;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import dev.aglerr.armorswitcher.ArmorSwitcher;
import dev.aglerr.armorswitcher.Utils;
import dev.aglerr.armorswitcher.XSound;

public class ArmorClick implements Listener{
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void playerRightClickArmor(PlayerInteractEvent e) {
		if (e.getItem() == null || !e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK) || (
			      !e.getItem().getType().name().endsWith("HELMET") && !e.getItem().getType().name().endsWith("CHESTPLATE") && !e.getItem().getType().name().endsWith("LEGGINGS") && !e.getItem().getType().name().endsWith("BOOTS")) || (
			      e.getItem().getType().name().endsWith("HELMET") && e.getPlayer().getInventory().getHelmet() == null) || (
			      e.getItem().getType().name().endsWith("CHESTPLATE") && e.getPlayer().getInventory().getChestplate() == null) || (
			      e.getItem().getType().name().endsWith("LEGGINGS") && e.getPlayer().getInventory().getLeggings() == null) || (
			      e.getItem().getType().name().endsWith("BOOTS") && e.getPlayer().getInventory().getBoots() == null)) {
			      return;
		}
		
		Player player = e.getPlayer();
		Location location = player.getLocation();
		ItemStack hand = null;
		ItemStack replace = null;
		FileConfiguration config = ArmorSwitcher.getInstance().getConfig();
		
		if(e.getItem().getType().name().endsWith("HELMET")) {
			if(config.getBoolean("use-permission")) {
				if(!(player.hasPermission("armorswitcher.helmet") || (!(player.hasPermission("armorswitcher.*"))))) {
					player.sendMessage(Utils.color(config.getString("messages.noPermission")));
					return;
				}
			}
			
			hand = ArmorSwitcher.getInstance().getPlayerItemInHand(hand, player);
			replace = player.getInventory().getHelmet();
			player.getInventory().setHelmet(hand);
			
			Optional<XSound> sound = XSound.matchXSound(config.getString("sounds.onArmorChange.name").toUpperCase());
			float volume = config.getInt("sounds.onArmorChange.volume");
			float pitch = config.getInt("sounds.onArmorChange.pitch");
			sound.ifPresent(sound1 -> sound1.playSound(location, volume, pitch));
			
		} else if(e.getItem().getType().name().endsWith("CHESTPLATE")) {
			if(config.getBoolean("use-permission")) {
				if(!(player.hasPermission("armorswitcher.chestplate") || (!(player.hasPermission("armorswitcher.*"))))) {
					player.sendMessage(Utils.color(config.getString("messages.noPermission")));
					return;
				}
			}
			
			hand = ArmorSwitcher.getInstance().getPlayerItemInHand(hand, player);
			replace = player.getInventory().getChestplate();
			player.getInventory().setChestplate(hand);
			
			Optional<XSound> sound = XSound.matchXSound(config.getString("sounds.onArmorChange.name").toUpperCase());
			float volume = config.getInt("sounds.onArmorChange.volume");
			float pitch = config.getInt("sounds.onArmorChange.pitch");
			sound.ifPresent(sound1 -> sound1.playSound(location, volume, pitch));
			
		} else if(e.getItem().getType().name().endsWith("LEGGINGS")) {
			if(config.getBoolean("use-permission")) {
				if(!(player.hasPermission("armorswitcher.leggings") || (!(player.hasPermission("armorswitcher.*"))))) {
					player.sendMessage(Utils.color(config.getString("messages.noPermission")));
					return;
				}
			}
			
			hand = ArmorSwitcher.getInstance().getPlayerItemInHand(hand, player);
			replace = player.getInventory().getLeggings();
			player.getInventory().setLeggings(hand);
			
			Optional<XSound> sound = XSound.matchXSound(config.getString("sounds.onArmorChange.name").toUpperCase());
			float volume = config.getInt("sounds.onArmorChange.volume");
			float pitch = config.getInt("sounds.onArmorChange.pitch");
			sound.ifPresent(sound1 -> sound1.playSound(location, volume, pitch));
			
		} else if(e.getItem().getType().name().endsWith("BOOTS")) {
			if(config.getBoolean("use-permission")) {
				if(!(player.hasPermission("armorswitcher.boots") || (!(player.hasPermission("armorswitcher.*"))))) {
					player.sendMessage(Utils.color(config.getString("messages.noPermission")));
					return;
				}
			}
			
			hand = ArmorSwitcher.getInstance().getPlayerItemInHand(hand, player);
			replace = player.getInventory().getBoots();
			player.getInventory().setBoots(hand);
			
			Optional<XSound> sound = XSound.matchXSound(config.getString("sounds.onArmorChange.name").toUpperCase());
			float volume = config.getInt("sounds.onArmorChange.volume");
			float pitch = config.getInt("sounds.onArmorChange.pitch");
			sound.ifPresent(sound1 -> sound1.playSound(location, volume, pitch));
			
		} else { return; }
		
		ArmorSwitcher.getInstance().setPlayerItemInHand(player, replace);
		player.updateInventory();
		
	}

}
