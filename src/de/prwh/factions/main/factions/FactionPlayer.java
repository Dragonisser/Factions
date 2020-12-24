package de.prwh.factions.main.factions;

import java.util.UUID;

public class FactionPlayer {

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
}
