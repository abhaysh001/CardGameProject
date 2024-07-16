/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
import java.util.Arrays;
/**
 *
 * @author Abhay
 */

public class Blackjack {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<String> playerNames = new ArrayList<>(Arrays.asList("Abhay", "Apram", "Anu", "Ridham"));
        Game game = new Game(playerNames);
        game.startGame();

        for (Player player : game.players) {
            System.out.println(player);
        }
    }
}
