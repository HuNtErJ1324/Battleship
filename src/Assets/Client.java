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
        System.out.println("Do you want to:\n1.Start\n2.Continue");
        if (input.nextLine().equalsIgnoreCase("start")) {
            System.out.print("Type your name here: ");
            Player player1 = new Player(input.nextLine());
            Bot bot = new Bot("bot");
            Game g = new Game("Battleship", player1, bot);
            g.start();
        }
        else{
            Game g = Game.load();
            g.recommence();
        }

    }
}
//sample ship position sequence
//A0 S J9 N D5 W F4 E J0 E
//nullpointerexception for incorrect input at start/continue
//arrayindexoutofboundsexception for incorrect move input