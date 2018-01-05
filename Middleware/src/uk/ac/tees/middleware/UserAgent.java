/**
 * UserAgent.java
 * A wrapper for a Meta Agent, that adds implementation for assigning
 * a Meta Agent to a Portal.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

public class UserAgent extends MetaAgent
{
    private MessageListener ml;
    private Portal portal;
    
    /**
     * Class constructor
     * 
     * @param name name of the Meta Agent
     */
    public UserAgent(String name)
    {
        super(name);
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
     * Puts the message into the Portal blocking queue
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    public void sendMessage(Message m) throws InterruptedException
    {
        this.portal.put(m);
    }   
    
    public void addMessageListener(MessageListener ml)
    {
        this.ml = ml;
    }    

    @Override
    void handleMessage(Message m)
    {
        this.ml.messageReceived(m);
    }

}