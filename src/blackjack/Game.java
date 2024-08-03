/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    protected ArrayList<Player> players;
    protected Deck deck;
    protected Player dealer;

    public Game(ArrayList<String> playerNames) {
        players = new ArrayList<>();
        for (String name : playerNames) {
            players.add(new Player(name, 100)); // Each player starts with a balance of 100
        }
        dealer = new Player("Dealer", 0); // Dealer does not need a balance
        deck = new Deck();
    }

    public void startGame() {
        System.out.println("Starting a new game...");
        displayInitialBalances();

        handleBets();

        for (Player player : players) {
            for (int i = 0; i < 2; i++) {
                player.drawCard(deck);
            }
        }

        for (int i = 0; i < 2; i++) {
            dealer.drawCard(deck);
        }

        System.out.println("Dealer's visible card: " + dealer.getHand().getCards().get(0));

        for (Player player : players) {
            playerTurn(player);
        }

        dealerTurn();
        determineWinners();
        displayFinalResults();
        resetPlayers();
    }

    private void handleBets() {
        Scanner scanner = new Scanner(System.in);
        for (Player player : players) {
            int bet = 0;
            while (true) {
                System.out.println(player.getName() + ", enter your bet (balance: " + player.getBalance() + "): ");
                bet = scanner.nextInt();
                if (bet <= player.getBalance() && bet > 0) {
                    player.placeBet(bet);
                    break;
                } else {
                    System.out.println("Invalid bet amount. Please enter an amount less than or equal to your balance.");
                }
            }
        }
    }

    private void playerTurn(Player player) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println(player);
            System.out.println(player.getName() + ", do you want to hit or stand? (H/S)");
            String action = scanner.next().toUpperCase();
            if (action.equals("H")) {
                player.drawCard(deck);
                if (calculateHandValue(player.getHand()) > 21) {
                    System.out.println(player.getName() + " busts!");
                    break;
                }
            } else if (action.equals("S")) {
                break;
            } else {
                System.out.println("Invalid input, please enter H or S.");
            }
        }
    }

    private void dealerTurn() {
        System.out.println("Dealer's turn...");
        while (calculateHandValue(dealer.getHand()) < 17) {
            dealer.drawCard(deck);
        }
        System.out.println("Dealer's hand: " + dealer.getHand());
    }

    private void determineWinners() {
        int dealerValue = calculateHandValue(dealer.getHand());
        System.out.println("Dealer's final hand value: " + dealerValue);

        for (Player player : players) {
            int playerValue = calculateHandValue(player.getHand());
            System.out.println(player.getName() + "'s hand value: " + playerValue);
            if (playerValue > 21) {
                System.out.println(player.getName() + " busts and loses the bet.");
            } else if (dealerValue > 21 || playerValue > dealerValue) {
                int winnings = player.getBet() * 2;
                player.addWinnings(winnings);
                player.incrementWins();
                System.out.println(player.getName() + " wins " + winnings + " with a hand value of " + playerValue);
            } else if (playerValue == dealerValue) {
                player.addWinnings(player.getBet());
                System.out.println(player.getName() + " ties and gets the bet back with a hand value of " + playerValue);
            } else {
                System.out.println(player.getName() + " loses the bet with a hand value of " + playerValue);
            }
        }
    }

    private void displayInitialBalances() {
        System.out.println("Initial Balances:");
        for (Player player : players) {
            System.out.println(player.getName() + ": " + player.getBalance());
        }
    }

    private void displayFinalResults() {
        System.out.println("Final Results:");
        for (Player player : players) {
            System.out.println(player.getName() + "'s final balance: " + player.getBalance());
        }
        System.out.println("Dealer's final hand: " + dealer.getHand());
        displayLeaderboard();
    }

    private void displayLeaderboard() {
        System.out.println("Leaderboard:");
        players.sort((p1, p2) -> Integer.compare(p2.getWins(), p1.getWins()));
        for (Player player : players) {
            System.out.println(player.getName() + " - Wins: " + player.getWins() + ", Balance: " + player.getBalance());
        }
    }

    private void resetPlayers() {
        for (Player player : players) {
            player.clearHand();
        }
        dealer.clearHand();
    }

    private int calculateHandValue(Hand hand) {
        int value = 0;
        int numAces = 0;
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
                case "Ace": value += 11; numAces++; break; // Initially treat Ace as 11
            }
        }
        // Adjust for Aces if value is over 21
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }
}
