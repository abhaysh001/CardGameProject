/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
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
            players.add(new Player(name));
        }
        deck = new Deck();
    }

    public void startGame() {
        for (Player player : players) {
            for (int i = 0; i < 7; i++) { // Each player draws 7 cards initially
                player.drawCard(deck);
            }
        }
        // Additional game logic goes here
    }
    
}
