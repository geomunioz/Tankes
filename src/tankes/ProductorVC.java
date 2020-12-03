/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankes;

import javax.swing.*;
import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class ProductorVC extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private JLabel porce;
    private boolean vc;
    private Condition condition;
    private Lock mutex;
    
    public ProductorVC(DibujaTanke panel, Y rc, JLabel porce, Lock mutex, Condition condicion){
        this.panel=panel;
        this.rc=rc;
        this.porce = porce;
        this.mutex = mutex;
        this.condition = condicion;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()<40){
                
                    panel.agua.getAgua().add(new Rectangle2D.Double(50,rc.getY(), 100, 5));
                    porce.setText(2.5*panel.agua.getAgua().size()+" %");
                    panel.repaint();
                    rc.setY(rc.getY()-5);
                    try{
                        sleep((int)(500+Math.random()*200));
                    }catch(Exception e){}
                    
                if(mutex.tryLock()){    
                    try{
                        condition.signal();
                    }catch(Exception e){}
                    finally{
                        mutex.unlock();
                    }
                }
            }
        }
    }
}
