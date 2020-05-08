/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.io.*;
import java.util.Scanner;

/**
 *
 * @author peanu
 */
public class Game implements Serializable {

    private String name;
    Player player1;
    Bot bot;

    Game() {

    }

    Game(String name, Player player1, Bot player2) {
        this.name = name;
        this.player1 = player1;
        this.bot = player2;
    }

    public void start() {
        player1.setShipPos();
        bot.setShipPos();
        //if statement player 1 turn
        if (Math.random() < 0.5) {
            System.out.println(player1.getName() + "'s move");
            String move = player1.getMove();
            if (move.equalsIgnoreCase("Save")) {
                save();
                return;
            }
            player1.updateTopBoard(move, bot.firedUpon(move));
            System.out.println(player1.getBoard());
        }
        while (!bot.gameOver()) {
            //player 2 turn
            String move = bot.getMove();
            System.out.println(bot.getName() + " move: " + move);
            bot.updateTopBoard(move, player1.firedUpon(move));

            //if statement is game over
            if (player1.gameOver()) {
                break;
            }
            //player 1 turn
            System.out.println(player1.getName() + " move");
            move = player1.getMove();
            if (move.equalsIgnoreCase("Save")) {
                save();
                break;
            }
            player1.updateTopBoard(move, bot.firedUpon(move));
            System.out.print(player1.getBoard());
        }
        if (!player1.gameOver()) {
            System.out.println(player1.getName() + " won!\nGame over!");
        } else if (player1.gameOver()) {
            System.out.println("Bot won!\nGame over!");
        } else {
            System.out.println("Game saved!");
        }
    }

    public void recommence() {
        while (!player1.gameOver()) {
            //player 1 turn
            System.out.println(player1.getName() + " move");
            String move = player1.getMove();
            if (move.equalsIgnoreCase("Save")) {
                save();
                break;
            }
            player1.updateTopBoard(move, bot.firedUpon(move));
            System.out.print(player1.getBoard());
            //if statement is game over
            if (bot.gameOver()) {
                break;
            }
            //player 2 turn
            move = bot.getMove();
            System.out.println(bot.getName() + " move: " + move);
            bot.updateTopBoard(move, player1.firedUpon(move));
        }
        if (!player1.gameOver()) {
            System.out.println(player1.getName() + " won!\nGame over!");
        } else if (player1.gameOver()) {
            System.out.println("Bot won!\nGame over!");
        } else {
            System.out.println("Game saved!");
        }
    }
//credit to: geeksforgeeks.org/serialization-in-java/

    public void save() {

        try {
            //Saving of object in a file 
            FileOutputStream file = new FileOutputStream("src/Assets/" + name + ".txt");
            ObjectOutputStream out = new ObjectOutputStream(file);
            // Method for serialization of object 
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("Object has been serialized");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        }
    }

    public static Game load() {
        Game game = null;
        try {
            // Reading the object from a file 
            Scanner input = new Scanner(System.in);
            System.out.print("Game name: ");
            FileInputStream file = new FileInputStream("src/Assets/" + input.next() + ".txt");
            ObjectInputStream in = new ObjectInputStream(file);
            // Method for deserialization of object 
            game = (Game) in.readObject();
            in.close();
            file.close();
            System.out.println("Object has been deserialized ");
        } catch (IOException ex) {
            System.out.println("IOException is caught");
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
        }
        return game;
    }
}

