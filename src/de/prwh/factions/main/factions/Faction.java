package de.prwh.factions.main.factions;

import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Faction {

	private String name = "";
	private String title = "";
	private String description = "";
	private ArrayList<FactionPlayer> factionPlayers = new ArrayList<FactionPlayer>();
	private UUID factionOwnerUUID;
	
	public Faction(String name, UUID factionOwnerUUID) {
		this.name = name;
		this.factionOwnerUUID = factionOwnerUUID;
	}
	
	public String getFactionName() {
		return this.name;
	}
	
	public Player getFactionOwner() {
		return Bukkit.getPlayer(this.factionOwnerUUID);
	}
	
	public UUID getFactionOwnerUUID() {
		return this.factionOwnerUUID;
	}
	
	public void addMember(Player player) {
		FactionPlayer fp = new FactionPlayer(player.getUniqueId());
		factionPlayers.add(fp);
	}
	
	public void removeMember(Player player) {
		for(FactionPlayer fp : factionPlayers) {
			if(fp.getPlayerUUID().equals(player.getUniqueId())) {
				factionPlayers.remove(fp);
				if(getMembers().size() == 1) {
					FactionHelper helper = FactionHelper.getInstance();
					helper.removeFaction(this.getFactionName());
				}
			}
		}
	}
	
	public ArrayList<FactionPlayer> getMembers() {
		return factionPlayers;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Faction) {
			Faction f = (Faction) obj;
			
			if(this.name.equals(f.getFactionName()) || this.factionOwnerUUID.equals(f.getFactionOwnerUUID())) {
				return true;
			}
		}
		return false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
