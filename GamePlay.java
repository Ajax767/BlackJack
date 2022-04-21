package Blackjack;

import java.util.List;
import java.util.Scanner;


public class GamePlay {
	
	private static final int blackjack = 21;
	private static Scanner kybd = new Scanner(System.in);
	
	public static void startGame() {
		Deck.createDeck();
		Deck.initialHand();		
		playerMove();
		dealerMove();
		checkForWin();		
	}
	
	public static void playerMove() {
		
		System.out.println("Hit or Stand?");
		String input = kybd.next();
		
		while("Hit".equals(input)) {
			Hands.addCardPlayer(Deck.getNextCard());
			System.out.printf("Your hand is: %s%n", Hands.getPlayer());
			if(checkForBust(Hands.getHandValue(Hands.getPlayer()), Hands.getPlayer())) {
				System.out.println("Player Busts!");
				input= "exit";
			}
			else {
				System.out.println("Hit or Stand?");
				input = kybd.next();
			}			
		}			
	}

	public static void dealerMove() {
		System.out.printf("Dealer reveals hand: %s%n", Hands.getDealer());
		if(Hands.getHandValue(Hands.getPlayer()) > blackjack) {
			checkForWin();
		}	
		else {
			while(Hands.getHandValue(Hands.getDealer()) <= 16) {				
				System.out.println("Dealer Hits.");
				Hands.addCardDealer(Deck.getNextCard());
				System.out.printf("Dealer's hand is: %s%n", Hands.getDealer());
				}
			String bust = !checkForBust(Hands.getHandValue(Hands.getDealer()), 
						Hands.getDealer()) ? "Results:" : "Dealer Busts";
			System.out.println(bust);
		}
	}

	public static void checkForWin() {
		System.out.printf("Dealer's hand is: %s%n", Hands.getDealer());
		System.out.printf("Player's hand is: %s%n", Hands.getPlayer());
		
		if (Hands.getHandValue(Hands.getPlayer()) < Hands.getHandValue(Hands.getDealer())) {			
			if(!checkForBust(Hands.getHandValue(Hands.getDealer()), Hands.getDealer())) {
				System.out.printf("Dealer has %d, you have %d. Dealer wins.", 
				Hands.getHandValue(Hands.getDealer()), Hands.getHandValue(Hands.getPlayer()));
			}
			else {
				System.out.printf("Dealer busts with %d, you have %d. Player wins.", 
				Hands.getHandValue(Hands.getDealer()), Hands.getHandValue(Hands.getPlayer()));
			}
		}
		else if(Hands.getHandValue(Hands.getPlayer()) > Hands.getHandValue(Hands.getDealer())) {			
			if(!checkForBust(Hands.getHandValue(Hands.getPlayer()), Hands.getPlayer())) {
				System.out.printf("Dealer has %d, you have %d. Player wins.", 
				Hands.getHandValue(Hands.getDealer()), Hands.getHandValue(Hands.getPlayer()));
			}
			else {
				System.out.printf("Dealer has %d, you busted with %d. Dealer wins.", 
				Hands.getHandValue(Hands.getDealer()), Hands.getHandValue(Hands.getPlayer()));
			}
		}
		else {
			System.out.printf("Dealer has %d, you have %d. Push.", 
			Hands.getHandValue(Hands.getDealer()), Hands.getHandValue(Hands.getPlayer()));
		}
		System.exit(0);
	}
	
	public static Boolean checkForBust(int value, List<Card> hand) {
		int ace = 0;
		for(Card temp : hand) {			
			if(temp.getValue()==1) {
				ace+=10;
			}
		}		
		if(value+ace <= blackjack || value <= blackjack) {
			return false;
		}		
		return true;		
	}
}
