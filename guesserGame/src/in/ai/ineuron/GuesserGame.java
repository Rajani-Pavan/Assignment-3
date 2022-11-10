package in.ai.ineuron;
/*
 * Guesser Game project : 
 * Parties in this game :
 * 	1. Guesser
 * 	2. Players
 * 	3. Umpire
 * 
 * How the game is played ?
 * 	1. Guesser will guess the number between 1 to 10. 
 * 	2. Umpire will record the guessed number from guesser.
 * 	3. Umpire provides chance to the players to guess the number.
 * 	4. Players guess the number.
 * 	5. Umpire records the guessed number from players.
 * 	6. Umpire compares the guesser number with player's guessed number.
 * 	7. Umpire declares the winner.
 * 
 * In this project two features enhanced:
 * 		1. Range validation for guesser : Refer method : isGuessNumInRange(int) void
 * 		2. Tie-break feature : Refer method : tieBreaker() void
 */

import java.util.Scanner;

class Guesser {

	int guessNum;

	Scanner scan = new Scanner(System.in);

	int guessNum() {

		System.out.println("Guesser kindly guess the number");
		guessNum = scan.nextInt();
		isGuessNumInRange(guessNum);
		return guessNum;
	}

	void isGuessNumInRange(int guessNum) {
		if (guessNum <= 1 || guessNum >= 10) {
			System.out.println("Guesser: please guess the number between 1 to 10");
			guessNum();
		}
	}
}

class Player {

	int guessNum;
	String playerName;

	Scanner scan = new Scanner(System.in);

	public Player(String playerName) {
		this.playerName = playerName;
	}

	int guessNum() {
		System.out.println(playerName + " kindly guess the number");
		guessNum = scan.nextInt();
		return guessNum;
	}
}

class Umpire {
	int numFromGuesser;
	int numFromPlayer1;
	int numFromPlayer2;
	int numFromPlayer3;

	Player player1 = new Player("Player1");
	Player player2 = new Player("player2");
	Player player3 = new Player("player3");

	void collectNumFromGuesser() {
		Guesser g = new Guesser();
		numFromGuesser = g.guessNum();
	}

	void collectNumFromPlayers() {

		System.out.println("Players: guess the number between 1 to 10");
		numFromPlayer1 = player1.guessNum();
		numFromPlayer2 = player2.guessNum();
		numFromPlayer3 = player3.guessNum();
	}

	void compare() {
		if (numFromGuesser == numFromPlayer1) {
			if (numFromGuesser == numFromPlayer2 && numFromGuesser == numFromPlayer3) {
				System.out.println("Congratulations!! All players won the game");
				System.out.println("Let us play tiebreaker!!!");
				tieBreaker();
			} else if (numFromGuesser == numFromPlayer2) {
				System.out.println("Congratulations!! Player 1 & Player 2 won the game");
				System.out.println("Let us play tiebreaker!!!");
				tieBreaker();
			} else if (numFromGuesser == numFromPlayer3) {
				System.out.println("Congratulations!! Player 1 & Player 3 won the game");
				System.out.println("Let us play tiebreaker!!!");
				tieBreaker();
			} else {
				System.out.println("Congratulations!! Player 1 won the game");
			}
		} else if (numFromGuesser == numFromPlayer2) {
			if (numFromGuesser == numFromPlayer3) {
				System.out.println("Congratulations!! Player 2 & Player 3 won the game");
				System.out.println("Let us play tiebreaker!!!");
				tieBreaker();
			} else {
				System.out.println("Congratulations!! Player 2 won the game");
			}
		} else if (numFromGuesser == numFromPlayer3) {
			System.out.println("Congratulations!! Player 3 won the game");
		} else {
			System.out.println("Game lost Try Again!");
		}
	}

	void reset() {
		final int resetValue = 0; // compile time constant
		numFromGuesser = resetValue;
		numFromPlayer1 = resetValue;
		numFromPlayer2 = resetValue;
		numFromPlayer3 = resetValue;
	}

	void tieBreaker() {

		if (numFromGuesser == numFromPlayer1 && numFromGuesser == numFromPlayer2 
				&& numFromGuesser == numFromPlayer3) {
			reset();
			collectNumFromGuesser();
			collectNumFromPlayers();
			compare();
		}

		if (numFromGuesser == numFromPlayer1 && numFromGuesser == numFromPlayer2) {
			reset();
			collectNumFromGuesser();
			this.numFromPlayer1 = player1.guessNum();
			this.numFromPlayer2 = player2.guessNum();
			compare();
		}

		if (numFromGuesser == numFromPlayer1 && numFromGuesser == numFromPlayer3) {

			reset();
			collectNumFromGuesser();
			this.numFromPlayer1 = player1.guessNum();
			this.numFromPlayer3 = player3.guessNum();
			compare();
		}

		if (numFromGuesser == numFromPlayer2 && numFromGuesser == numFromPlayer3) {
			reset();
			collectNumFromGuesser();
			this.numFromPlayer2 = player2.guessNum();
			this.numFromPlayer3 = player3.guessNum();
			compare();
		}
	}
}

public class GuesserGame {

	public static void main(String[] args) {

		Umpire u = new Umpire();
		u.collectNumFromGuesser();
		u.collectNumFromPlayers();
		u.compare();
	}
}