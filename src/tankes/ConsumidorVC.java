/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankes;

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
public class ConsumidorVC extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private Lock VCondition;
    private boolean vc;
    private Condition condition;
    private Lock mutex;
    private JLabel porce;
    
    public ConsumidorVC(DibujaTanke panel, Y rc, JLabel porce, Lock mutex, Condition condicion){
        this.panel=panel;
        this.rc=rc;
        this.mutex = mutex;
        this.condition = condicion;
        this.porce = porce;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()>0){
                if(mutex.tryLock()){    //mutex.lock();
                    try{
                        condition.await();
                    }catch(Exception e){}
                    finally{
                        mutex.unlock();
                    }
                }   
                
                    panel.agua.getAgua().remove(panel.agua.getAgua().size()-1);
                    porce.setText(2.5*panel.agua.getAgua().size()+" %");
                    panel.repaint();
                    rc.setY(rc.getY()+5);
                    try{
                        sleep((int)(500+Math.random()*200));
                    }catch(Exception e){}
                
                
            }
        }
    }
}
