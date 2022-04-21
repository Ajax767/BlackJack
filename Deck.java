package Blackjack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
	
	public static List<Card> deck = new ArrayList<>(52);
	public static int cardIndex = 0;
	
	public static void createDeck() {		
		for (Suit syms : Suit.values()) {
			for(Rank nums : Rank.values() ) {
				Card card = new Card(syms, nums);			
				deck.add(card);
			}
		}		
		shuffleDeck();
	}
	
	public static void shuffleDeck() {
		Collections.shuffle(deck);
	}
	
	public static void showDeck() {
		for(Card game : deck) {
			System.out.println(game);
		}
	}
	
	public static void initialHand() {
		for (int i = 0; i < 4; i++) {
			if(i%2 == 0) {
				Hands.addCardDealer(deck.get(cardIndex));
			}else {
				Hands.addCardPlayer(deck.get(cardIndex));
			}
			cardIndex++;
		}	
		
		System.out.printf("Dealer's hand is: HIDDEN, %s%n", Hands.getDealer().get(1));
		System.out.printf("Player's hand is: %s%n", Hands.getPlayer());
	}
	
	public static int getCardIndex() {
		return cardIndex;
	}

	public static Card getNextCard() {
		return deck.get(cardIndex++);
	}
}
