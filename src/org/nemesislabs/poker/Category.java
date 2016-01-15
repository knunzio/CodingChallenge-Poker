package org.nemesislabs.poker;


public enum Category {
	STRAIGHT_FLUSH(9, "STRAIGHT_FLUSH"),
	FOUR_OF_A_KIND(8,"FOUR_OF_A_KIND"),
	FULL_HOUSE(7,"FULL_HOUSE"),
	FLUSH(6,"FLUSH"),
	STRAIGHT(5,"STRAIGHT"),
	THREE_OF_A_KIND(4,"THREE_OF_A_KIND"),
	TWO_PAIR(3,"TWO_PAIR"),
	ONE_PAIR(2,"ONE_PAIR"),
	HIGH_CARD(1,"HIGH_CARD"),
	UNDEFINED_CATEGORY(0,"UNDEFINED_CATEGORY");
	
	private int ranking = 0;
	private String name = "UNDEFINED_CATEGORY";

	Category(int ranking, String name){
		this.ranking = ranking;
		this.name = name;
	}
	
	public int getRanking(){
		return this.ranking;
	}

	public String getName(){
		return this.name;
	}
}
