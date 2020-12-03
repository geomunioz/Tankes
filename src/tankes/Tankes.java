package tankes;
import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Tankes extends JFrame{
    private DibujaTanke panelMutex, panelSem, panelVC, panelMoni, panelBarre;
    private Y rcMutex, rcSem, rcMoni, rcBarre, rcVC;
    private ProductorMutex pMutex;
    private ConsumidorMutex cMutex;
    private ProductorSem pSem;
    private ConsumidorSem cSem;
    private ProductorVC pVC;
    private ConsumidorVC cVC;
    private ProductorMoni pMoni;
    private ConsumidorMoni cMoni;
    private ProductorBarre pBarre;
    private ConsumidorBarre cBarre;
    private Agua aguaMutex, aguaSem, aguaVC, aguaMoni, aguaBarre;
    private JLabel etiMutex, etiSem, etiVC, etiMoni, etiBarre;
    private JLabel etiPorceMutex, etiPorceSem, etiPorceVC, etiPorceMoni, etiPorceBarre;
    private Lock mutex, mutexVC;
    private Semaphore semaforo;
    private Condition condicion;
    private Barrier barrera;
    
    public Tankes(){
        setSize(900,400);
        setLocation(250,200);
        etiMutex = new JLabel("Mutex");
        etiSem = new JLabel("Semaforo");
        etiVC = new JLabel("Variable de Condicion");
        etiMoni = new JLabel("Monitores");
        etiBarre = new JLabel("Barreras");
        etiPorceMutex = new JLabel("%");
        etiPorceSem = new JLabel("%");
        etiPorceVC = new JLabel("%");
        etiPorceMoni = new JLabel("%");
        etiPorceBarre = new JLabel("%");
        rcMutex = new Y();
        rcSem = new Y();
        rcVC = new Y();
        rcBarre = new Y();
        rcMoni = new Y();
        aguaMutex = new Agua();
        aguaSem = new Agua();
        aguaVC = new Agua();
        aguaMoni = new Agua();
        aguaBarre = new Agua();
        panelMutex = new DibujaTanke(aguaMutex);
        panelSem = new DibujaTanke(aguaSem);
        panelVC = new DibujaTanke(aguaVC);
        panelMoni = new DibujaTanke(aguaMoni);
        panelBarre = new DibujaTanke(aguaBarre);
        
        mutex = new ReentrantLock();
        mutexVC = new ReentrantLock();
        condicion = mutexVC.newCondition();
        semaforo = new Semaphore(1);
        barrera = new Barrier(1);
        
        pMutex = new ProductorMutex(panelMutex,rcMutex, etiPorceMutex, mutex);
        cMutex = new ConsumidorMutex(panelMutex,rcMutex, etiPorceMutex, mutex);
        pSem = new ProductorSem(panelSem,rcSem, etiPorceSem, semaforo);
        cSem = new ConsumidorSem(panelSem, rcSem, etiPorceSem, semaforo);
        pVC = new ProductorVC(panelVC,rcVC, etiPorceVC, mutexVC, condicion);
        cVC = new ConsumidorVC(panelVC, rcVC, etiPorceVC, mutexVC, condicion);
        pMoni = new ProductorMoni(panelMoni, rcMoni,etiPorceMoni);
        cMoni = new ConsumidorMoni(panelMoni,rcMoni,etiPorceMoni);
        pBarre =  new ProductorBarre(panelBarre, rcBarre, etiPorceBarre, barrera);
        cBarre = new ConsumidorBarre(panelBarre, rcBarre, etiPorceBarre, barrera);
        
        getContentPane().setLayout(new GridLayout());
        panelMutex.setLayout(null);
        etiMutex.setBounds(80, 20, 100, 25);
        etiPorceMutex.setBounds(90, 300, 100, 25);
        panelMutex.add(etiMutex);
        panelMutex.add(etiPorceMutex);
        
        panelSem.setLayout(null);
        etiSem.setBounds(70, 20, 100, 25);
        etiPorceSem.setBounds(90, 300, 100, 25);
        panelSem.add(etiSem);
        panelSem.add(etiPorceSem);
        
        panelVC.setLayout(null);
        etiVC.setBounds(50, 20, 100, 25);
        etiPorceVC.setBounds(90, 300, 100, 25);
        panelVC.add(etiVC);
        panelVC.add(etiPorceVC);
        
        panelMoni.setLayout(null);
        etiMoni.setBounds(70, 20, 100, 25);
        etiPorceMoni.setBounds(90, 300, 100, 25);
        panelMoni.add(etiMoni);
        panelMoni.add(etiPorceMoni);
        
        panelBarre.setLayout(null);
        etiBarre.setBounds(70, 20, 100, 25);
        etiPorceBarre.setBounds(90, 300, 100, 25);
        panelBarre.add(etiBarre);
        panelBarre.add(etiPorceBarre);
        
        getContentPane().add(panelMutex);
        getContentPane().add(panelSem);
        getContentPane().add(panelVC);
        getContentPane().add(panelMoni);
        getContentPane().add(panelBarre);
        
        pMutex.start();
        cMutex.start();
        pSem.start();
        cSem.start();
        pVC.start();
        cVC.start();
        pMoni.start();
        cMoni.start();
        pBarre.start();
        cBarre.start();
                
    }
    public static void main(String[] args) {
        Tankes fr = new Tankes();
        fr.setVisible(true);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
}
