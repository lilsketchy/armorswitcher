package dev.aglerr.armorswitcher;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import dev.aglerr.armorswitcher.events.ArmorClick;

public class ArmorSwitcher extends JavaPlugin{
	
	private static ArmorSwitcher instance;
	
	@SuppressWarnings("unused")
	@Override
	public void onEnable() {
		
		int pluginId = 7050;
		MetricsLite metrics = new MetricsLite(this, pluginId);
		
		this.saveDefaultConfig();
		this.getConfig().options().copyDefaults(true);
		
		instance = this;
		
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ArmorClick(), this);
		
	}
	
	@Override
	public void onDisable() {
		
	}

	public static ArmorSwitcher getInstance() { return instance; }
	
	@SuppressWarnings("deprecation")
	public ItemStack getPlayerItemInHand(ItemStack stack, Player player) {
		if(Bukkit.getVersion().contains("1.8")) {
			stack = player.getInventory().getItemInHand();
			return stack;
			
		} else if(Bukkit.getVersion().contains("1.9") ||Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11") ||Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16")){
			
			stack = player.getInventory().getItemInMainHand();
			return stack;
			
		}
		
		return stack;
	}
	
	@SuppressWarnings("deprecation")
	public void setPlayerItemInHand(Player player, ItemStack stack) {
		if(Bukkit.getVersion().contains("1.8")) {
			player.getInventory().setItemInHand(stack);
			
		} else if(Bukkit.getVersion().contains("1.9") ||Bukkit.getVersion().contains("1.10") || Bukkit.getVersion().contains("1.11") ||Bukkit.getVersion().contains("1.12") || Bukkit.getVersion().contains("1.13") || Bukkit.getVersion().contains("1.14") || Bukkit.getVersion().contains("1.15") || Bukkit.getVersion().contains("1.16")){
			player.getInventory().setItemInMainHand(stack);
			
		}
		
	}

}
