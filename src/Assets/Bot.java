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
}