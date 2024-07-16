/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author Abhay
 */


public class Game {
    protected ArrayList<Player> players;
    protected Deck deck;

    public Game(ArrayList<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name, 100)); // Assuming each player starts with a balance of 100
        }
        deck = new Deck();
    }

    public void startGame() {
        handleBets();

        for (Player player : players) {
            for (int i = 0; i < 2; i++) { // Each player draws 2 cards initially
                player.drawCard(deck);
            }
        }

        // Additional game logic goes here

        determineWinner();
    }

    private void handleBets() {
        Scanner scanner = new Scanner(System.in);
        for (Player player : players) {
            System.out.println(player.getName() + ", enter your bet: ");
            int bet = scanner.nextInt();
            player.placeBet(bet);
        }
    }

    private void determineWinner() {
        Player winner = null;
        int highestValue = 0;
        for (Player player : players) {
            int handValue = calculateHandValue(player.getHand());
            if (handValue > highestValue) {
                highestValue = handValue;
                winner = player;
            }
        }

        if (winner != null) {
            int totalPot = players.stream().mapToInt(Player::getBet).sum();
            winner.addWinnings(totalPot);
            System.out.println(winner.getName() + " wins the pot of " + totalPot + " with a hand value of " + highestValue);
        }
    }

    private int calculateHandValue(Hand hand) {
        int value = 0;
        for (Card card : hand.getCards()) {
            switch (card.getRank()) {
                case "2": value += 2; break;
                case "3": value += 3; break;
                case "4": value += 4; break;
                case "5": value += 5; break;
                case "6": value += 6; break;
                case "7": value += 7; break;
                case "8": value += 8; break;
                case "9": value += 9; break;
                case "10": case "Jack": case "Queen": case "King": value += 10; break;
                case "Ace": value += 11; break; // Simplification: treating Ace as 11
            }
        }
        return value;
    }
}