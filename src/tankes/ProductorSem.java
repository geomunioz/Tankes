/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankes;

import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class ProductorSem extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private Semaphore sem;
    private JLabel porce;
    
    public ProductorSem(DibujaTanke panel, Y rc, JLabel porce, Semaphore semaforo){
        this.panel=panel;
        this.rc=rc;
        this.porce = porce;
        this.sem = semaforo;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()<40){
                    try{
                        sem.acquire();
                        panel.agua.getAgua().add(new Rectangle2D.Double(50,rc.getY(), 100, 5));
                        porce.setText((2.5*panel.agua.getAgua().size())+"%");
                        panel.repaint();
                        rc.setY(rc.getY()-5);
                        sleep((int) (500+Math.random()*200));
                        sem.release();
                    }catch(Exception e){
                        System.out.println("Error");
                    }
            }
        }
    }
}
