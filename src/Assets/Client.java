/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.util.Scanner;

/**
 *
 * @author peanu
 */
public class Client {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Type your name here: ");
        Player player1 = new Player(input.nextLine());
        Player bot = new Player("bot");
        Game g = new Game("Battleship", player1, bot);
        g.start();
    }
}
