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
public class Bot extends Player{
    
    public Bot(String name) {
        super(name);
    }
    //overide set ship position
    public void setShipPosition(){
        StringBuilder s = new StringBuilder();
        ArrayList<Ship> ships = super.getBoard().getShips();
        for (int i = 0; i < ships.size(); i++) {
            System.out.print(ships.get(i).getName() + " location(eg. A1): ");
            s.append((char) ((int) (Math.random() * 10) + 65));
            s.append((int) (Math.random() * 10));
            String location = s.toString();
            ships.get(i).setLocation(location);
            System.out.print(ships.get(i).getName() + " direction(eg. N, E, S, W): ");
            char direction = randomDirection();
            ships.get(i).setDirection(direction);
            super.getBoard().drawShips();
            System.out.println(super.getBoard());
        }
    }
    //overide get move
    @Override
    public String getMove(){
        StringBuilder s = new StringBuilder();
        System.out.print("Shot location: ");
        s.append((char) ((int) (Math.random() * 10) + 65));
        s.append((int) (Math.random() * 10));
        String move = s.toString();
        return move;
    }
    
    public char randomDirection(){
        int rand = (int) (Math.random()*(4));
        if(rand == 0)
            return 'N';
        else if(rand == 1) 
            return 'E';
        else if(rand == 2)
            return 'S';
        else
            return 'W';
    }
}
