package de.prwh.factions.main.factions;

import java.io.Serializable;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class FactionPlayer implements Serializable {

	private static final long serialVersionUID = 6062768722364086593L;
	private UUID playerUUID;
	private FactionPlayerType factionPlayerType;
	
	public FactionPlayer(UUID playerUUID) {
		this.setPlayerUUID(playerUUID);
		this.setFactionPlayerType(FactionPlayerType.MEMBER);
	}
	
	public FactionPlayer(UUID playerUUID, FactionPlayerType factionPlayerType) {
		this.setPlayerUUID(playerUUID);
		this.setFactionPlayerType(factionPlayerType);
	}
	
	public UUID getPlayerUUID() {
		return playerUUID;
	}
	
	public Player getPlayer() {
		return Bukkit.getPlayer(playerUUID);
	}

	private void setPlayerUUID(UUID playerUUID) {
		this.playerUUID = playerUUID;
	}

	public FactionPlayerType getFactionPlayerType() {
		return factionPlayerType;
	}

	public void setFactionPlayerType(FactionPlayerType factionPlayerType) {
		this.factionPlayerType = factionPlayerType;
	}

	public enum FactionPlayerType {
		MEMBER("Member"),
		MODERATOR("Moderator"),
		OWNER("Owner");

		private String type;
		
		FactionPlayerType(String type) {
			this.type = type;
		}
		
		public String getType() {
			return this.type;
		}
		
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FactionPlayer) {
			FactionPlayer fp = (FactionPlayer) obj;
			return this.playerUUID.equals(fp.getPlayerUUID());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getPlayer().getName() + " - " + this.getFactionPlayerType().name();
		
	}
}
