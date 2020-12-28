package de.prwh.factions.main.factions;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.prwh.factions.main.factions.FactionPlayer.FactionPlayerType;

public class Faction implements Serializable {

	private static final long serialVersionUID = -8011089667849474144L;
	private String name = "";
	private String title = "";
	private String description = "";
	private ArrayList<FactionPlayer> factionPlayers = new ArrayList<FactionPlayer>();
	private UUID factionOwnerUUID;
	
	public Faction(String name, UUID factionOwnerUUID) {
		this.name = name;
		this.factionOwnerUUID = factionOwnerUUID;
		addMember(factionOwnerUUID, FactionPlayerType.OWNER);
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
		addMember(player.getUniqueId(), FactionPlayerType.MEMBER);
	}
	
	public void addMember(UUID uuid, FactionPlayerType playerType) {
		FactionPlayer fp = new FactionPlayer(uuid, playerType);
		factionPlayers.add(fp);
	}
	
	public void removeMember(Player player) {
		removeMember(player.getUniqueId());
	}
	
	public void removeMember(UUID playerUUID) {
		factionPlayers.removeIf(fp -> fp.getPlayerUUID().equals(playerUUID));
	}
	
	public boolean checkIfFactionIsForRemoval() {
		if(this.getMembers().size() == 0) {
			return true;
		}
		return false;
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
	
	@Override
	public String toString() {
		return this.getFactionName();		
	}
}
