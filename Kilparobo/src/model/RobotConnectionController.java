package model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/*
*
*Yhteyden muodostamis luokka. TÃ¤mÃ¤n avulla saadaan yhteys robottiin langattomasti */

public class RobotConnectionController {
    
    public Socket s;
    public DataOutputStream out;
    public DataInputStream in;
    
    boolean laheta = true;
    boolean vastaanota = false;
    
    long aika;
    
    /* Konstruktori yrittÃ¤Ã¤ ottaa yhteyden robottiin */
    public RobotConnectionController(){
        
        /* Perus yhteyden muodostaminen */
        try {
            s = new Socket("10.0.1.1", 1111);
            System.out.println("Yhteyden muodostus onnistui!");
        } catch (UnknownHostException e) {
            e.printStackTrace();
            System.exit(-1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        
        
        /* OutputStreamin muodostaminen */
        try {
            out = new DataOutputStream(s.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /* InputStreamin muodostaminen */
        try {
            in = new DataInputStream(s.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        /* Alustetaan vastaan otettava aika */
        aika = 0;
    }
    
    /* YrittÃ¤Ã¤ lÃ¤hettÃ¤Ã¤ robotille arvon True */
    public void lahetaTrue() {
        try {
            out.writeBoolean(laheta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* YrittÃ¤Ã¤ vastaan ottaa arvon True robotilta */
    public void vastaanotaTrue() {
        try {
             vastaanota = in.readBoolean();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* YrittÃ¤Ã¤ vastaan ottaa robotilta long tyyppisen aika muutujan */
    public void vastaanotaAika() {
        try {
            aika = in.readLong();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /* Palauttaa ajan */
    public long palautaAika() {
        return aika;
    }
    
    /* Nollaa ajan */
    public void nollaaAika() {
        this.aika = 0;
    }
    

}