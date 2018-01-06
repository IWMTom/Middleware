/**
 * MetaAgent.java
 * An abstract class that defines a Meta Agent in the middleware.
 * It is an extension of a Linked Blocking Queue, and implements the Runnable
 * interface, to allow the blocking queue to be in its own thread.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.concurrent.LinkedBlockingQueue;

public abstract class MetaAgent extends LinkedBlockingQueue implements Runnable
{
    private String name;
    protected Thread t;

    /**
     * Class constructor
     * 
     * @param name name of the Meta Agent
     */  
    public MetaAgent(String name)
    {
        super();
        this.name = name;
        this.t = new Thread(this);
    }
    
    public abstract void start(Portal p);
    
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
    
    /**
     * Gets the name of the Meta Agent
     * 
     * @return name 
     */
    public String getName()
    {
        return this.name;
    }
    
    /**
     * Abstract method for User Agents to handle incoming messages
     * 
     * @param m reference to Message
     */
    abstract void handleMessage(Message m);
}