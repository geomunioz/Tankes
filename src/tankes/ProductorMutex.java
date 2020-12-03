package tankes;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.awt.geom.*;

public class ProductorMutex extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private Lock mutex;
    private JLabel porce;
    
    public ProductorMutex(DibujaTanke panel, Y rc, JLabel porce, Lock mutex){
        this.panel=panel;
        this.rc=rc;
        this.mutex = mutex;
        this.porce = porce;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()<40){
                try{
                    if (mutex.tryLock()) {
                        try{
                            panel.agua.getAgua().add(new Rectangle2D.Double(50,rc.getY(), 100, 5));
                            porce.setText((2.5*panel.agua.getAgua().size())+"%");
                            panel.repaint();
                            rc.setY(rc.getY()-5);
                            sleep((int)(500+(int) Math.random()*200));
                        }finally{
                            mutex.unlock();
                        }
                    }
                }catch(Exception e){
                    System.out.println("Error: "+e);
                }                
            }
        }
    }
}
