package org.nemesislabs.poker;
import java.util.Comparator;


public class Card implements Comparable<Card>{

	private Rank rank = null;
	private Suit suit = null;
	
	public Card(Rank rank, Suit suit) {
		this.rank = rank;
		this.suit = suit;
	}
	
	public  Rank getRank(){
		return rank;
	}
	
	public Suit getSuit(){
		return suit;
	}

	@Override
	public int compareTo(Card card) {
		return this.getRank().getRankInteger() - card.getRank().getRankInteger();
	}
	
	public boolean equalsRanks(Card c){
		return this.getRank().equals(c.getRank()) ? true : false;
	}
	
	public boolean equalsSuit(Card c){
		return this.getSuit().equals(c.getSuit()) ? true : false;
	}
	
	public String toString(){
		return this.rank.toString() + this.suit.toString();
	}
	
	public static Comparator<Card> CardComparator = new Comparator<Card>() {

        public int compare(Card card1, Card card2) {
            return card1.compareTo(card2);
        }

    };	
}
