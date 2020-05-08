/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author peanu
 */
public class Player {

    private Board board;
    private String name;
    ArrayList<String> moves;

    public Player(String name) {
        this.name = name;
        board = new Board();
    }

    public void updateTopBoard(String position, boolean hit) {
        board.updateTopBoard(position, hit);
    }

    public boolean firedUpon(String move) {
        String result = board.potentialHit(move);
        System.out.println(result);
        //if statement but cooler
        return !result.equals("Shot missed");
    }

    public boolean gameOver() {
        if (board.allDead()) {
            return true;
        }
        return false;
    }
    
    public void setShipPos(){
        Scanner input = new Scanner(System.in);
        ArrayList<Ship> ships = board.getShips();
        for (int i = 0; i < ships.size(); i++) {
            System.out.print(ships.get(i).getName() + " position(eg. A1): ");
            String position = input.next();
            ships.get(i).setPosition(position);
            System.out.print(ships.get(i).getName() + " direction(eg. N, E, S, W): ");
            char direction = input.next().charAt(0);
            ships.get(i).setDirection(direction);
            board.drawShips();
            System.out.println(board);
        }
    }
    
    public String getMove(){
        Scanner input = new Scanner(System.in);
        System.out.print("Shot position: ");
        String move = input.next();
        return move;
    }
    
    public Board getBoard(){
        return board;
    }
    
    public String getName(){
        return name;
    }
}
