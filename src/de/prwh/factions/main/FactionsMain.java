package de.prwh.factions.main;

import java.util.logging.LogManager;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import de.prwh.factions.main.commands.CommandHelper;
import de.prwh.factions.main.factions.FactionHelper;

public class FactionsMain extends JavaPlugin {

	BukkitScheduler autoSave = getServer().getScheduler();
	
	private CreateConfig cfg = new CreateConfig();
	public static final String PLUGINID = "ressourcetowers";
	private static final Logger log = LogManager.getLogManager().getLogger(PLUGINID.toUpperCase());
	private FactionHelper factionHelper = FactionHelper.getInstance();
	
	
	public void onEnable() {
		cfg.create(this);
		factionHelper.setFilePath(getDataFolder().getAbsolutePath());
		factionHelper.loadFactionList();
		factionHelper.cleanUpFactionList();
		startAutoSave();
		
		getCommand("factions").setExecutor(new CommandHelper(this, cfg));
	}
	
	private void startAutoSave() {
		autoSave.scheduleSyncRepeatingTask(this, new Runnable() {

			@Override
			public void run() {
				factionHelper.saveFactionList();
			}
		}, 0, cfg.getConfig().getInt("autoSaveTime") * 60 * 20);
	}
	
	public void restartScheduler() {
		autoSave.cancelTasks(this);

		cfg.getConfig();

		startAutoSave();
	}
	
	public void reloadPlugin() {
		restartScheduler();
		cfg.getConfig();
	}
	
	public void onDisable() {
		factionHelper.saveFactionList();
	}
	
	public static Logger getLoggerMain() {
		return log;
	}
	
	public static void sendToConsole(String message) {
		FactionsMain.getPlugin(FactionsMain.class).getServer().getConsoleSender().sendMessage(ChatColor.RED + "[Factions] " + ChatColor.GREEN + message);
	}
}
