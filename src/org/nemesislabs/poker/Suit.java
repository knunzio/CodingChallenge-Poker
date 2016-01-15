package org.nemesislabs.poker;

public enum Suit {
	HEART("H"),
	SPADE("S"),
	DIAMOND("D"),
	CLUB("C");
	
	private final String id;
	Suit(String id){ 
		this.id = id;
	};
	
	public String toString(){
		return this.id;
	}
}
