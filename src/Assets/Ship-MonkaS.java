/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.io.Serializable;

/**
 *
 * @author peanu
 */
public class Ship implements Serializable {

    private int length;
    private String position;
    private char direction;
    private String name;
    private boolean[] health;

    Ship() {

    }

    Ship(String name, int length) {
        this.name = name;
        this.length = length;
        this.health = new boolean[length];
        for (int i = 0; i < length; i++) {
            health[i] = true;
        }
    }

    Ship(String name, int length, String position, char direction) {
        this.name = name;
        this.length = length;
        this.position = position;
        this.direction = direction;
        this.health = new boolean[length];
        for (int i = 0; i < length; i++) {
            health[i] = true;
        }
    }

    public boolean isHit(String target) {
        if (direction == 'n' || direction == 'N') {
            char col = position.charAt(1);
            for (int i = 0; i < length; i++) {
                char row = (char) (position.charAt(0) - i);
                if (col == target.charAt(1) && row == target.charAt(0)) {
                    health[i] = false;
                    return true;
                }
            }
            return false;
        } else if (direction == 's' || direction == 'S') {
            char col = position.charAt(1);
            for (int i = 0; i < length; i++) {
                char row = (char) (position.charAt(0) + i);
                if (col == target.charAt(1) && row == target.charAt(0)) {
                    health[i] = false;
                    return true;
                }
            }
            return false;
        } else if (direction == 'e' || direction == 'E') {
            char row = position.charAt(0);
            for (int i = 0; i < length; i++) {
                char col = (char) (position.charAt(1) + i);
                if (col == target.charAt(1) && row == target.charAt(0)) {
                    health[i] = false;
                    return true;
                }
            }
            return false;
        } else if (direction == 'w' || direction == 'W') {
            char row = position.charAt(0);
            for (int i = 0; i < length; i++) {
                char col = (char) (position.charAt(1) - i);
                if (col == target.charAt(1) && row == target.charAt(0)) {
                    health[i] = false;
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    public boolean isAlive() {
        for (int i = 0; i < health.length; i++) {
            if (health[i]) {
                return true;
            }
        }
        return false;
    }

    public int getLength() {
        return length;
    }

    public String getPosition() {
        return position;
    }

    public char getDirection() {
        return direction;
    }

    public boolean[] getHealth() {
        return health;
    }

    public String getName() {
        return name;
    }

    public void setPosition(String position) {
        this.position = position.toUpperCase();
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }
}
