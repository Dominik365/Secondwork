/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pong;



/**
 *
 * @author pytho
 */
public class Pong {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
          GUI frame = new GUI();
          frame.setSize(800, 600);
          frame.setVisible(true);
          

        while (true) { // cylkus pro pohyb 
            frame.pongLogic.moveBall();
            frame.RepaintPanel();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
