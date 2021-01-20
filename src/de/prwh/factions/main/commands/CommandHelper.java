package de.prwh.factions.main.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.prwh.factions.main.FactionsMain;
import de.prwh.factions.main.factions.Faction;
import de.prwh.factions.main.factions.FactionHelper;
import de.prwh.factions.main.players.PlayerHelper;

public class CommandHelper implements CommandExecutor {

	private FactionsMain main;

	public CommandHelper(FactionsMain main) {
		this.main = main;
	}
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		FactionHelper factionHelper = FactionHelper.getInstance();
		PlayerHelper playerHelper = PlayerHelper.getInstance();
		
		if(sender instanceof Player) {
			Player player = (Player)sender;

			if(args.length == 2 && args[0].equalsIgnoreCase("create")) {
				if(factionHelper.createNewFaction(args[1], player.getUniqueId())) {
					sender.sendMessage("Creating Faction '" + args[1] + "'");
					return true;
				}
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("join")) {
//				if(playerHelper.isFactionMember(player.getUniqueId())) {
//					sender.sendMessage("You are aready in a Faction");
//					return true;
//				} else {
//					if(factionHelper.joinFaction(args[1], player.getUniqueId())) {
//						sender.sendMessage("Joined Faction '" + args[1] + "'");
//						return true;
//					}
//				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("leave")) {
//				if(playerHelper.isFactionOwner(player.getUniqueId())) {
//					sender.sendMessage("You cant leave a Faction if you're the owner. Disband it or change the Ownership");
//					return true;
//				} else {
//					if(factionHelper.leaveFaction(player.getUniqueId())) {
//						sender.sendMessage("Left the Faction");
//						factionHelper.cleanUpFactionList();
//						return true;
//					} else {
//						sender.sendMessage("You are in no Faction");
//						return true;
//					}
//				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("disband")) {
//				if(playerHelper.isFactionOwner(player.getUniqueId())) {
//					sender.sendMessage("Disbanding Faction");
//					factionHelper.removeOwnedFaction(player.getUniqueId());
//				} else {
//					sender.sendMessage("You are not an owner of a Faction");
//				}
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("changeOwner")) {
//				if(playerHelper.isFactionOwner(player.getUniqueId())) {
//					Faction faction = playerHelper.getFactionByOwner(player.getUniqueId());
//					UUID uuid = Bukkit.getPlayer(args[1]).getUniqueId();
//					if(uuid != null) {
//						if(uuid.equals(player.getUniqueId())) {
//							sender.sendMessage("You are already the owner of the Faction");
//							return true;
//						} else {
//							sender.sendMessage("Changing ownership of Faction");
//							factionHelper.changeFactionOwner(uuid, faction);
//							return true;
//						}
//					} else {
//						sender.sendMessage("User not found or not member of Faction");
//						return true;
//					}
//				} else {
//					sender.sendMessage("You are not an owner of a Faction");
//					return true;
//				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("list")) {
				sender.sendMessage(factionHelper.getFactionlist().toString());
				return true;
			}
			if(args.length == 2 && args[0].equalsIgnoreCase("info")) {
				Faction fac = factionHelper.getFactionByName(args[1]);
				sender.sendMessage(fac.getFactionName());
				sender.sendMessage(fac.getMembers().toString());
				sender.sendMessage(fac.getFactionOwner().toString());
				return true;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("save")) {
				if(factionHelper.saveFactionList()) {
					sender.sendMessage("Saving FactionList");
					return true;
				}
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("load")) {
				if(factionHelper.loadFactionList()) {
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
				sender.sendMessage(factionHelper.toJson());
				return true;
			}
		}
		return false;
	}

}
