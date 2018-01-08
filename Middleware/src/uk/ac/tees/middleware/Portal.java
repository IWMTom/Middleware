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
        
        this.t.start();
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
     * starts its respective thread, and adds a reference of the 
     * Portal/Agent connection to the Router.
     * 
     * @param a reference to Meta Agent
     */
    public void addAgent(MetaAgent a)
    {
        this.r.addAgent(a, this);
        this.directory.put(a.getName(), a);
        a.start(this);            
    }
    
    /**
     * Finds the agent in the directory, and puts the message
     * onto its blocking queue - if the agent isn't available in
     * the Portal's directory, the Router is consulted.
     * 
     * @param m reference to Message
     * @throws InterruptedException 
     */
    @Override
    public void handleMessage(Message m)
    {
        Middleware.demo.addRow(m, "Portal: " + this.getName());
        
        if (this.directory.containsKey(m.getRecipient()))
        {
            try
            {
                directory.get(m.getRecipient()).put(m);
            }
            catch (InterruptedException ex) {}            
        }
        else
        {
            this.r.handleMessage(m);
        }
    }
    
    @Override
    public void start(Portal p){}    
}