/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg3itb2_patnactka;

/**
 *
 * @author usd07
 */
public class Hra {
    logikahtHry logika=new logikahtHry();
    Okno okno;
    
    public Hra(){
        okno=new Okno(logika);
        okno.setVisible(true);
    }
}
