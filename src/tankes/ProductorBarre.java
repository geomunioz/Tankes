/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankes;

import java.awt.geom.Rectangle2D;
import static java.lang.Thread.sleep;
import javax.swing.JLabel;

/**
 *
 * @author Jorge
 */
public class ProductorBarre extends Thread{
    private Y rc;
    private DibujaTanke panel;
    private JLabel porce;
    private Barrier barrera;
    
    public ProductorBarre(DibujaTanke panel, Y rc, JLabel porce, Barrier barrera){
        this.panel=panel;
        this.rc=rc;
        this.porce = porce;
        this.barrera = barrera;
    }
    public void run(){
        while(true){
            if(panel.agua.getAgua().size()<=40){
                barrera.call();
                panel.agua.getAgua().add(new Rectangle2D.Double(50,rc.getY(), 100, 5));
                porce.setText((2.5*panel.agua.getAgua().size())+"%");
                panel.repaint();
                rc.setY(rc.getY()-5);
                try{
                    sleep((int) (500+Math.random()*200));
                }catch(Exception e){}
            } 
            
        }
    }
}
