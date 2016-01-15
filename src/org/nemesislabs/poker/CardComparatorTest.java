package org.nemesislabs.poker;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

public class CardComparatorTest {

	@Test
	public void testStraightFlush(){
		
		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.TEN,Suit.CLUB));
		cardList.add(new Card(Rank.KING,Suit.CLUB));
		cardList.add(new Card(Rank.ACE,Suit.CLUB));
		cardList.add(new Card(Rank.QUEEN,Suit.CLUB));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.STRAIGHT_FLUSH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testNotStraightFlush(){
		
		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.KING,Suit.CLUB));
		cardList.add(new Card(Rank.ACE,Suit.CLUB));
		cardList.add(new Card(Rank.QUEEN,Suit.CLUB));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertFalse(hand.getCategory() == Category.STRAIGHT_FLUSH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test4OfAKind(){
		
		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		cardList.add(new Card(Rank.EIGHT,Suit.SPADE));
		cardList.add(new Card(Rank.EIGHT,Suit.HEART));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.FOUR_OF_A_KIND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFullHouse(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		cardList.add(new Card(Rank.EIGHT,Suit.SPADE));
		cardList.add(new Card(Rank.JACK,Suit.SPADE));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.FULL_HOUSE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testFlush(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.THREE,Suit.CLUB));
		cardList.add(new Card(Rank.TWO,Suit.CLUB));
		cardList.add(new Card(Rank.KING,Suit.CLUB));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.FLUSH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStraight(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.SEVEN,Suit.CLUB));
		cardList.add(new Card(Rank.SIX,Suit.CLUB));
		cardList.add(new Card(Rank.FIVE,Suit.CLUB));
		cardList.add(new Card(Rank.FOUR,Suit.DIAMOND));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.STRAIGHT);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testStraightFlushRandom(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.SEVEN,Suit.CLUB));
		cardList.add(new Card(Rank.SIX,Suit.CLUB));
		cardList.add(new Card(Rank.FIVE,Suit.CLUB));
		cardList.add(new Card(Rank.FOUR,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.STRAIGHT_FLUSH);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testThreeOfAKind(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		cardList.add(new Card(Rank.EIGHT,Suit.SPADE));
		cardList.add(new Card(Rank.FIVE,Suit.CLUB));
		cardList.add(new Card(Rank.FOUR,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.THREE_OF_A_KIND);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testTwoPair(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		cardList.add(new Card(Rank.FIVE,Suit.CLUB));
		cardList.add(new Card(Rank.FIVE,Suit.HEART));
		cardList.add(new Card(Rank.FOUR,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.TWO_PAIR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testOnePair(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		cardList.add(new Card(Rank.KING,Suit.CLUB));
		cardList.add(new Card(Rank.TWO,Suit.HEART));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.ONE_PAIR);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void testHighCard(){

		List<Card> cardList = new ArrayList<>();
		cardList.add(new Card(Rank.EIGHT,Suit.CLUB));
		cardList.add(new Card(Rank.FIVE,Suit.DIAMOND));
		cardList.add(new Card(Rank.KING,Suit.CLUB));
		cardList.add(new Card(Rank.TWO,Suit.HEART));
		cardList.add(new Card(Rank.JACK,Suit.CLUB));
		Hand hand = null;
		try {
			hand = new Hand(cardList);
			Assert.assertTrue(hand.getCategory() == Category.HIGH_CARD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
