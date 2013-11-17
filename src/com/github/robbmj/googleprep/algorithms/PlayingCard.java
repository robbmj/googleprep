package com.github.robbmj.googleprep.algorithms;

import com.github.robbmj.googleprep.datastructures.BinaryTree;
import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Slice;

public class PlayingCard {

	public static final int CARDS_IN_DECK = 52;
			
	public static enum Suit {
		Harts, Diamonds, Spades, Clubs
	}
	
	public static enum Rank {
		Two, Three, Four, Five,
		Six, Seven, Eight, Nine,
		Ten, Jack, Queen, King,
		Ace
	}
	
	private Suit suit;
	private Rank rank;
	
	public PlayingCard(Suit s, Rank r) {
		this.suit = s;
		this.rank = r;
	}
	
	@Override
	public String toString() {
		return rank + " of " + suit + "\n";
	}
	
	public static PlayingCard[] makeDeck() {
		PlayingCard[] deck = new PlayingCard[CARDS_IN_DECK];
		int i = 0;
		
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				deck[i++] = new PlayingCard(s, r);
			}
		}
		
		return deck;
	}
	
	public static void shffleDeck(PlayingCard[] deck) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < deck.length; j++) {
				int rand = (int)(Math.random() * deck.length);
				swap(deck, j, rand);
			}
		}
	}
	
	// it probably makes more sense to return a new sorted deck
	@SuppressWarnings("unused")
	private static void _sortDeckByRank(PlayingCard[] deck) {
		int[] cards = new int[Rank.values().length];
		
		for (PlayingCard pc : deck) {
			cards[pc.rank.ordinal()]++;
		}
		
		int sumSoFar = 0;
		
		for (int ord = 0; ord < cards.length; ord++) {
			int t = cards[ord];
			cards[ord] = sumSoFar;
			sumSoFar += t;	
		}
				
		int pos = cards[deck[0].rank.ordinal()];
		BinaryTree<Integer> bTree = new BinaryTree<>(pos);
		swap(deck, 0, pos);
		cards[deck[0].rank.ordinal()]++;
		int i = 0;
		
		while (bTree.size < 52) {
		
			pos = cards[deck[i].rank.ordinal()];

			if (!bTree.contains(i)) {
				
				swap(deck, i, pos - 1);
				cards[deck[i].rank.ordinal()]++;
				bTree.add(pos);
				i--;
			}
			i++;
		}
	}
	
	// counting sort
	public static PlayingCard[] sortByRank(PlayingCard[] deck) {
		int[] cards = new int[Rank.values().length];
		
		for (PlayingCard pc : deck) {
			cards[pc.rank.ordinal()]++;
		}
		
		int sumSoFar = 0;
		
		for (int ord = 0; ord < cards.length; ord++) {
			int t = cards[ord];
			cards[ord] = sumSoFar;
			sumSoFar += t;
		}
		
		PlayingCard[] sorted = new PlayingCard[deck.length];
		
		for (int i = 0; i < deck.length; i++) {
			sorted[cards[deck[i].rank.ordinal()]] = deck[i];
			cards[deck[i].rank.ordinal()]++;
		}
		return sorted;
	}
	
	// bucket sort first half
	private static Hashmap<Suit, Slice<PlayingCard>> splitBySuit(PlayingCard[] deck) {
		Hashmap<Suit, Slice<PlayingCard>> buckets = new Hashmap<>();
		
		for (PlayingCard pc : deck) {
			if (buckets.get(pc.suit) == null) {
				Slice<PlayingCard> cardsInSuit = new Slice<>(PlayingCard.class);
				buckets.add(pc.suit, cardsInSuit); 
			}
			buckets.get(pc.suit).add(pc);
		}
		
		return buckets;
	}
	
	// bucket sort second half
	public static PlayingCard[] sortBySuit(PlayingCard[] deck) {
				
		Hashmap<Suit, Slice<PlayingCard>> buckets = splitBySuit(deck);
		PlayingCard[] sorted = new PlayingCard[deck.length];
		int filledSoFar = 0;
		
		for (Suit s : Suit.values()) {
			PlayingCard[] suit = buckets.get(s).toArray();
			System.arraycopy(suit, 0, sorted, filledSoFar, suit.length);
			filledSoFar += suit.length;
		}
		return sorted;
	}
	
	// bucket sort, then counting sort to sort the buckets
	public static PlayingCard[] sort(PlayingCard[] deck) {
		
		Hashmap<Suit, Slice<PlayingCard>> buckets = splitBySuit(deck);
		PlayingCard[] sorted = new PlayingCard[deck.length];
		int filledSoFar = 0;
		
		for (Suit s : Suit.values()) {
			PlayingCard[] suit = buckets.get(s).toArray();
			suit = sortByRank(suit);
			System.arraycopy(suit, 0, sorted, filledSoFar, suit.length);
			filledSoFar += suit.length;
		}
		return sorted;
	}
	
	private static void swap(PlayingCard[] deck, int from, int to) {
		PlayingCard temp = deck[from];
		deck[from] = deck[to];
		deck[to] = temp;
	}
}
