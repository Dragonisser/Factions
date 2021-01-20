package de.prwh.factions.main.factions;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.prwh.factions.main.factions.FactionPlayer.FactionPlayerType;
import de.prwh.factions.main.factions.FactionRelation.FactionRelationStatus;
import de.prwh.factions.main.players.PlayerHelper;

public class Faction implements Serializable {

	private static final long serialVersionUID = -8011089667849474144L;
	private String name = "";
	private String title = "";
	private String description = "";
	private List<UUID> factionPlayers = new ArrayList<UUID>();
	private ArrayList<FactionRelation> factionRelations = new ArrayList<FactionRelation>();
	private PlayerHelper playerHelper = PlayerHelper.getInstance();
	private UUID factionOwnerUUID;
	
	public Faction(String name, UUID factionOwnerUUID) {
		this.name = name;
		this.factionOwnerUUID = factionOwnerUUID;
		addMember(factionOwnerUUID);
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
		addMember(player.getUniqueId());
	}
	
	public void addMember(UUID uuid) {
		factionPlayers.add(uuid);
	}
	
	public void removeMember(Player player) {
		removeMember(player.getUniqueId());
	}
	
	public void removeMember(UUID playerUUID) {
		factionPlayers.remove(playerUUID);
	}
	public void changeMemberRole(UUID uuid, FactionPlayerType playerType) {
		FactionPlayer fp = playerHelper.getFactionPlayer(uuid);
		if(fp != null) {
			fp.setFactionPlayerType(playerType);
		}
	}
	
	public boolean checkIfFactionIsForRemoval() {
		if(this.getMembers().size() == 0) {
			return true;
		}
		return false;
	}
	
	public List<UUID> getMembers() {
		return factionPlayers;
	}
	
	public void changeRelationship(Faction faction, FactionRelationStatus status) {
		FactionRelation relation = new FactionRelation(faction, status);
		factionRelations.add(relation);
	}
	
	public void removeRelation(Faction faction) {
		factionRelations.removeIf(fr -> fr.getFaction().equals(faction));
	}
	
	public ArrayList<FactionRelation> getFactionrelations() {
		return factionRelations;
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
	
	public void setOwner(UUID uuid) {
		this.factionOwnerUUID = uuid;
	}
	
	@Override
	public String toString() {
		return this.getFactionName();		
	}
}
