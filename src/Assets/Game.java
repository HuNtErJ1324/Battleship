/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.io.*;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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
            String move;
            System.out.println(player1.getName() + "'s move");
            move = player1.getMove();
            player1.getMoves().add(move);
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
            while (!bot.isValidMove(move)) {
                move = bot.getMove();
            }
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("AAAHHHH");
                }
            }
            System.out.println("");
            System.out.println(bot.getName() + " move: " + move);
            bot.updateTopBoard(move, player1.firedUpon(move));
            System.out.print(player1.getBoard());
            //if statement is game over
            if (player1.gameOver()) {
                break;
            }
            //player 1 turn
            System.out.println(player1.getName() + ", enter a move:");
            move = player1.getMove();
            if (move.equalsIgnoreCase("save")) {
                save();
                return;
            }
            while (!player1.isValidMove(move)) {
                System.out.println(player1.getName() + ", You already did that!\nEnter a new move:");
                move = player1.getMove();
                if (move.equalsIgnoreCase("save")) {
                    save();
                    return;
                }
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
            System.out.println(player1.getName() + ", enter a move:");
            String move = player1.getMove();
            if (move.equalsIgnoreCase("save")) {
                save();
                return;
            }
            while (!player1.isValidMove(move)) {
                System.out.println(player1.getName() + ", You already did that!\nEnter a new move:");
                move = player1.getMove();
                if (move.equalsIgnoreCase("save")) {
                    save();
                    return;
                }

            }
            player1.updateTopBoard(move, bot.firedUpon(move));
            System.out.print(player1.getBoard());
            //if statement is game over
            if (bot.gameOver()) {
                break;
            }
            //player 2 turn
            move = bot.getMove();
            for (int i = 0; i < 3; i++) {
                System.out.print(".");
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException ex) {
                    System.out.println("AAAHHHH");
                }
            }
            System.out.println("");
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
            FileOutputStream file = new FileOutputStream(name + ".ser");
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
            FileInputStream file = new FileInputStream(input.next() + ".ser");
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

    public String getName() {
        return name;
    }
}