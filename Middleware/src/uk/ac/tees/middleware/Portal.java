/**
 * Portal.java
 * A class that defines a Portal in the Middleware.
 * It is an extension of a Linked Blocking Queue, and implements the Runnable
 * interface, to allow the blocking queue to be in its own thread.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class Portal extends LinkedBlockingQueue implements Runnable
{
    private final String name;
    private final Thread t;
    private HashMap<String, MetaAgent> directory = new HashMap<>();

    /**
     * Class constructor - starts blocking queue thread
     * 
     * @param name name of the Portal
     */      
    public Portal(String name)
    {
        this.name = name;
        this.t = new Thread(this);
        
        this.t.start();
    }
    
    /**
     * Adds an agent to the directory of agents for the Portal,
     * and starts its respective thread
     * 
     * @param a reference to Meta Agent
     */
    public void addAgent(MetaAgent a)
    {
        directory.put(a.getName(), a);
        a.start(this);
    }
    
    /**
     * Finds the agent in the directory, and puts the message
     * onto its blocking queue
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    public void handleMessage(Message m) throws InterruptedException
    {
        directory.get(m.getRecipient()).put(m);
    }

    /**
     * Constantly pulls from the queue and sends messages to the message handler
     */
    public void run()
    {
        while (true)
        {
            try 
            {
                handleMessage((Message) this.take());
                Thread.sleep(100);
            }
            catch (InterruptedException ex){}     
        }
    }
}