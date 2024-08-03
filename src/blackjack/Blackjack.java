/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package blackjack;

import java.util.ArrayList;
import java.util.Scanner;

public class Blackjack {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> playerNames;

        while (true) {
            System.out.println("Welcome to Blackjack!");
            System.out.println("1. Start a new game");
            System.out.println("2. Exit");
            System.out.print("Select an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume the newline

            if (option == 1) {
                playerNames = new ArrayList<>();
                System.out.println("Enter the number of players:");
                int numPlayers = scanner.nextInt();
                scanner.nextLine(); // consume the newline

                for (int i = 0; i < numPlayers; i++) {
                    System.out.println("Enter the name of player " + (i + 1) + ":");
                    String name = scanner.nextLine();
                    playerNames.add(name);
                }

                Game game = new Game(playerNames);
                game.startGame();

                // Ask if they want to play again
                System.out.println("Do you want to play again? (Y/N)");
                String response = scanner.nextLine().toUpperCase();
                if (!response.equals("Y")) {
                    break;
                }
            } else if (option == 2) {
                break;
            } else {
                System.out.println("Invalid option, please select 1 or 2.");
            }
        }

        System.out.println("Thanks for playing!");
    }
}
