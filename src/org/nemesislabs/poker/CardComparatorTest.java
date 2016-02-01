package org.nemesislabs.poker;
import org.junit.Assert;
import org.junit.Test;

public class CardComparatorTest {

	@Test
	public void testStraightFlush(){
		Hand hand = new Hand();
		hand.add(new Card(Rank.TEN,Suit.CLUB));
		hand.add(new Card(Rank.KING,Suit.CLUB));
		hand.add(new Card(Rank.ACE,Suit.CLUB));
		hand.add(new Card(Rank.QUEEN,Suit.CLUB));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.STRAIGHT_FLUSH);
	}

	@Test
	public void testNotStraightFlush(){
		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.KING,Suit.CLUB));
		hand.add(new Card(Rank.ACE,Suit.CLUB));
		hand.add(new Card(Rank.QUEEN,Suit.CLUB));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertFalse(hand.getCategory() == Category.STRAIGHT_FLUSH);
	}

	@Test
	public void test4OfAKind(){
		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		hand.add(new Card(Rank.EIGHT,Suit.SPADE));
		hand.add(new Card(Rank.EIGHT,Suit.HEART));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.FOUR_OF_A_KIND);
	}

	@Test
	public void testFullHouse(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		hand.add(new Card(Rank.EIGHT,Suit.SPADE));
		hand.add(new Card(Rank.JACK,Suit.SPADE));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.FULL_HOUSE);
	}

	@Test
	public void testFlush(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.THREE,Suit.CLUB));
		hand.add(new Card(Rank.TWO,Suit.CLUB));
		hand.add(new Card(Rank.KING,Suit.CLUB));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.FLUSH);
	}

	@Test
	public void testStraight(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.SEVEN,Suit.CLUB));
		hand.add(new Card(Rank.SIX,Suit.CLUB));
		hand.add(new Card(Rank.FIVE,Suit.CLUB));
		hand.add(new Card(Rank.FOUR,Suit.DIAMOND));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.STRAIGHT);
	}

	@Test
	public void testStraightFlushRandom(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.SEVEN,Suit.CLUB));
		hand.add(new Card(Rank.SIX,Suit.CLUB));
		hand.add(new Card(Rank.FIVE,Suit.CLUB));
		hand.add(new Card(Rank.FOUR,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.STRAIGHT_FLUSH);
	}

	@Test
	public void testThreeOfAKind(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		hand.add(new Card(Rank.EIGHT,Suit.SPADE));
		hand.add(new Card(Rank.FIVE,Suit.CLUB));
		hand.add(new Card(Rank.FOUR,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.THREE_OF_A_KIND);
	}

	@Test
	public void testTwoPair(){

		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		hand.add(new Card(Rank.FIVE,Suit.CLUB));
		hand.add(new Card(Rank.FIVE,Suit.HEART));
		hand.add(new Card(Rank.FOUR,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.TWO_PAIR);
	}

	@Test
	public void testOnePair(){
		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.EIGHT,Suit.DIAMOND));
		hand.add(new Card(Rank.KING,Suit.CLUB));
		hand.add(new Card(Rank.TWO,Suit.HEART));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.ONE_PAIR);
	}

	@Test
	public void testHighCard(){
		Hand hand = new Hand();
		hand.add(new Card(Rank.EIGHT,Suit.CLUB));
		hand.add(new Card(Rank.FIVE,Suit.DIAMOND));
		hand.add(new Card(Rank.KING,Suit.CLUB));
		hand.add(new Card(Rank.TWO,Suit.HEART));
		hand.add(new Card(Rank.JACK,Suit.CLUB));
		hand.determineCategory();
		Assert.assertTrue(hand.getCategory() == Category.HIGH_CARD);
	}
}
