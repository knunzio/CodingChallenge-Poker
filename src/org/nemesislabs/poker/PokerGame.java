package org.nemesislabs.poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;


public class PokerGame {

	public PokerGame() {
		// TODO Auto-generated constructor stub
	}

	final static int MAX_HANDS = 5;
	final static int NUM_CARDS = 52;
	final static int CARDS_PER_HAND = 5;
	public static void main(String[] args) {

		//Build Deck
		List<Card> deck = new ArrayList<>();
		for(Rank r : Rank.values()){
			for(Suit s : Suit.values()){
			  deck.add(new Card(r,s));
			}
		}
		
		//Deal 5 hands.
		Random rand = new Random();
		List<Hand> hands = new ArrayList<>(MAX_HANDS);
		for(int ii = 0; ii < MAX_HANDS;  ii++){
			List <Card>cardsDelt = new ArrayList<>();
			for(int jj = 0; jj < CARDS_PER_HAND; jj++){
				int cardIndex = rand.nextInt(deck.size());
				cardsDelt.add(deck.get(cardIndex));
				deck.remove(cardIndex);
			}
			
			try {
				hands.add(new Hand(cardsDelt));
			} catch (Exception e) {
				System.out.println("Not enough cards for a hand.");
				e.printStackTrace();
			}
		}

		//Print the hands;
		System.out.println("Delt Hands: ");
		int handCount = 0;
		for(Hand hand : hands){
			System.out.println("Hand_"+handCount+ ": " + hand);
			handCount++;
		}
		
		Collections.sort(hands,Hand.PokerHandComparator);
		
		System.out.println("Sorted Hands(High to Low): ");
		handCount = 0;
		for(Hand hand : hands){
			System.out.println("Hand "+handCount+ ": " + hand
					+ " Category: " + hand.getCategory().getName());
			handCount++;
		}
	}
}
