/**
 * MetaAgent.java
 * An abstract class that defines a Meta Agent in the Middleware.
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
    private Portal portal;
    private Thread t;

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
    
    /**
     * Sets the Portal, and starts the Blocking Queue thread
     * 
     * @param p reference to the Portal
     */
    public void start(Portal p)
    {
        this.portal = p;
        this.t.start();
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
    
    /**
     * Puts the message into the Portal blocking queue
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    public void sendMessage(Message m) throws InterruptedException
    {
        portal.put(m);
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