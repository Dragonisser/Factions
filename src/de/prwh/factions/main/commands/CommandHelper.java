package de.prwh.factions.main.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prwh.factions.main.CreateConfig;
import de.prwh.factions.main.FactionsMain;
import de.prwh.factions.main.factions.Faction;
import de.prwh.factions.main.factions.FactionHelper;

public class CommandHelper implements CommandExecutor {

	@SuppressWarnings("unused")
	private CreateConfig config;
	private FactionsMain main;

	public CommandHelper(FactionsMain main, CreateConfig config) {
		this.config = config;
		this.main = main;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FactionHelper helper = FactionHelper.getInstance();
		
		if(sender instanceof Player) {
			Player player = (Player)sender;
			
			if(args.length == 2 && args[0].equalsIgnoreCase("create")) {
				if(helper.createNewFaction(args[1], player.getUniqueId())) {
					sender.sendMessage("Creating Faction '" + args[1] + "'");
					return true;
				}
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
				if(helper.isFactionMember(player.getUniqueId())) {
					sender.sendMessage("You are aready in a Faction");
					return true;
				} else {
					if(helper.joinFaction(args[1], player.getUniqueId())) {
						sender.sendMessage("Joined Faction '" + args[1] + "'");
						return true;
					}
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("leave")) {
				if(helper.isFactionOwner(player.getUniqueId())) {
					sender.sendMessage("You cant leave a Faction if you're the owner. Disband it or change the Ownership");
					return true;
				} else {
					if(helper.leaveFaction(player.getUniqueId())) {
						sender.sendMessage("Left the Faction");
						helper.cleanUpFactionList();
						return true;
					} else {
						sender.sendMessage("You are in no Faction");
						return true;
					}
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
				sender.sendMessage(helper.getFactionlist().toString());
				return true;
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("info")) {
				Faction fac = helper.getFactionByName(args[1]);
				sender.sendMessage(fac.getFactionName());
				sender.sendMessage(fac.getMembers().toString());
				sender.sendMessage(fac.getFactionOwner().toString());
				return true;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("save")) {
				if(helper.saveFactionList()) {
					sender.sendMessage("Saving FactionList");
					return true;
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("load")) {
				if(helper.loadFactionList()) {
					sender.sendMessage("Loading FactionList");
					return true;
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("reload")) {
				main.reloadPlugin();
				sender.sendMessage("Reload Factions");
				return true;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("tojson")) {
				sender.sendMessage(helper.toJson());
				return true;
			}
		}
		return false;
	}

}
