/**
 * MetaAgent.java
 * An abstract class that defines a Meta Agent in the middleware.
 * It is an extension of a Message Queue, and implements the Runnable
 * interface, to allow the message queue to be in its own thread.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class MetaAgent extends MessageQueue implements Runnable
{
    private String name;
    private Thread t;
    private boolean isRunning;
    private AgentMonitor am;

    /**
     * Class constructor
     * 
     * @param name name of the Meta Agent
     */  
    public MetaAgent(String name)
    {
        super();
        this.name = name;
    }
    
    /**
     * If the thread isn't already running, it is started
     */
    public void start()
    {
        if (!this.isRunning)
        {
            this.t          = new Thread(this);
            this.isRunning  = true;
            this.t.start();  
        }
    }
    
    /**
     * Gets the status of the thread
     * 
     * @return isRunning
     */
    public boolean isRunning()
    {
        return this.isRunning;
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
            }
            catch (UnroutableException ex)
            {
                Logger.getLogger(MetaAgent.class.getName()).log(Level.WARNING, "Recipient Agent unreachable", ex);
            }
            catch (UnhandledMessageException ex)
            {
                Logger.getLogger(MetaAgent.class.getName()).log(Level.WARNING, "Message handler not specified", ex);
            } catch (Exception ex) {}
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
     * Gets the thread
     * 
     * @return thread 
     */
    public Thread getThread()
    {
        return this.t;
    }
    
    /**
     * Abstract method for User Agents to handle incoming messages
     * 
     * @param m reference to Message
     */
    abstract void handleMessage(Message m) throws Exception;
    
    
    public void setAgentMonitor(AgentMonitor am)
    {
        this.am = am;
    }
    
    
    public AgentMonitor getAgentMonitor()
    {
        return this.am;
    }
}