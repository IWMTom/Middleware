/**
 * Portal.java
 * A class that defines a Portal in the middleware.
 * It is an extension of a Meta Agent, with a directory of other agents.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.HashMap;

public class Portal extends MetaAgent
{
    private HashMap<String, MetaAgent> directory = new HashMap<>();
    private Router r;

    /**
     * Class constructor - starts blocking queue thread
     * 
     * @param name name of the Portal
     */      
    public Portal(String name)
    {
        super(name);
    }
    
    /**
     * Sets the router that the Portal is connected to
     * 
     * @param r reference to Router
     */
    public void setRouter(Router r)
    {
        this.r = r;
    }
    
    /**
     * Adds an agent to the directory of agents for the Portal,
     * starts its respective thread.
     * 
     * If a Router is connected, a reference to the agent is added to it.
     * 
     * @param a reference to Meta Agent
     */
    public synchronized void addAgent(Agent a)
    {
        if (this.r != null)
        {
            this.r.addAgent(a, this);
        }
        
        this.directory.put(a.getName(), a);
        a.setPortal(this);            
    }
    
    /**
     * Finds the agent in the directory, and puts the message
     * onto its blocking queue - if the agent isn't available in
     * the Portal's directory and a Router is connected, it is consulted.
     * If there isn't a Router connected, an exception is thrown.
     * If there is an AgentMonitor connected, messages are logged to it.
     * 
     * @param m reference to Message
     * @throws uk.ac.tees.middleware.UnroutableException
     * @throws java.lang.InterruptedException
     */
    @Override
    public void handleMessage(Message m) throws UnroutableException, InterruptedException
    {
        if (this.getAgentMonitor() != null)
        {
            if (!this.getAgentMonitor().isRunning())
            {
                this.getAgentMonitor().start();
            }
            
            this.getAgentMonitor().put(new Message(m.getRecipient(), m.getSender(), m.getMessage(), this.getName()));
        }
        
        if (this.directory.containsKey(m.getRecipient()))
        {
            MetaAgent recipient = directory.get(m.getRecipient());

            if (!recipient.isRunning())
            {
                recipient.start();
            }

            recipient.put(m);            
        }
        else
        {
            if (this.r != null)
            {
                if (!this.r.isRunning())
                {
                    this.r.start();
                }

                this.r.handleMessage(m);                
            }
            else
            {
                throw new UnroutableException();
            }
        }
    } 
}