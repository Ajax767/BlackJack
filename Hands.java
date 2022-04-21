package Blackjack;

import java.util.ArrayList;
import java.util.List;


public class Hands {	

	private static List<Card> dealer = new ArrayList<>();
	private static List<Card> player = new ArrayList<>();
	private static final int blackjack = 21;
	
	
	public Hands(Card dCard, Card pCard) {
		dealer.add(dCard);
		player.add(pCard);		
	}
	
	public static void addCardDealer(Card dCard ) {
		dealer.add(dCard);
	}
	
	public static void addCardPlayer(Card dCard ) {
		player.add(dCard);
	}

	public static List<Card> getDealer() {
		
		return dealer;
	}

	public static List<Card> getPlayer() {
		return player;
	}
	
	public static int getHandValue(List<Card> hand) {
		int total = 0;
		int ace = 0;
		for(Card temp : hand) {
			total+= temp.getValue();
			if(temp.getValue()==1) {
				ace+=10;
			}
		}
		if(ace + total <= blackjack) {
			return ace+total;
		}
		else {
			return total;
		}
	}
}
