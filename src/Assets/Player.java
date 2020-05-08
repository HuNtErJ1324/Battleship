/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author peanu
 */
public class Player implements Serializable {

    private Board board;
    private String name;
    ArrayList<String> moves = new ArrayList();

    public Player(String name) {
        this.name = name;
        board = new Board();
    }

    public void setShipPos() {
        Scanner input = new Scanner(System.in);
        ArrayList<Ship> ships = board.getShips();
        String position;
        char direction;
        for (int i = 0; i < ships.size(); i++) {
            System.out.print(ships.get(i).getName() + " position(eg. A1): ");
            position = input.next().toUpperCase();
            ships.get(i).setPosition(position);
            System.out.print(ships.get(i).getName() + " direction(eg. N, E, S, W): ");
            direction = Character.toUpperCase(input.next().charAt(0));
            ships.get(i).setDirection(direction);
            while (!isValidPosition(position, direction, ships.get(i).getLength())) {
                System.out.print("Invalid position!\nRe-enter " + ships.get(i).getName() + " position:");
                position = input.next().toUpperCase();
                ships.get(i).setPosition(position);
                System.out.print(ships.get(i).getName() + " direction(eg. N, E, S, W): ");
                direction = Character.toUpperCase(input.next().charAt(0));
                ships.get(i).setDirection(direction);
            }
            board.drawShips();
            System.out.println(board);
        }
    }

    public boolean isValidMove(String move) {
        for (int i = 0; i < moves.size(); i++) {
            if (moves.get(i).equals(move)) {
                return false;
            }
        }
        moves.add(move);
        return true;
    }

    public boolean isValidPosition(String position, char direction, int length) {
        //check if it is inside the board
        if (direction == 'N' && position.charAt(0) - length < 'A') {
            return false;
        } else if (direction == 'S' && position.charAt(0) + length > 'J') {
            return false;
        } else if (direction == 'E' && position.charAt(1) + length > '9') {
            return false;
        } else if (direction == 'W' && position.charAt(1) - length < '0') {
            return false;
        }

        //check if it is overlapping with other ships
        //for goes through ships in array list
        for (int m = 0; m < getBoard().getShips().size(); m++) {
            String[][] bottom = getBoard().getBottomBoard();
            //for goes through placed ship length
            for (int q = 0; q < length; q++) {
                if (direction == 'N') {
                    int col = position.charAt(1) - 48;
                    for (int i = 0; i < getBoard().getShips().get(m).getLength(); i++) {
                        int row = (position.charAt(0) - 65 - q);
                        if (bottom[row][col].equals("O")) {
                            return false;
                        }
                    }
                } else if (direction == 'S') {
                    int col = position.charAt(1) - 48;
                    for (int i = 0; i < getBoard().getShips().get(m).getLength(); i++) {
                        int row = (position.charAt(0) - 65 + q);
                        if (bottom[row][col].equals("O")) {
                            return false;
                        }
                    }
                } else if (direction == 'E') {
                    int row = position.charAt(0) - 65;
                    for (int i = 0; i < getBoard().getShips().get(m).getLength(); i++) {
                        int col = (position.charAt(1) - 48 + q);
                        if (bottom[row][col].equals("O")) {
                            return false;
                        }
                    }
                } else if (direction == 'W') {
                    int row = position.charAt(0) - 65;
                    for (int i = 0; i < getBoard().getShips().get(m).getLength(); i++) {
                        int col = (position.charAt(1) - 48 - q);
                        if (bottom[row][col].equals("O")) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
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

    public String getMove() {
        Scanner input = new Scanner(System.in);
        System.out.print("Shot position: ");
        String move = input.next().toUpperCase();
        return move;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getMoves() {
        return moves;
    }
}