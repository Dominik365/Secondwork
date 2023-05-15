/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3itb2_patnactka;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 *
 * @author usd07
 */
public class logikahtHry {
    int[][] hracipole = new int[4][4];
    int x0=3; //souradnice prazdneho policka, jeho hodnota je 0
    int y0=3;
    
    
    public logikahtHry(){
        NaplnPole();
        VypisPole();      
    }
    private void NaplnPole() {
        
        for (int i = 0; i < hracipole.length; i++) {
            for (int j = 0; j < hracipole.length; j++) {
                hracipole[i][j] = i*4+j+1;
                if(hracipole[i][j]==16){
                    hracipole[i][j]=15;
                }
            }
 
        }       
       
        for(int i=0;i<hracipole.length;i++){
        shuffleArray(hracipole[i]);
       

    }
         hracipole[3][3]=0;
    }
    static void shuffleArray(int[] ar)
  {
    // If running on Java 6 or older, use `new Random()` on RHS here
    Random rnd = ThreadLocalRandom.current();
    for (int i = ar.length - 1; i > 0; i--)
    {
      int index = rnd.nextInt(i + 1);
      // Simple swap
      int a = ar[index];
      ar[index] = ar[i];
      ar[i] = a;
    }
  }
    
    private void VypisPole() {
        System.out.println("------------------");
        for (int i = 0; i < hracipole.length; i++) {
            for (int j = 0; j < hracipole.length; j++) {
                System.out.print(hracipole[i][j]+",");
            }
            System.out.println("");
        }
    }
  
    public void PresunCislo(int i, int j){
        //pokud bude mozno presune se cislo na pozici i,j na volne policko
        //kdyz bude jeden index stejny jako prazne policko a jeden se bude lisit o jednicku pak muzeme presunout cislo
        if((i==x0 && Math.abs(j-y0)==1) || (j==y0 && Math.abs(i-x0)==1)){
            //muzu polickem posunout
            System.out.println("Muzu posunout");
            hracipole[x0][y0]=hracipole[i][j];
            hracipole[i][j]=0;
            x0=i; //prenastaveni pozic nuloveho policka
            y0=j;
        }
    }
    
    public boolean Vyhodnot(){
        for (int i = 0; i < hracipole.length; i++) {
            for (int j = 0; j < hracipole.length; j++) {
                if(!(i==3 && j==3) && !(hracipole[i][j]==i*4+j+1)){
                    //aktualni cislo neodpovidat tomu co tam ma byt
                    return false;
                }
            }            
        }
        return true;
    }
    
    public int[][] getHodnotyPole(){
        return hracipole;
    }
}
