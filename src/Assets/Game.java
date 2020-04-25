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
public class Game {
    private String name;
    Player player1; 
    Player player2;
    Game(){
        
    }
    Game(String name, Player player1, Player player2){
        this.name = name;
        this.player1 = player1;
        this.player2 = player2;
    }
    
    public void start(){
        player1.setShipPos();
        player2.setShipPos();
        //if statement player 1 turn
        /*if(Math.random() < 0.5){
            System.out.println(player1.getName() + "'s move");
            String move = player1.getMove();
            player1.updateTopBoard(move, player2.firedUpon(move));
            player1.getBoard().toString();
        }
*/
        while(!player2.gameOver()){
            //player 2 turn
            System.out.println(player2.getName() + " move");
            String move = player2.getMove();
            player2.updateTopBoard(move, player1.firedUpon(move));
            System.out.println(player1.getBoard());
            //if statement is game over
            if(player1.gameOver()){
                break;
            }
            //player 1 turn
            System.out.println(player1.getName() + " move");
            move = player1.getMove();
            player1.updateTopBoard(move, player2.firedUpon(move));
            System.out.print(player1.getBoard());
        }
        System.out.println("Game over!");
        
    }
    public void save(){
        
    }
    public void load(){
        
    }
}
