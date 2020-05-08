/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.util.ArrayList;

/**
 *
 * @author peanu
 */
public class Bot extends Player {

    public Bot(String name) {
        super(name);
    }

    //overide set ship position
    @Override
    public void setShipPos() {
        ArrayList<Ship> ships = super.getBoard().getShips();
        for (int i = 0; i < ships.size(); i++) {
            String position = "";
            char direction = 'N';
            do {
                position = ((char) ((int) (Math.random() * 10) + 65)) + "" + ((int) (Math.random() * 10));
                direction = randomDirection();
            } while (!isValidPosition(position, direction, ships.get(i).getLength()));
            ships.get(i).setDirection(direction);
            ships.get(i).setPosition(position);
            super.getBoard().drawShips();
            //System.out.println(super.getBoard());
        }
    }

    //overide get move
    @Override
    public String getMove() {
        return ((char) ((int) (Math.random() * 10) + 65)) + "" + ((int) (Math.random() * 10));
    }

    private char randomDirection() {
        int rand = (int) (Math.random() * (4));
        if (rand == 0) {
            return 'N';
        } else if (rand == 1) {
            return 'E';
        } else if (rand == 2) {
            return 'S';
        } else {
            return 'W';
        }
    }

    private boolean isValidPosition(String position, char direction, int length) {
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
}
