package org.nemesislabs.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hand implements Comparable<Hand>{

	private static final int MAX_NUM_CARDS = 5;	  
	private Category category = Category.UNDEFINED_CATEGORY;

	private List<List<Card>>categorizedHandList = new ArrayList<>();
	private boolean isFlush = true;
	private List<Card> cards = new ArrayList<>();

	private Map<HandAttributeKeys,Rank> highRankMap = new HashMap<>();

	/**
	 * Constructs a Hand instance from the list of cards.
	 * @param cards
	 *     - A valid list of Cards - size must == 5.
	 * @throws Exception
	 *     - If the number of cards is less 5;
	 */
	public Hand(final List<Card> cards) throws Exception{

	  if(MAX_NUM_CARDS != cards.size() ) {
		  throw new Exception("Invalid number of cards.");
	  }
      Collections.sort(cards, Card.CardComparator);
      this.cards = cards;
      init(this.cards);
	}

    /**
     * Builds the categorized hand list and determines flush flag.
     * @param cards
     */
	private void init(List<Card> cards){
		Suit suit = null;
		for (Card card : cards){

		    //Determine if this is a straight.
			if(null == suit){
				suit = card.getSuit();
			} else {
				if(suit != card.getSuit()){
					this.isFlush = false;
				}
			}

			//Organize cards in buckets.
			boolean foundRankBucket = false;
		    for(List rankList : categorizedHandList){
		 	   Rank listRank = ((Card)rankList.get(0)).getRank();
			   if(listRank == card.getRank()){
				   rankList.add(card);
				   foundRankBucket = true;
			   }
		    }
			if(!foundRankBucket){
				List<Card> newCardList = new ArrayList<>();
				newCardList.add(card);
				categorizedHandList.add(newCardList);
			}
		}
		determineCategory();
	}
	
	private void determineCategory() {

        final int STRAIGHT_AND_HIGH_CARD_LIST_SIZE = 5;
        final int ONE_PAIR_LIST_SIZE = 4;
        final int TWO_PAIR_AND_THREE_OF_A_KIND_LIST_SIZE = 3;
        final int FULL_HOUSE_AND_FOUR_OF_A_KIND_LIST_SIZE = 2;

		switch (categorizedHandList.size()){
			case STRAIGHT_AND_HIGH_CARD_LIST_SIZE:{

				//get  rank of first list of list element 0;
				Rank lowRank = categorizedHandList.get(0).get(0).getRank();
				//get rank of first element of last list;
				Rank highRank = categorizedHandList.get(categorizedHandList.size()-1).get(0).getRank();

				//Straight ACE low?
				if(highRank == Rank.ACE && lowRank == Rank.TWO){

					highRank = categorizedHandList.get(categorizedHandList.size()-2).get(0).getRank();
					if(lowRank.equals(Rank.TWO)
							&& ((highRank.getRankInteger() - lowRank.getRankInteger()) == 3)) {
						category = Category.STRAIGHT;
					} else {
                        category = Category.HIGH_CARD;
                        highRank = categorizedHandList.get(categorizedHandList.size()-1).get(0).getRank();
					}
				}
                //Straight?
				else if ((highRank.getRankInteger() - lowRank.getRankInteger()) == 4){
					if(isFlush){
						category = Category.STRAIGHT_FLUSH;
					} else {
						category = Category.STRAIGHT;
					}
				}
                else {
                    if(isFlush){
                        category = Category.FLUSH;
                    } else {
                        category = Category.HIGH_CARD;
                    }
				}

                //Save the high card rank for hand comparison.
                highRankMap.put(HandAttributeKeys.HIGH_CARD_RANK, highRank);
				break;
			}
			case ONE_PAIR_LIST_SIZE: {
				category = Category.ONE_PAIR;
				Rank pairRank = null;
				Rank highCardRank = Rank.TWO;
				//Save off the Pair Rank and High Card Rank.
				for (List<Card> rankGroup : this.categorizedHandList) {
					if (rankGroup.size() == 2) {
						//Get the rank of the pair.
						pairRank = rankGroup.get(0).getRank();
					} else {
						//Get the high rank of the remaining cards.
						if (rankGroup.get(0).getRank().getRankInteger() > highCardRank.getRankInteger()) {
							highCardRank = rankGroup.get(0).getRank();
						}
					}
				}
                //Save the pair and high card rank for same category comparison.
				highRankMap.put(HandAttributeKeys.PAIR_ONE_RANK, pairRank);
				highRankMap.put(HandAttributeKeys.HIGH_CARD_RANK, highCardRank);
				break;
			}
			case TWO_PAIR_AND_THREE_OF_A_KIND_LIST_SIZE: {
                category = Category.TWO_PAIR;

                //Sort the list in list size order to easily determine
                //two pair or three of a kind.
                Collections.sort(categorizedHandList, new Comparator<List<Card>>() {
                    @Override
                    public int compare(List<Card> list1, List<Card> list2) {
                        if( list2.size() == list1.size()){
                           return list2.get(0).getRank().getRankInteger() - list1.get(0).getRank().getRankInteger();
                        }
                        return list2.get(0).getRank().getRankInteger() - list1.get(0).getRank().getRankInteger();
                    }
                });

                if(categorizedHandList.get(0).size() == 3){
                    category = Category.THREE_OF_A_KIND;
                    highRankMap.put(HandAttributeKeys.THREE_OF_A_KIND_RANK,
                            categorizedHandList.get(0).get(0).getRank());

                } else {
                    category = Category.TWO_PAIR;
                    highRankMap.put(HandAttributeKeys.PAIR_ONE_RANK, categorizedHandList.get(0).get(0).getRank());
                    highRankMap.put(HandAttributeKeys.PAIR_TWO_RANK, categorizedHandList.get(1).get(0).getRank());
                    highRankMap.put(HandAttributeKeys.HIGH_CARD_RANK, categorizedHandList.get(2).get(0).getRank());
                }

				break;
			}
			case FULL_HOUSE_AND_FOUR_OF_A_KIND_LIST_SIZE: {
                for (List <Card>rankGroup : this.categorizedHandList) {
                    if (rankGroup.size() == 2 || rankGroup.size() == 3) {
                        category = Category.FULL_HOUSE;
                        if(3 == rankGroup.size()){
                            highRankMap.put(HandAttributeKeys.THREE_OF_A_KIND_RANK, rankGroup.get(0).getRank());
                        }
                    }else{
                        category = Category.FOUR_OF_A_KIND;
                        if(rankGroup.size() == 4) {
                            highRankMap.put(HandAttributeKeys.FOUR_OF_A_KIND_RANK,rankGroup.get(0).getRank());
                        }
                    }
                }
				break;
			}
			default:{
                System.out.println("Unable to determine hand category");
				break;
			}
		}
	}


	public Category getCategory(){
		return this.category;
	}
	
	@Override
	public int compareTo(Hand hand) {
		if(hand.getCategory().getRanking() == this.getCategory().getRanking()){
			switch(getCategory()){
                case STRAIGHT_FLUSH:
                case FLUSH:
                case HIGH_CARD:
                case STRAIGHT: {
                    return hand.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger()
                            - this.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger();
                }
                case FOUR_OF_A_KIND: {
                    return hand.highRankMap.get(HandAttributeKeys.FOUR_OF_A_KIND_RANK).getRankInteger()
                            - this.highRankMap.get(HandAttributeKeys.FOUR_OF_A_KIND_RANK).getRankInteger();
                }
                case FULL_HOUSE:
                case THREE_OF_A_KIND:{
                    return hand.highRankMap.get(HandAttributeKeys.THREE_OF_A_KIND_RANK).getRankInteger()
                            - this.highRankMap.get(HandAttributeKeys.THREE_OF_A_KIND_RANK).getRankInteger();
                }
                case TWO_PAIR: {
                    int rankDiff = hand.highRankMap.get(HandAttributeKeys.PAIR_ONE_RANK).getRankInteger()
                            - this.highRankMap.get(HandAttributeKeys.PAIR_ONE_RANK).getRankInteger();
                    //First pair is the same, compare second pair.
                    if(0 == rankDiff){
                        rankDiff = hand.highRankMap.get(HandAttributeKeys.PAIR_TWO_RANK).getRankInteger()
                                - this.highRankMap.get(HandAttributeKeys.PAIR_TWO_RANK).getRankInteger();
                    }
                    //Pairs are the same, compare high card.
                    if (0 == rankDiff){
                        rankDiff = hand.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger()
                                - this.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger();
                    }
                    return rankDiff;
                }
                case ONE_PAIR: {
                    int rankDiff = hand.highRankMap.get(HandAttributeKeys.PAIR_ONE_RANK).getRankInteger()
                            - this.highRankMap.get(HandAttributeKeys.PAIR_ONE_RANK).getRankInteger();
                    //Pair is the same, compare high card.
                    if(0 == rankDiff){
                        rankDiff = hand.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger()
                                - this.highRankMap.get(HandAttributeKeys.HIGH_CARD_RANK).getRankInteger();
                    }
                   return rankDiff;
                }
            }
		}
		return hand.getCategory().getRanking() - this.getCategory().getRanking();
	}
	
	public String toString(){
		return this.cards.toString();
	}

	public static Comparator<Hand> PokerHandComparator = new Comparator<Hand>() {

        public int compare(Hand hand1, Hand hand2) {
            return hand1.compareTo(hand2);
        }
    };	
}
