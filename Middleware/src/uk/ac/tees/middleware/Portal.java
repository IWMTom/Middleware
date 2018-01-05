/**
 * Portal.java
 * A class that defines a Portal in the Middleware.
 * It is an extension of a Meta Agent, with a directory of
 * other Meta Agents (User Agents and Portals).
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.HashMap;

public class Portal extends MetaAgent
{
    private HashMap<String, MetaAgent> directory = new HashMap<>();

    /**
     * Class constructor - starts blocking queue thread
     * 
     * @param name name of the Portal
     */      
    public Portal(String name)
    {
        super(name);
        
        this.t.start();
    }
    
    public void start(Portal p){}
    
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
    @Override
    public void handleMessage(Message m)
    {
        try
        {
            directory.get(m.getRecipient()).put(m);
        }
        catch (InterruptedException ex) {}
    }
}