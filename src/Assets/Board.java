/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assets;

import java.util.ArrayList;
import java.io.Serializable;

/**
 *
 * @author peanu
 */
public class Board implements Serializable {

    private final int COLS = 10;
    private final int ROWS = 10;
    //targeting board
    private String[][] top;
    //positiong board
    private String[][] bottom;
    private ArrayList<Ship> ships;

    Board() {
        top = new String[ROWS][COLS];
        bottom = new String[ROWS][COLS];
        Ship destroyer = new Ship("destroyer", 2);
        Ship cruiser = new Ship("cruiser", 3);
        Ship submarine = new Ship("submarine", 3);
        Ship battleship = new Ship("battleship", 4);
        Ship carrier = new Ship("carrier", 5);
        ships = new ArrayList<>();
        ships.add(destroyer);
        ships.add(cruiser);
        ships.add(submarine);
        ships.add(battleship);
        ships.add(carrier);
        this.setDefaultBoards();
    }

    /**
     * Will check if the user's inputed move hits a ship.
     *
     * @param move the target. Is tested in isHit method.
     * @return Possible return statements are: "Hit", "You've sunk my x!", "your
     * shot missed"
     */
    public String potentialHit(String move) {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).isHit(move)) {
                bottom[move.charAt(0) - 65][move.charAt(1) - 48] = "X";
                if (!ships.get(i).isAlive()) {
                    return "You've sunk my " + ships.get(i).getName() + "!";
                }
                return "Hit";
            }
        }
        bottom[move.charAt(0) - 65][move.charAt(1) - 48] = "-";
        return "Shot missed";
    }

    public void updateTopBoard(String move, boolean hit) {
        top[move.charAt(0) - 65][move.charAt(1) - 48] = hit ? "X" : "-";
    }

    public boolean allDead() {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).isAlive()) {
                return false;
            }
        }
        return true;
    }

    public ArrayList<Ship> getShips() {
        return ships;
    }

    private void setDefaultBoards() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                top[i][j] = "*";
                bottom[i][j] = "*";
            }
        }
    }

    public String[][] getBottomBoard() {
        return bottom;
    }

    public String[][] getTopBoard() {
        return top;
    }

    public void drawShips() {
        for (int i = 0; i < ships.size(); i++) {
            if (ships.get(i).getDirection() == 'n' || ships.get(i).getDirection() == 'N') {
                int col = ships.get(i).getPosition().charAt(1) - 48;
                for (int z = 0; z < ships.get(i).getLength(); z++) {
                    int row = (ships.get(i).getPosition().charAt(0) - z) - 65;
                    bottom[row][col] = "O";
                }
            } else if (ships.get(i).getDirection() == 's' || ships.get(i).getDirection() == 'S') {
                int col = ships.get(i).getPosition().charAt(1) - 48;
                for (int z = 0; z < ships.get(i).getLength(); z++) {
                    int row = (ships.get(i).getPosition().charAt(0) + z) - 65;
                    bottom[row][col] = "O";
                }
            } else if (ships.get(i).getDirection() == 'e' || ships.get(i).getDirection() == 'E') {
                int row = (ships.get(i).getPosition().charAt(0)) - 65;
                for (int z = 0; z < ships.get(i).getLength(); z++) {
                    int col = ships.get(i).getPosition().charAt(1) + z - 48;
                    bottom[row][col] = "O";
                }
            } else if (ships.get(i).getDirection() == 'w' || ships.get(i).getDirection() == 'W') {
                int row = (ships.get(i).getPosition().charAt(0)) - 65;
                for (int z = 0; z < ships.get(i).getLength(); z++) {
                    int col = ships.get(i).getPosition().charAt(1) - z - 48;
                    bottom[row][col] = "O";
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("\n########################\n#  0 1 2 3 4 5 6 7 8 9 #\n");
        for (int i = 0; i < 10; i++) {
            s.append("#");
            s.append((char) (i + 65));
            s.append(" ");
            for (int j = 0; j < 10; j++) {
                s.append(top[i][j]);
                s.append(" ");
            }
            s.append("#\n");
        }
        s.append("########################\n#  0 1 2 3 4 5 6 7 8 9 #\n");
        for (int i = 0; i < 10; i++) {
            s.append("#");
            s.append((char) (i + 65));
            s.append(" ");
            for (int j = 0; j < 10; j++) {
                s.append(bottom[i][j]);
                s.append(" ");
            }
            s.append("#\n");
        }
        s.append("########################\n");
        return s.toString();
    }
}