package blackjack;

import java.util.*;
import java.util.concurrent.CountDownLatch;

import javax.swing.GroupLayout.SequentialGroup;

public class Blackjack implements BlackjackEngine {

	/**
	 * Constructor you must provide. Initializes the player's account to 200 and the
	 * initial bet to 5. Feel free to initialize any other fields. Keep in mind that
	 * the constructor does not define the deck(s) of cards.
	 * 
	 * @param randomGenerator
	 * @param numberOfDecks
	 */

	ArrayList<Card> playerHand = new ArrayList<Card>();// creating new generic arraylist
	ArrayList<Card> dealer = new ArrayList<Card>();// creating new generic arraylist
	ArrayList<Card> deckOfCards = new ArrayList<Card>();// creating new generic arraylist
	Random rnd;

	static int playerAccount = 200;
	static int betAmount = 0;

	int numberOfDecksinUse;
	int gameStatus = GAME_IN_PROGRESS;

	public Blackjack(Random randomGenerator, int numberOfDecks) {
		rnd = randomGenerator;
		numberOfDecksinUse = numberOfDecks;
	}

	public int getNumberOfDecks() {
		return numberOfDecksinUse;
	}

	public void createAndShuffleGameDeck() {
		CardSuit CS = CardSuit.SPADES;
		deckOfCards.clear();
		
		for (int decks = 0; decks < numberOfDecksinUse; decks++) {
			for (int i = 1; i <= 4; i++) {
				if (i == 1) {
					CS = CardSuit.SPADES;
				}

				if (i == 2) {
					CS = CardSuit.DIAMONDS;
				}

				if (i == 3) {
					CS = CardSuit.HEARTS;
				}

				if (i == 4) {
					CS = CardSuit.CLUBS;
				}

				deckOfCards.add(new Card(CardValue.Ace, CS));
				deckOfCards.add(new Card(CardValue.Two, CS));
				deckOfCards.add(new Card(CardValue.Three, CS));
				deckOfCards.add(new Card(CardValue.Four, CS));
				deckOfCards.add(new Card(CardValue.Five, CS));
				deckOfCards.add(new Card(CardValue.Six, CS));
				deckOfCards.add(new Card(CardValue.Seven, CS));
				deckOfCards.add(new Card(CardValue.Eight, CS));
				deckOfCards.add(new Card(CardValue.Nine, CS));
				deckOfCards.add(new Card(CardValue.Ten, CS));
				deckOfCards.add(new Card(CardValue.Jack, CS));
				deckOfCards.add(new Card(CardValue.Queen, CS));
				deckOfCards.add(new Card(CardValue.King, CS));

			}
		}
		System.out.println(deckOfCards.toString());
		Collections.shuffle(deckOfCards, rnd);
		dealer.clear();
		playerHand.clear();
		gameStatus = GAME_IN_PROGRESS;
	}

	public Card[] getGameDeck() {

		Card[] temp = new Card[deckOfCards.size()];
		deckOfCards.toArray(temp);
		return temp;
	}

	public void deal() {
		this.createAndShuffleGameDeck();

		playerHand.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		deckOfCards.get(0).setFaceDown();
		dealer.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		playerHand.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		dealer.add(deckOfCards.get(0));
		deckOfCards.remove(0);

		playerAccount -= 5;
		betAmount = 5;

	}

	public Card[] getDealerCards() {

		Card[] temp = new Card[dealer.size()];
		dealer.toArray(temp);
		return temp;
	}

	/**
	 * Returns an array representing the possible value(s) associated with the
	 * player's cards if the cards represent a value less than or equal to 21.
	 * 
	 * @return integer array representing the possible value(s) or null if cards
	 *         represent a value higher than 21. The array will have a size of 1 if
	 *         only one value is associated with the set of cards, and a size of two
	 *         if two values are possible. For the case of an array of size two, the
	 *         smaller value must appear in the first array entry.
	 */
	public int[] getDealerCardsTotal() {
		int sum = 0;
		boolean ace = false;

		for (int i = 0; i < dealer.size(); i++) {
			if (dealer.get(i).getValue() == CardValue.Ace) {
				ace = true;
			}
			sum += dealer.get(i).getValue().getIntValue();
		}

		if (sum > 21) {
			return null;
		}

		else if (ace && (sum + 10 <= 21)) {
			int[] a = new int[2];
			a[0] = sum;
			a[1] = sum + 10;
			return a;
		} else {
			int[] a = new int[1];
			a[0] = sum;
			return a;
		}

	}

	/**
	 * Returns an integer value that can assume the values LESS_THAN_21 if the
	 * player's cards have a value less than 21, BUST if the players's cards have a
	 * value greater than 21, and BLACKJACK if the player has an Ace along with a
	 * "10", Jack, Queen, or King. If the players' cards have a value equivalent to
	 * 21 and the hand does not correspond to a blackjack, HAS_21 will be returned.
	 * 
	 * @return Integer value that corresponds to one of the following: LESS_THAN_21,
	 *         BUST, BLACKJACK, HAS_21
	 */
	public int getDealerCardsEvaluation() {

		int sum = 0;
		boolean ace = false;

		for (int i = 0; i < dealer.size(); i++) {
			if (dealer.get(i).getValue() == CardValue.Ace) {
				ace = true;
			}
			sum += dealer.get(i).getValue().getIntValue();
		}

		if (sum > 21) {
			return BUST;
		} else if (sum < 21) {
			return LESS_THAN_21;
		} else if (ace && (sum + 10 == 21)) {
			return BLACKJACK;
		} else if (!ace && sum == 21) {
			return HAS_21;
		} else {
			return 0;
		}

	}

	public Card[] getPlayerCards() {
		Card[] temp = new Card[playerHand.size()];
		playerHand.toArray(temp);
		return temp;
	}

	public int[] getPlayerCardsTotal() {
		int sum = 0;
		boolean ace = false;

		for (int i = 0; i < playerHand.size(); i++) {
			if (playerHand.get(i).getValue() == CardValue.Ace) {
				ace = true;
			}
			sum += playerHand.get(i).getValue().getIntValue();
		}

		if (sum > 21) {
			return null;
		}

		else if (ace && (sum + 10 <= 21)) {
			int[] a = new int[2];
			a[0] = sum;
			a[1] = sum + 10;
			return a;
		} else {
			int[] a = new int[1];
			a[0] = sum;
			return a;
		}
	}

	public int getPlayerCardsEvaluation() {
		int sum = 0;
		boolean ace = false;

		for (int i = 0; i < playerHand.size(); i++) {
			if (playerHand.get(i).getValue() == CardValue.Ace) {
				ace = true;
			}
			sum += playerHand.get(i).getValue().getIntValue();
		}

		if (sum > 21) {
			return BUST;
		} else if (sum < 21) {
			return LESS_THAN_21;
		} else if (ace && (sum + 10 == 21)) {
			return BLACKJACK;
		} else if (!ace && sum == 21) {
			return HAS_21;
		} else {
			return 0;
		}

		
	}

	/**
	 * Retrieves a card from the deck and assigns the card to the player. The new
	 * sets of cards will be evaluated. If the player busts, the game is over and
	 * the games's status will be updated to DEALER_WON. Otherwise the game's status
	 * is GAME_IN_PROGRESS.
	 */

	public void playerHit() {

		playerHand.add(deckOfCards.get(0));
		deckOfCards.remove(0);
		deckOfCards.get(0).setFaceDown();

		int eval = 0;
		eval = getPlayerCardsEvaluation();

		if (eval == BUST) {
			gameStatus = DEALER_WON;
			// set gamestatus to DEALER_WON
		} else {
			gameStatus = GAME_IN_PROGRESS;
			// GAME_IN_PROGRESS

		}
	}

	/**
	 * 1. Flips the dealer's card that is currently face down 2. and assigns cards
	 * to the dealer as long as the dealer doesn't bust and the cards have a value
	 * less than 16. 3. Once the dealer has a hand with a value greater than or
	 * equal to 16, and less than or equal to 21, the hand will be compared against
	 * the player's hand and 4. whoever has the hand with a highest value will win
	 * the game. If both have the same value we have a draw. The game's status will
	 * be updated to one of the following values: DEALER_WON, PLAYER_WON, or DRAW.
	 * The player's account will be updated with a value corresponding to twice the
	 * bet amount if the player wins. If there is a draw the player's account will
	 * be updated with the only the bet amount.
	 *
	 */
	public void playerStand() {
		boolean game = true;
		
		for (int i = 0; i < dealer.size(); i++) {
			if (!dealer.get(i).isFacedUp()) {
				dealer.get(i).setFaceUp();
			}
		}
		
		while (game) {
			if(getDealerCardsTotal() == null) {
				gameStatus = PLAYER_WON;
				game = false;
			} else if(getDealerCardsTotal().length >= 1) {
			if (getDealerCardsTotal()[0] >= 16) {
				if (getPlayerCardsTotal()[0] < getDealerCardsTotal()[0]) {
					gameStatus = DEALER_WON;
					game = false;
				} else if (getPlayerCardsTotal()[0] > getDealerCardsTotal()[0]) {
					gameStatus = PLAYER_WON;
					game = false;
				} else {
					gameStatus = DRAW;
					game = false;
				}
				if (getPlayerCardsTotal().length == 1) {
					if (getPlayerCardsTotal()[0] > getDealerCardsTotal()[0]) {
						gameStatus = PLAYER_WON;
						game = false;
					} else if (getPlayerCardsTotal()[0] < getDealerCardsTotal()[0]) {
						gameStatus = DEALER_WON;
						game = false;
					} else {
						gameStatus = DRAW;
						game = false;
					}
				}

			} else {
				dealer.add(deckOfCards.get(0));
				deckOfCards.remove(0);
			}
		}
		}
	}

	/**
	 * Returns an integer representing the game status.
	 * 
	 * @return DRAW, PLAYER_WON, DEALER_WON OR GAME_IN_PROGRESS
	 */
	public int getGameStatus() {
		return gameStatus;
	}

	public void setBetAmount(int amount) {
		betAmount = amount;
	}

	public int getBetAmount() {
		return betAmount;
	}

	public void setAccountAmount(int amount) {
		playerAccount = amount;

	}

	public int getAccountAmount() {
		return playerAccount;
	}

	/* Feel Free to add any private methods you might need */
}