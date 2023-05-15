/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pong;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 *
 * @author pytho
 */
public class GameLogic {
    private int ballX = 400; // parametry míče
    private int ballY = 300;
    private int paddle1Y = 250; // pozice pálek na začátku hry
    private int paddle2Y = 250;
    private int ballXSpeed = 2; // rychlost míče
    private int ballYSpeed = 2;
    private int paddleSpeed = 10; // rychlost pálek
    
    private int paddleHeight = 80; // velikosti pro vykreslování
    private int paddleWidth = 15;
    private int topBorder = 15; // hranice pro detekci kolizí
    private int botBorder = 560;
    private int boundaryLeft = 50; // hranice pro detekci kolizí
    private int boundaryRight = 735;
    
    private int player1Points = 0; // body
    private int player2Points = 0;
    
    private boolean isGameOver = true; // stopne hru pokud 
public GameLogic(){
    
}
 public void draw(Graphics g) { // vykreslení hrací plochy
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 800, 600);
        g.setColor(Color.WHITE);
        g.fillRect(ballX, ballY, 15, 15);
        g.fillRect(50, paddle1Y, paddleWidth, paddleHeight);
        g.fillRect(735, paddle2Y, paddleWidth, paddleHeight);
    }
 public void paddleMove(int keyCode) {
     if(!isGameOver){ // pokud hra není zastavená hýbe s pálkami podle kláves  
     switch (keyCode) {
            case 87: // S
                paddle1Y -= paddleSpeed;
                break;
            case 83: // W
                paddle1Y += paddleSpeed;
                break;
            case 38: // down
                paddle2Y -= paddleSpeed;
                break;
            case 40: // up
                paddle2Y += paddleSpeed;
                break;
        }
    }
     else if(keyCode == 32){ // mezerník
         isGameOver = false;
     }
 }
  public void paddleStop(int keyCode) { // není potřebná může být zakomentována
  
  }
   public void moveBall() {
       if(!isGameOver){ // pokud není hra zastavená
       ballX += ballXSpeed; // pohne s míčem v X a Y směru podle rychlosti
        ballY += ballYSpeed;

        if (ballY >= botBorder || ballY <= topBorder) // kolize v horní a dolní hraně
            ballYSpeed *= -1;

        if (ballX <= boundaryLeft) { // kolize v levé a pravé hraně
            if (ballY > paddle1Y && ballY < paddle1Y + paddleHeight) // kontroluje jestli se míč dotkl pálky
                ballXSpeed *= -1;
            else{ // pokud ne přičte bod dannému hráči
                System.out.println("Player 2 scores!");
                updatePoints(2);
                resetGame(); // resetuje hru
                
            }
        } else if (ballX >= boundaryRight) {
            if (ballY > paddle2Y && ballY < paddle2Y + paddleHeight)
                ballXSpeed *= -1;
            else{
                 System.out.println("Player 1 scores!");
                 updatePoints(1);
                 resetGame();
            }
               
        
        }
       }
    }
   private void updatePoints(int player){ // počítá body
       if(player == 1){
           player1Points++;
           if(player1Points >= 5){
               endGame("Player 1 wins!");
           }
       }
       else{
           player2Points++;
           if(player2Points >= 5){
               endGame("Player 2 wins!");
           }
       }
       
   }
   private void endGame(String msg){ // konec hry
     JOptionPane.showMessageDialog(new Frame(),msg);
   }
   private void resetGame(){ // reset míče a pálek do původního stavu
        ballX = 400;
        ballY = 300;
        paddle1Y = 250;
        paddle2Y = 250;
        isGameOver = true;
   }
  
}
