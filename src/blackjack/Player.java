/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

public class Player {
    private String name;
    private Hand hand;
    private int bet;
    private int balance;
    private int wins;

    public Player(String name, int balance) {
        this.name = name;
        this.hand = new Hand();
        this.balance = balance;
        this.bet = 0;
        this.wins = 0;
    }

    public String getName() {
        return name;
    }

    public Hand getHand() {
        return hand;
    }

    public int getBet() {
        return bet;
    }

    public void placeBet(int amount) {
        if (amount <= balance) {
            bet = amount;
            balance -= amount;
        } else {
            System.out.println(name + " does not have enough balance to place this bet.");
        }
    }

    public int getBalance() {
        return balance;
    }

    public void addWinnings(int amount) {
        balance += amount;
    }

    public void drawCard(Deck deck) {
        Card drawnCard = deck.drawCard();
        if (drawnCard != null) {
            hand.addCard(drawnCard);
        }
    }

    public void clearHand() {
        hand = new Hand();
    }

    public int getWins() {
        return wins;
    }

    public void incrementWins() {
        wins++;
    }

    @Override
    public String toString() {
        return name + "'s hand: " + hand.toString() + ", Bet: " + bet + ", Balance: " + balance;
    }
}
