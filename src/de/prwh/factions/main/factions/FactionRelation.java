package de.prwh.factions.main.factions;

import java.io.Serializable;

public class FactionRelation implements Serializable {
	
	private static final long serialVersionUID = 4851510915091159414L;
	private Faction faction;
	private FactionRelationStatus status;
	
	public FactionRelation(Faction faction, FactionRelationStatus status) {
		this.setFaction(faction);
		this.setStatus(status);
	}
	
	public Faction getFaction() {
		return faction;
	}

	public void setFaction(Faction faction) {
		this.faction = faction;
	}
	
	public FactionRelationStatus getStatus() {
		return status;
	}

	public void setStatus(FactionRelationStatus status) {
		this.status = status;
	}

	public enum FactionRelationStatus {
		NEUTRAL("Neutral"),
		TRUCE("Truce"),
		ALLIED("Allied"),
		ENEMY("Enemy");

		private String type;
		
		FactionRelationStatus(String type) {
			this.type = type;
		}
		
		public String getType() {
			return this.type;
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof FactionRelation) {
			FactionRelation fr = (FactionRelation) obj;
			return this.getFaction().equals(fr.getFaction());
		}
		return false;
	}
	
	@Override
	public String toString() {
		return this.getFaction().getFactionName() + " - " + this.getStatus().getType();
		
	}
}
