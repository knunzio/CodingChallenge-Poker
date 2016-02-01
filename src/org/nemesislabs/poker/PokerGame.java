package org.nemesislabs.poker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class PokerGame {

	public PokerGame() {
		// TODO Auto-generated constructor stub
	}

	final static int MAX_HANDS = 5;
	final static int CARDS_PER_HAND = 5;
	public static void main(String[] args) {


		List<Card> deck = new ArrayList<>();
		buildDeck(deck);
		Collections.shuffle(deck);

		//Deal 5 hands.
		List<Hand> hands = new ArrayList<>();
		dealHands(CARDS_PER_HAND, MAX_HANDS, deck, hands);

		//Print the hands;
		System.out.println("Delt Hands: ");
		printHands(hands);

        categorizeHands(hands);

		Collections.sort(hands,Hand.PokerHandComparator);
		System.out.println("Sorted Hands(High to Low): ");
		printHands(hands);

	}

	private static void dealHands(final int cardsPerHand, final int numberOfHands, List<Card> deck, List<Hand> hands){

        for(int i = 0; i < numberOfHands; i++){
            hands.add(new Hand());
        }

		for(int i = 0; i < (numberOfHands*cardsPerHand); i++){
		    hands.get(i%numberOfHands).add(deck.remove(0));
		}
	}

    private static void categorizeHands(List<Hand> hands) {
        for(Hand hand : hands) {
            hand.determineCategory();
        }
    }

	private static void buildDeck(List<Card> deck){
		//Build Deck
		for(Rank r : Rank.values()){
			for(Suit s : Suit.values()){
				deck.add(new Card(r,s));
			}
		}
	}

	private static void printHands(List<Hand> hands){
		for(int i = 0; i < hands.size(); i++){
			System.out.println("Hand " + i + ": " + hands.get(i)
					+ " Category: " + hands.get(i).getCategory().getName());
		}
	}
}
