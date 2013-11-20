package com.github.robbmj.googleprep.algorithms;

import java.util.Arrays;

import com.github.robbmj.googleprep.datastructures.BinaryTree;
import com.github.robbmj.googleprep.datastructures.Hashmap;
import com.github.robbmj.googleprep.datastructures.Slice;

public class PlayingCardDeck {

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
	
	private PlayingCard[] deck;
	
	public PlayingCardDeck() {
		this.deck = new PlayingCard[CARDS_IN_DECK];
		
		int i = 0;
		for (Suit s : Suit.values()) {
			for (Rank r : Rank.values()) {
				this.deck[i++] = new PlayingCard(s, r);
			}
		}
	}
	
	public PlayingCard[] toArray() {
		return deck;
	}
	
	public PlayingCard cardAt(int i) {
		if (i < 0 || i >= deck.length) {
			return null;
		}
		return deck[i];
	}
	
	@Override
	public String toString() {
		return Arrays.toString(deck);
	}
	
	public void shffleDeck() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < deck.length; j++) {
				int rand = (int)(Math.random() * deck.length);
				swap(j, rand);
			}
		}
	}
	
	// it probably makes more sense to return a new sorted deck
	@SuppressWarnings("unused")
	private void inplaceSortByRank(PlayingCard[] deck) {
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
		swap(0, pos);
		cards[deck[0].rank.ordinal()]++;
		int i = 0;
		
		while (bTree.size < 52) {
		
			pos = cards[deck[i].rank.ordinal()];

			if (!bTree.contains(i)) {
				
				swap(i, pos - 1);
				cards[deck[i].rank.ordinal()]++;
				bTree.add(pos);
				i--;
			}
			i++;
		}
	}
	
	private static PlayingCard[] sortByRank(PlayingCard[] deck) {
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
	
	// counting sort
	public void sortByRank() {
		this.deck = sortByRank(this.deck);
	}
	
	// bucket sort first half
	private Hashmap<Suit, Slice<PlayingCard>> splitBySuit() {
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
	public void sortBySuit() {
				
		Hashmap<Suit, Slice<PlayingCard>> buckets = splitBySuit();
		PlayingCard[] sorted = new PlayingCard[deck.length];
		int filledSoFar = 0;
		
		for (Suit s : Suit.values()) {
			PlayingCard[] suit = buckets.get(s).toArray();
			System.arraycopy(suit, 0, sorted, filledSoFar, suit.length);
			filledSoFar += suit.length;
		}
		this.deck = sorted;
	}
	
	// bucket sort, then counting sort to sort the buckets
	public void sort() {
		
		Hashmap<Suit, Slice<PlayingCard>> buckets = splitBySuit();
		PlayingCard[] sorted = new PlayingCard[deck.length];
		int filledSoFar = 0;
		
		for (Suit s : Suit.values()) {
			PlayingCard[] suit = buckets.get(s).toArray();
			suit= sortByRank(suit);
			System.arraycopy(suit, 0, sorted, filledSoFar, suit.length);
			filledSoFar += suit.length;
		}
		this.deck = sorted;
	}
	
	private void swap(int from, int to) {
		PlayingCard temp = deck[from];
		deck[from] = deck[to];
		deck[to] = temp;
	}
	
	public static class PlayingCard implements Comparable<PlayingCard> {
		private Suit suit;
		private Rank rank;
		
		public PlayingCard(Suit s, Rank r) {
			this.suit = s;
			this.rank = r;
		}
		
		public Suit getSuit() {
			return suit;
		}

		public Rank getRank() {
			return rank;
		}

		@Override
		public boolean equals(Object o) {
			if (!(o instanceof PlayingCard)) return false;
			return this.suit == ((PlayingCard)o).suit && 
				   this.rank == ((PlayingCard)o).rank;
		}
		
		@Override
		public String toString() {
			return rank + " of " + suit + "\n";
		}

		@Override
		public int compareTo(PlayingCard o) {
			int s = this.suit.ordinal() - o.suit.ordinal();
			return s != 0 ? s : this.rank.ordinal() - o.rank.ordinal();
		}
	}
}
