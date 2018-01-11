/**
 * Agent.java
 * A User Agent - a wrapper for a Meta Agent, that adds
 * implementation for assigning a Meta Agent to a Portal.
 */

package uk.ac.tees.middleware;

public class Agent extends MetaAgent
{
    private MessageListener ml;
    private Portal portal;
    
    /**
     * Class constructor
     * 
     * @param name name of the Meta Agent
     */
    public Agent(String name)
    {
        super(name);
    }
    
    /**
     * Sets the Portal that the Agent is connected to
     * 
     * @param p reference to the Portal
     */
    public void setPortal(Portal p)
    {
        this.portal = p;
    }

    /**
     * Puts the message into the Portal blocking queue
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    public void sendMessage(Message m) throws InterruptedException
    {
        if (!this.portal.isRunning())
        {
            this.portal.start(); 
        }
        
        this.portal.put(m);
    }   
    
    /**
     * Adds a message listener to the User Agent
     * 
     * @param ml reference to MessageListener
     */
    public void addMessageListener(MessageListener ml)
    {
        this.ml = ml;
    }    

    /**
     * Passes the Message on to the MessageListener
     * If there is an AgentMonitor connected, messages are logged to it.
     * 
     * @param m reference to Message
     * @throws uk.ac.tees.middleware.UnhandledMessageException
     */
    @Override
    public void handleMessage(Message m) throws Exception
    {
        if (this.getAgentMonitor() != null)
        {
            if (!this.getAgentMonitor().isRunning())
            {
                this.getAgentMonitor().start();
            }
            
            this.getAgentMonitor().put(new Message(m.getRecipient(), m.getSender(), m.getMessage(), this.getName()));
        }      
        
        if (this.ml != null)
        {
            this.ml.messageReceived(m);
        }
        else
        {
            throw new UnhandledMessageException();
        }
    }

}