/**
 * Router.java
 * A class that defines a Router in the middleware.
 * It is an extension of a Meta Agent, with a directory of Portal/Agents.
 * The Router has routing information for every portal in the middleware.
 * 
 * @author Tom Wilson (S605130)
 */

package uk.ac.tees.middleware;

import java.util.HashMap;

public class Router extends MetaAgent
{
    private HashMap<String, Portal> directory = new HashMap<>();
    
    /**
     * Class constructor
     * 
     * @param name name of the Router
     */
    public Router(String name)
    {
        super(name);
        this.t.start();
    }
    
    /**
     * Adds a link between an Agent and Portal to the global routing table.
     * 
     * @param a reference to Meta Agent
     * @param p reference to Portal
     */
    public void addAgent(MetaAgent a, Portal p)
    {
        directory.put(a.getName(), p);
    }
    
    /**
     * Passes the message on to the respective Portal
     * 
     * @param m reference to Message
     */
    @Override
    public void handleMessage(Message m)
    {
        directory.get(m.getRecipient()).handleMessage(m);
    }

    @Override
    public void start(Portal p){}
}