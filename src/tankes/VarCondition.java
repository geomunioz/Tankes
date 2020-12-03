/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankes;


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Lock;
/**
 *
 * @author Jorge
 */
public class VarCondition {
    private Lock Vcondition;
    private boolean var;
    private Condition condition;
    
    VarCondition(){
        Vcondition = new ReentrantLock();
        condition = Vcondition.newCondition();
    }
    
    public void Acquire(){
        
    }
    
    public void Release(){
        
    }
    
    
}
