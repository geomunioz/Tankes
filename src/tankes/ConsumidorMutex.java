package tankes;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.geom.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import static java.lang.Thread.sleep;

public class ConsumidorMutex extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private Lock mutex;
    private JLabel porce;
    
    public ConsumidorMutex(DibujaTanke panel, Y rc, JLabel porce, Lock mutex){
        this.panel=panel;
        this.rc=rc;
        this.mutex = mutex;
        this.porce = porce;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()>0){
                try{
                    if (mutex.tryLock()) {
                        try{
                            panel.agua.getAgua().remove(panel.agua.getAgua().size()-1);
                            porce.setText((2.5*panel.agua.getAgua().size())+"%");
                            panel.repaint();
                            rc.setY(rc.getY()+5);
                            sleep((int)(500+(int)Math.random()*200));
                        }finally{
                            mutex.unlock();
                        }
                    }
                }catch(Exception e){}
            }
        }
    }
}
